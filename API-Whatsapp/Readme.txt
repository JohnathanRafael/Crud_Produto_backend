ARQUIVOS API 
https://www.youtube.com/watch?v=uCoSzw9L0SQ

criar arquivo jason
- npm init

instalar typescript

pacotes basicos
npm install nodemon rimraf ts-node @types/node --save-dev

cirar arquivo nodemon para atualizar o node 
{
    "watch": ["src"],
    "ext": ".ts, .js",
    "ignore": [],
    "exec": "npx ts-node ./src/app.ts"
}

criar pastas 
build - com .keep
src  - com app.ts
arquivo .gitgnore - com 
node_modules
./build
!./build/.keep

insatalar venom bot 
npm i --save venom-bot
https://github.com/orkestral/venom

executar ts config
npx tsc --init

trocar tsconfig para

{
    "compilerOptions": {
        "target": "es5",
        "module": "commonjs",
        "lib": ["es6"],
        "allowJs": true,
        "outDir": "src",
        "strict": true,
        "strictPropertyInitialization": false,
        "noImplicitAny": true,
        "esModuleInterop": true,
        "resolveJsonModule": true,
        "skipLibCheck": true,
        "forceConsistentCasingInFileNames": true 
    },
    "include": ["src/**/*"],
    "exclude": ["**/*.spec.ts"]
  }

Até aqui tem uma aplicacao que envia mensagens no whatsapp ------

Comecar a criar API
npm i dotenv express --save
npm i @types/dotenv @types/express --saved-dev

instalar biblioteca para validar e transformar numero
npm i libphonenumber-js --save
