
# Desafio Votação

API desenvolvida para concluir o desafio de votação

## Instalação

Realize o download do projeto com o comando:

```bash
git clone https://github.com/renan-auer/desafio-votacao
```

Foi utilizado banco de dados MySql para o desenvolvimento. Caso deseje utilizar outro banco será necessário alterar as propriedades no arquivo:


```bash
  \src\main\resources\application.properties
```

Instruções de como configurar a aplicação para outros bancos de dados podem ser vistas aqui: https://howtodoinjava.com/spring-boot2/datasource-configuration/.

Após executar o projeto, o Spring JPA irá criar as tabelas necessárias na base de dados e em seguinda a API está disponível no seguinte endereço:

```bash
    http://localhost:8080/v1
```

Além disso, uma documentação com Swagger estará disponível no endereço:

```bash
  http://localhost:8080/swagger-ui.html#
```

## Rodando os testes

Para rodar os testes, rode o seguinte comando

```bash
  nvm test
```

