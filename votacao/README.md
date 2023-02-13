# Como Rodar o projeto
Tecnonoligas utilizadas:
* Java 17
* RabbitMQ
* PostgreSQL
* Flyway
* PgAdmin
* Swagger
  
# Entrar na pasta docker e executar o comando abaixo dentro das pastas rabbitmq e database
`````
docker-compose up -d
`````
Isso irá subir os serviços em container, PgAdmin e  RabbitMQ podem ser acessados via URL:

# PgAdmin : [localhost:8090](http://localhost:8090)
`````
user: admin@email.com
senha: root
`````
# RabbitMQ:  [localhost:15672](http://localhost:15672)
`````
user: admin
senha: 123456
`````

OBS: <br>
 Para configurar a conexão com o banco pelo PgAdmin é necessário adicionar o ip no hostname: <br> 'Connection -> Hostname/address' e
 setar o campo  para o ip 172.17.0.1

# [Swagger](http://localhost:8080/swagger-ui/index.html)
`````
http://localhost:8080/swagger-ui/index.html
`````
