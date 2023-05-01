# Desafio Votação 



API de Gerenciamento de votos.



### Funcionalidades

- [x] Cadastrar Associados
- [x] Cadastrar Associado como votante de uma Pauta
- [x] Cadastrar Pautas a serem votadas
- [x] Listar todas as Pautas
- [x] Atualizar Pauta
- [x] Buscar Pauta
- [x] Deletar Pauta
- [x] Cadastrar Votos em uma Pauta
- [x] Resultado da votação em uma Pauta



### Tecnologias



| Uso                         | Tecnologia                                                   |
| --------------------------- | ------------------------------------------------------------ |
| Linguagem de Programação    | Java 17                                                      |
| Banco de Dados              | PostgreSQL                                                   |
| Framework                   | Spring Boot                                                  |
| Interface para consultar BD | DBeaver                                                      |
| Documentação                | Swagger ([springdoc-openapi v2.1.0](https://springdoc.org/v2/)) |
| Testes Unitários            | jUnit e Mockito                                              |



### Como rodar o projeto



Antes de rodar o projeto é necessário ter instalado e configurado na máquina o Banco de Dados. Outra opção é fazer a instalação do Docker e  configurar um container para subir um BD.

(OBS: Por padrão, o Postgres roda na porta 5432, mas isso fica disponível apenas dentro do container, para permitir que ele possa ser acessado pela máquina local, precisamos um `mapping` da porta interna, para porta externa.).

O `application.properties` está configurado da seguinte forma:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=postgres
spring.datasource.password=1234
```

Mas se preferir você pode criar um banco com nome, usuário e senha da sua preferencia e alterar essas configurações.



##### Swagger

```
http://localhost:8080/swagger-ui/index.html
http://localhost:8080/v3/api-docs
```



### Modelo de Dados

![](https://github.com/systemagic-91/desafio-votacao/blob/main/src/main/resources/static/Modelo%20de%20Dados.png)
