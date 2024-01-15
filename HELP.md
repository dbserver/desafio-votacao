# Desafio voto

Projeto construido para o desafio do sicred

## 🚀 Começando

Essas instruções permitirão que você obtenha uma cópia do projeto em operação na sua máquina local para fins de desenvolvimento e teste.

Consulte **[Implantação](#-implanta%C3%A7%C3%A3o)** para saber como implantar o projeto.

### 📋 Pré-requisitos

```
jdk 17
maven
docker
```

### 🔧 Instalação

Uma série de exemplos passo-a-passo que informam o que você deve executar para ter um ambiente de desenvolvimento em execução.

Diga como essa etapa será:

```
docker-compose up -d
mvn clean install
mvn spring-boot:run
```

## ⚙️ Executando os testes

Além de rodar os testes do maven como exemplificado abaixo.

```
mvn clean install
```
Você também pode rodar os testes via postman clicando no botão abaixo

[<img src="https://run.pstmn.io/button.svg" alt="Run In Postman" style="width: 128px; height: 32px;">](https://app.getpostman.com/run-collection/4262576-d1097e65-d685-4240-9089-73974c13c1ad?action=collection%2Ffork&source=rip_markdown&collection-url=entityId%3D4262576-d1097e65-d685-4240-9089-73974c13c1ad%26entityType%3Dcollection%26workspaceId%3D7f8b9790-cf5f-4faa-af64-4daddeb89665)

Observação:

- caso não consiga rodar via postman run, existe uma collection salva no projeto em **resources -> static**

## 📦 Implantação

Ao rodar os comando maven acima, será gerado um pacote jar na pasta target dentro do projeto que poderá deployado em servidores web.

## 🛠️ Construído com

* [Spring](https://start.spring.io/) - O framework web usado.
* [Maven](https://maven.apache.org/) - Gerente de Dependência.
* [Docker](https://www.docker.com/) -  container usado para o mysql e redis.
* [Redis](https://redis.io/) - Cache distribuido.
* [Mysql](https://www.mysql.com/) - Banco de dados.
* [Flyway](https://flywaydb.org/) - Versionador de banco de dados nas migrations.

## 📌 Versão

No path da Api estamos usando o prefix v1 para controle de versão seguido do recurso a ser disponibilizado. Para as versões disponíveis, observe as [tags neste repositório](https://github.com/ferreiralisson/desafio-votacao/tags).

## ✒️ Autor

* **Alisson Ferreira** - [perfil](https://github.com/ferreiralisson)

---

## Informações adicionais

* **Api Fake para validação de cpf** - [perfil](https://github.com/ferreiralisson/validator-api)

---
