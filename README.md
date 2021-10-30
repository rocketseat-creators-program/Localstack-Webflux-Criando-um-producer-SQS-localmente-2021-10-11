<img src="https://storage.googleapis.com/golden-wind/experts-club/capa-github.svg" />

# Localstack + Webflux: Criando um producer SQS localmente

Nessa aula vamos aprender como utilizar o localstack que é um emulador de serviços AWS para desenvolver um producer SQS com Webflux e AWS SDK.

Tecnologias utilizadas: Spring Webflux, Localstack, AWS SDK, AWS CLI, SQS e Docker


### Comandos para criar ambiente e testar a aplicação 
- Subir o container localstack com o sqs 


  ````
  docker-compose up
  ````
  
 - Criar fila SQS


  ````
   aws --endpoint-url=http://localhost:4566 sqs create-queue --queue-name process-order-queue
  ````
  
 - Criar mensagens na fila


  ````
   curl --location --request POST 'http://localhost:8080/checkout/create-payment
  ````
  
  ## Arquitetura producer e consumer:
 
 <img src="https://github.com/rocketseat-experts-club/Localstack-Webflux-Criando-um-producer-SQS-localmente-2021-10-11/blob/main/arquitetura.drawio%20(2).png">
  


[Aula com o desenvolvimento do consumer]() em breve


[Slides](https://github.com/rocketseat-experts-club/Localstack-Webflux-Criando-um-producer-SQS-localmente-2021-10-11/blob/main/_Localstack%20%2B%20Webflux_%20Criando%20um%20producer%20SQS%20gratuitamente.pdf)


## Expert

| [<img src="https://avatars.githubusercontent.com/u/32311268?s=460&u=88788249fc35ea2f59f583dae36d674d34896839&v=4" width="75px;"/>](https://github.com/Kamilahsantos) |
| :-: |
|[Kamila Santos](https://github.com/Kamilahsantos)|



