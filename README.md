# Instruções
- Recomendo que tenha docker para executar a base de dados. Caso não seja possível basta criar uma base e ajustar as configurações localizadas em application.properties de acordo com a base criada.
- Em resources, existe o arquivo init.sql. Com ele é possível criar as tabelas e sequences necessárias.
- Postman para enviar as requisições
- Existe um arquivo no repositório chamado docker-compose para criação do container do banco de dados
- Orientação para subir container docker :
  - Ir até a pasta do projeto
  - docker compose up -d
- Em src/main/resources/postman foi colocada uma coleção para auxiliar na chamada dos métodos

## Detalhes do projeto
- O servidor está sendo executado na porta 8081, caso tenha necessidade de alterar, basta modificar o campo server.port em application.properties para a porta desejada.
- Link documentação: http://localhost:8081/swagger-ui/index.html

### Versionamento
- O versionamento pode ser controlado por branchs, sendo cada nova branch uma versão de API diferente. Para que seja modificado na URL, é necessário a cada branch alterar a constante VERSION em VersionApi.
