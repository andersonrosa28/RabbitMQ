<h1 align="center">    
   Implementando RabbitMQ na pratica!
</h1>

<h4 align="center">
  Implementando direct, fanout e topic, de um modo simples!
</h4>

<p align="center">
  <a href="#api_producer-producer">Producer API</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#api_producer_ui">Producer UI</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#api-consumer">Consumer API</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#api-consumer-ui">Consumer UI</a>
</p>

<p align="center">
  <img alt="RabbitMQ" src="https://miro.medium.com/max/1200/1*sPS7EkIJMv4H7lqoaDRYiQ.png">
</p>

## Technologies

Esse projeto segue as seguintes tecnologias para seu desenvolvimento:

-  [Java 11]()
-  [Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
-  [RabbitMQ](https://www.rabbitmq.com/documentation.html)
-  [Docker]()


## Producer

Esse Microsserviço tem duas funcionalidade:
- Criar a Exchange de acordo com o fluxo, que pode ser: direct, fanout ou topic.
- Enviar o objeto para a Exchange quando for solicitado via tela ou api.


Não foi implementado nenhum padrão estrutural ou de codigo, a ideia é focar no funcionamento do RabbitMQ, mas, a aplicação segue uma estrutura basica de divisão de camadas:
<ul>
    <li>api</li>
    <li>domain
        <ul>
          <li>machine
                <ul>
                  <li>service</li>
                </ul>
            </li>
        </ul>
    </li>  
    <li>insfraestrutura
        <ul>
          <li>messageria
                <ul>
                  <li>config</li>
                </ul>
            </li>
        </ul>
    </li>  
</ul>  

### API
Nesta camada temos o MachineController camada responsavel por receber as requisições externas.

### Domain
Nesta camada temos 3 classes:
- MachineService: Nesta classe contém a implementação para o envio do objeto utilizando o RabbitTemplate, 
  para o envio utilizamos o método **convertAndSend**, passando o nome da exchange, routingKey e o objeto em json.
  <br><br>
- Machine: Classe somente com a estrutura de dados a ser enviada e recebida via controller. O ideal 
  nesse caso é termos um classe de entrada e uma classe de saida de dados, como por exemplo DTOs, 
  mas foi utilizado somente uma classe para facilitar o entendimento.
  <br><br>
- MachineAMQPConfig: Essa interface, contém os metodos(contrato) que será utilizado nas classes de configuração, a ideia
de criar essa interface é para facilitar a extensão da funcionalidade caso precise ser alterada futuramente para outro
  tipo de mensageria.

### Infraestrutura 
Nesta camada, dentro do pacote de mensageria temos 3 classes, a ideia desse Microsserviço é 
ser "dinamico" e funcionar com os 3 tipos de fluxo que o RabbitMQ trabalha: Direct, Fanout e Topic.

Então, para o funcionamento correto de cada uma dos fluxos, foi utilizado o profile do Spring, 
perceba que em cada classe de configuração temos a anotação @Profile, 
essa anotação irá definir qual o tipo de fluxo será utilizado quando nossa aplicação iniciar,
temos 3 profiles, sendo eles: RABBITMQ_DIRECT, RABBITMQ_FANOUT e RABBITMQ_TOPIC, cada um deles irá inicializar uma das 
classes de configuração abaixo.

- MachineAMQPDirectConfig: Se iniciado irá criar uma Exchange com o tipo Direct.

  <br><br>
- MachineAMQPFanoutConfig: Se iniciado irá criar uma Exchange com o tipo Fanout.

  <br><br>
- MachineAMQPTopicConfig:  Se iniciado irá criar uma Exchange com o tipo Topic.


## Producer UI
Escrever aqui...

## Consumer
Escrever aqui...

## Consumer UI
Escrever aqui...


Made with ♥ by Anderson Rosa :wave: [Get in touch!](https://www.linkedin.com/in/anderson-rosa-cascalho/)
