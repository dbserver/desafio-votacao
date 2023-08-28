# Para subir applicacao
* projeto estará rodando em http://localhost:9000 
* deve ter um postgres instalado local ou com docker e apontar para a porta 5434 , usuario e senha postgres e database com nome postgres

# Desafio Votacao

Foi escolhido a arquitetura mais comum e utilizada no mundo das rest api sendo as camadas de 
service , controller e repository. 

# Swagger 

O corpo das requisições e os endPoints podem ser visto no seguinte endereço , para isso a aplicacao deve estar rodando
: http://localhost:9000/swagger-ui/index.html 

### Sobre o desenvolvimento
 * Persistencia de dados : Utilizado jpa e banco de dados relacional postgres , ambos são muito utilizados juntos e são bons de trabalharem juntos
 * Excecoes : foi utilizado um handler para tratar as excecoes do projeto e retornar em um dto com uma mensagem , assim padroniza e centraliza os erros
 * Resposta da API : sempre retornando DTO e para e para facilitar o mapeamento foi utilizado o modelMapper , que por sua vez ficou como um component para que o spring 
possa fazer a injeção de dependencia e assim se tornar injetavel em qualquer ponto da aplicacao
