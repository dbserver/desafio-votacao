<p align="center">
  <h1 align="center">Sistema de Assembleia</h1>
</p>

<p align="center">

[![License: Apache 2.0](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
<img src="https://img.shields.io/badge/Version-1.0.0-brightgreen.svg"/>
<img src="https://img.shields.io/badge/PRs-welcome-brightgreen.svg"/>
</p>

## Tabela de Conteúdo

- [Sobre o Projeto](#sobre-o-projeto)
- [Funcionalidades](#funcionalidades)
- [Tecnologias](#tecnologias)
- [Modelo de Dados](#modelo-de-dados)
- [Pré-Requisitos](#pré-requisitos)
- [Como Rodar](#como-rodar)
- [Considerações Finais](#considerações-finais)

## Sobre o Projeto

Esse projeto tem como objetivo implementar um sistema para gerenciar a realização de assembleias, pautas e votações por associados.

## Funcionalidades

O sistema permite:

- Cadastrar e consultar associados
- Cadastrar e consultar assembleias
- Cadastrar  e consultar pautas
- Registrar votos dos associados nas pautas durante as assembleias
- Gerar relatórios com os resultados das votações

## Tecnologias

O sistema foi desenvolvido utilizando as seguintes tecnologias:

| Objetivo | Tecnologia |
| ------ | ------ |
| Linguagem de programação | Java 17 |
| Banco de Dados | PostgreSQL 12 |
| Framework | SpringBoot  |
| Interface para manipulação do banco | PgAdmin  |
| Documentação | Swagger  |
| Monitoramento | Actuator  |
| Cobertura de Testes | Maven  |
| Gerenciador | JaCoCo  |
| Conversão de entidades | MapStruct  |
| Integração entre APIs | FeignClient  |
| Testes de comunicação externa | FeignMock  |
| Testes de Integração | MockMvc  |
| Qualidade | Sonar  |

## Pré-Requisitos

Antes de começar, você precisará ter o Docker instalado em sua máquina.<br>

## Como Rodar

É necessário que você execute o seguinte comando dentro das pastas "docker/rabbitmq" e "docker/database" para subir os serviços em container:
`````
docker-compose up -d
`````
Isso irá subir os serviços de Banco de Dados e RabbitMQ em container.<br>
OBS: PgAdmin não é necessário, apenas caso não tenha acesso a nenhuma plataforma para o PostgreSQL.<br>
Os serviços ficaram disponiveis em:

# [Swagger](http://localhost:8080/swagger-ui/index.html)
`````
http://localhost:8080/swagger-ui/index.html
`````
# [Actuator](http://localhost:8080/actuator)
`````
http://localhost:8080/actuator
`````

# PgAdmin : [localhost:8090](http://localhost:8090) com as credenciais:
`````
user: admin@email.com
senha: root
`````
# RabbitMQ:  [localhost:15672](http://localhost:15672)  com as credenciais:
`````
user: admin
senha: 123456
`````

# JaCoCo
Caso queira ver a cobertura através do Jacoco basta rodar o comando:

`````sh
mvn clean package
`````
Será gerado um arquivo .HTML que pode ser visto no path: ./target/site/jacoco/index.html

## Modelo de Dados
![mapeamentoDB.png](img.png)
Abaixo segue o modelo de dados do sistema:

```plantuml
class Associado {
  id: serial
  nome: varchar(150)
  documento: varchar(14)
  status: varchar(40)
}

class Assembleia {
  id: serial
  inicio: timestamp with time zone
  fim: timestamp with time zone
}

class Pauta {
  id: serial
  descricao: text
  inicio: timestamp with time zone
  fim: timestamp with time zone
}

class Voto {
  id: serial
  pauta_id: integer
  associado_id: integer
  valor: varchar(10)
}

class Assembleia_Pauta {
  assembleia_id: bigint
  pauta_id: bigint
}

class Pauta_Votacao {
  pauta_id: bigint
  votos_id: bigint
}
```

# Considerações Finais

Criei um sistema básico que permite a inserção de votos em Pautas de uma Assembleia, o sistema valida as datas, permitindo apenas que sejam criadas Assembleias e Pautas com datas válidas e a Assembleia deve estar ainda aberta a votação para receber uma pauta. Os votos também só podem ser inseridos por Associados aptos a votar e que ainda não tenham votado na pauta e a mesma ainda deve estar aberta a votação.

Foram inseridas validações e testes unitários e de integração para validação dos métodos.

Usei o `Feign Client` para validação de CPF e também criei a anotação `@CpfOuCnpj` que valida se o campo é um CPF ou CNPJ válido, esta anotação trabalha em conjunto com as anotações do hibernate validator.

Algumas ferramentas foram usadas ou incluídas por boas práticas, um banco H2 em memória para testes foi criado.

Criei uma classe genérica a `MapperGenerics` como um exemplo de uso do genereics do java para evitar código duplicado onde é possível converter DTOs passando o DTO e um método de conversão. Está classe foi utilizada para personalizar `um modelo de paginação` que é enviado ao front.

`Swagger` foi configurado para organização da documentação, cada endpoint pode ter sua própria descrição e grupo de alocação na documentação.

`Jacoco` foi utilizado como métrica de qualidade de cobertura de testes.

O `Actuator` foi configurado para expor todas as informações operacionais do sistema por ser algo didático.

Nas classes de serviços, optei por criar uma interface e uma classe de implementação, injetando nos controllers somente as interfaces, protegendo e isolando métodos mais internos que expõe os repositórios ao package de Services.

##### Serviço de mensageria com RabbitMQ:
Coloquei um exemplo de uso simples, onde me conecto a uma exchange padrão do RabbitMQ, crio uma fila e consumo desta fila, tudo na mesma API, o correto seria dividir as tarefas em outros serviços porém isso iria gerar complexidade para quem analisar o código, e pelo mesmo motivo mantive todas as classes no pacote “rabbitmq” para facilitar a leitura do código e por ser algo didático.
Escolhi o tipo de fila direct pois haveria concorrência na fla de voto e acredito ser o mais indicado nesta situação. Também saliento que a fila de votos é apenas um exemplo, qualquer endpoint poderia ter sido convertido em uma fila, assim sendo toda a API poderia ser facilmente convertida em uma API consumidora, sem endpoints expostos e criado um serviço de mensageria mais avançado, o que creio não era a ideia neste momento.

Por fim, creio que muitas outras questões poderiam ser abordadas, mas tentei demonstrar um pouco de cada situação para expor conhecimento.
Muito Orbigado!