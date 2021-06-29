# :speech_balloon: Share+ (nome provisório)
> ```Em desenvolvimento ```

Aplicação para promover troca de conhecimentos e experiências entre os usuários.

# :nerd_face: Como testar

## Em base de Testes
1. Clone este projeto através do comando ```git clone git@github.com:tnicacio/shareable-backend.git```
2. Execute o projeto
    - Caso queira utlizar o Spring Tool Suite:
      - Importe o projeto Maven existente.
      - Rode-o através da opção <strong>Run As Spring Boot App</strong>
3. Em seu browser favorito, navegue para http://localhost:8080/h2-console e execute os seus testes no banco de dados H2

## Utilizando a aplicação na Nuvem
> https://shareable-ifc.herokuapp.com/

# Rotas da API

## :man_technologist: Users

### [GET] /users
Retorna todos os usuários

### [GET] /users/:id
Retorna o usuário de id informado

### [POST] /users
Insere o usuário passado no corpo da requisição

### [PUT] /users/:id
Atualiza os dados do usuário de id informado

### [DELETE] /users/:id
Exclui o usuário de id informado

## :woman_student: Knowledges

### [GET] /knowledges
Retorna todos os conhecimentos cadastrados

### [GET] /knowledges/:id
Retorna o conhecimento de id informado

### [POST] /knowledges
Insere o conhecimento passado no corpo da requisição

### [PUT] /knowledges/:id
Atualiza os dados do conhecimento de id informado

### [DELETE] /knowledges/:id
Exclui o conhecimento de id informado

## :books: Sessions

### [GET] /sessions
Retorna todas as sessões de troca de conhecimento

### [GET] /sessions/:id
Retorna a sessão de id informado

### [POST] /sessions
Insere a sessão passada no corpo da requisição

### [PUT] /sessions/:id
Atualiza os dados da sessão de id informado

### [DELETE] /sessions/:id
Exclui a sessão de id informado

## :gear: Roles

### [GET] /roles
Retorna todos as funções

### [GET] /roles/:id
Retorna a função de id informado

### [POST] /roles
Insere a função passada no corpo da requisição

### [PUT] /roles/:id
Atualiza os dados da função de id informado

### [DELETE] /roles/:id
Exclui a função de id informado

# :computer: Tecnologias utilizadas
- SpringBoot
- PostgreSQL
- JUnit
- Mockito
- OAuth2
- H2 Database
- Heroku
