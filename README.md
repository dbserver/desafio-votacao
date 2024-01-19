# API de Votação

## Objetivo

Prover um sistema simples para votação utilizando os endpoins para que os usuários possam:
- Listar Pautas
- Criar Pautas
- Editar Pautas
- Apagar Pautas
- Abrir Sessao de Votação por período de Tempo.
- Votar na Pauta.
Como proposto basta utilizar os enpoints respectivos para consumir a API e ela entrega os resultados seguinto o padrão Rest.

## Environment

Para este projeto ofi Utilizado **Springboot 3 e Maven e Java 17 **( OpenJDK 64-Bit Server VM (Red_Hat-17.0.9.0.9-4) (build 17.0.9+9, mixed mode, sharing)
) 

**Banco de dados H2**

**Servidor Tomcat-9.0.85**

 
## BANCO DE DADOS
O Banco de dados Utilizado para fins de demonostração é o H2 em arquivo para persistir os dados, e algumas rotinas foram implementados o cache para otimização.
E o projeto cria o banco na raiz do projeto se ele não estiver presente de forma automática.
Para acessar o banco de dados acesse em seu ambiente local pelo navegador: 

**http://localhost:8080/votacao/h2-console/**

usuário: sa
senha: **em branco**

Basta informar o usuario sa e deixar a senha em branco para poder acessar o painel do H2.

## Endpoins:
### 1 -  Listar Pautas:

Endpoint: http://localhost:8080/votacao/api/pautas/

Método: GET.

Resposta esperada (exemplo):

[
    {
        "sessao": {
            "id": 1,
            "pauta": {
                "id": 1,
                "descricao": "Pauta 01"
            },
            "dataAbertura": "2024-01-18T21:39:26.581337",
            "duracaoEmMinutos": 2
        },
        "votosSim": 1,
        "id": 1,
        "descricao": "Pauta 01",
        "votosNao": 0
    },
    {
        "sessao": null,
        "votosSim": 0,
        "id": 2,
        "descricao": "Pauta 02",
        "votosNao": 0
    },
    {
        "sessao": null,
        "votosSim": 0,
        "id": 3,
        "descricao": "Pauta 03",
        "votosNao": 0
    }
]
### Explicação  o comportamento do endpoint de Listar Pautas:
O intuito é que liste as pautas existentes com os dados de data de abertura, duração e votos SIM e Não recebidos, pois a idéia é poder ser transparente e contabilizar todos os votos.

**A não existencia de nenhuma pauta traz um json vazio.** 

## Criar Pauta
Endpoint: http://localhost:8080/votacao/api/pautas/criarpauta

Método: Post

Content-Type: application/json

Body(Json):

**{
  "descricao": "Pauta 03"
}**

Resposta: 

**{
    "mensagem": "Pauta salva com sucesso!",
    "titulo": "Pauta 03",
    "id": 3
}**

O uso deste endpoint basta enviar o Json no Body com o campo descricao e o nome que deseja.


## Editar Pauta

Endpoint: http://localhost:8080/votacao/api/pautas/editpauta/1

Método: Put

Content-type: application/json

Body(Json):

**{
  "descricao": "Pauta 01-bkp"
}**

Resposta:
**{
    "mensagem": "Pauta atualizada com sucesso!",
    "titulo": "Pauta 01-bkp",
    "id": 1
}**

Mensagem de Erro:
**{
    "mensagem": "A pauta não existe no banco de dados."
}**


O uso  basta no endpoint enviar o ID da pauta que deseja editar e no json no campo descrição informar no novo nome que deseja, e não encontrando o ID respectivo informa que a Pauta não existe no Banco de dados.

## Abrir Sessão para Votação

Endpoint: http://localhost:8080/votacao/api/pautas/1/abrir-sessao?minutes=2

Método: Post

Query: minutes

Resultado esperado - Sucesso:

**{
    "mensagem": "Sessão de votação aberta com sucesso."
}**
Mensagem de Erro:

**{
    "mensagem": "Erro ao abrir a sessão de votação."
}**

O uso é passar o ID da pauta no endpoiont e na query minutes especificar a duração em minutos onde o  comportamento esperado é que tenha sucesso e tendo sucesso retorne a mensagem Sessao de votação aberta com sucesso e se tiver uma falha, por exemplo passando um ID inválido infomra a mensagem Erro ao Abrir a sessao de votação, no caso específico não informo o ID da pauta apenas por uma questão de conceito de  arquitetura/segurança.


## Votar

Endpoint: http://localhost:8080/votacao/api/votos/votar


Método: Post

Bosy(JSON)

**{
	"cpf": "008.493.030-60",
	"pauta": "1",
	"voto": "sim"
}**

Mensagem de erro:

**{
    "mensagem": "A sessão de votação para esta pauta está fechada."
}**

O uso deste enpoint para votar no Body envia um json onde tem as chaves do CPF, Pauta é o ID da pauta e o a chave voto que pode receber sim ou não, onde internamente é convertido para true e false.
foi desenvolvido um código para verificar a validade do CPF informado que é checado em cada voto que esteja com a sessão aberta previamente e a mensagem de erro "A Sessão de votação para esta pauta está fechada" informa que precisa ser aberta, onde podem ser abertas quantas sessões necessárias para a mesma pauta e esta é persistida no banco de dados.

## Apagar Pauta

Endpoint: http://localhost:8080/votacao/api/pautas/apagarpauta/4

Método: Delete

Sucesso:

**{
    "mensagem": "Pauta apagada com sucesso!",
    "id": 4
}**

Mensagem de erro:
**{
    "mensagem": "A pauta não existe no banco de dados."
}
**

O uso deste endpoint é para apagar a pauta passando o ID.
