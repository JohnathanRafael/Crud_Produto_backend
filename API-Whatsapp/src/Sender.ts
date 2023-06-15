import { create, Whatsapp, Message, SocketState } from "venom-bot"
import parsePhoneNumber, {isValidPhoneNumber} from "libphonenumber-js"
import { connected } from "process"
import { linkSync } from "fs"

export type QRcode = {
    base64Qr: string
    attempts?: number
}

class Sender {
    private client: Whatsapp
    private connected: boolean
    private qr: QRcode
    public flag: number

    get isConnected(): boolean {
        return this.connected
    }

    get qrCode() : QRcode {
        return this.qr
    }

    async sendText(to: string, body: string){
        
        if(!isValidPhoneNumber(to, "BR"))
        {
            throw new Error('this number is not valid')
        }

        let phoneNumber = parsePhoneNumber(to, "BR")?.format("E.164")?.replace("+", "") as string

        phoneNumber = phoneNumber.includes("@c.us") ? phoneNumber : `${phoneNumber}@c.us`

        console.log("phoneNumber", phoneNumber)

        await this.client.sendText(phoneNumber, body)
    }
   
    async initialize() {
        this.flag = 1
        const qr = (base64Qr: string) => { this.qr = {base64Qr}}

        const status = (statusSession: string) => {

            this.connected = ["qrReadSuccess", "chatsAvailable", "isLogged", "successChat"].includes(statusSession)

        } 
        /*return isLogged || notLogged || browserClose || qrReadSuccess || qrReadFail 
         || autocloseCalled || desconnectedMobile || deleteToken || chatsAvailable || deviceNotConnected 
         || serverWssNotConnected || noOpenBrowser || initBrowser || openBrowser || connectBrowserWs || initWhatsapp 
         || erroPageWhatsapp || successPageWhatsapp || waitForLogin || waitChat || successChat*/
         const start = (client: Whatsapp) => {
            this.client = client
            //5562992097829@c.us
    
            client.onStateChange((state) =>{
                this.connected = state === SocketState.CONNECTED
            })

        };
    
        create('ProgWebI', qr, status)
            .then((client) => start(client))
            .catch((error) => console.error(error));
    }
}
export default Sender