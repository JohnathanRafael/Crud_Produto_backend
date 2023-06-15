import express, {Request, Response} from "express"
import Sender from "./Sender";
import { errorMonitor } from "events";
import { connect } from "http2";

const sender = new Sender()

const app = express()
app.use(express.json())
app.use(express.urlencoded({ extended: false }))

app.post('/conectar', async(req: Request, res: Response) => {
    if(sender.flag != 1)
    {
        await sender.initialize();  
    }
    else 
    {
        return res.send({
            qr_code: sender.qrCode,
            connected: sender.isConnected})
    }
         
})

app.get('/status', (req: Request, res: Response) => {
    return res.send({
         connected: sender.isConnected})
})

app.post('/send', async(req: Request, res: Response) => {
    const {number, message} = req.body

    try {
        // validar e transformar o número
        console.log(number, message)
        await sender.sendText(number, message)

        return res.status(200).json()
    } catch (error) {
        console.error("error", error)
        res.status(500).json({status: "500"})
    }
})

app.listen(5001, () => {
    console.log('🚩 Servidor Iniciado')
})