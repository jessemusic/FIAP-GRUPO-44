# FIAP-GRUPO-44
Controlador de gastos de eletrodomésticos
## Introdução:

O presente projeto tem como principio atender as diretrizes solicitadas pela pós graduação da FIAP no desenvolvimento do Tech Chellenge. Em suma o projeto visa desenvolver uma solução que provisione o monitoramento de eletrodoméstico identificando aparelhos com maior consumo, gerenciamento do consumo de energia em tempo real gerando economia para usuario final.
Para atender a demanda em sua complitude o desenvolvimento do projeto se dará por etapas


![img_2.png](img_2.png)

## Descrição da primeira etapa:

Na primeira etapa foi proposto o desenvolvimento de tres APIs que promovam a manipulação de registros cadastrados para Pessoas, eletrodomésticos e Endereços. As APIs proporcionam a listagem, visualização, criação, atualização e deleção dos registros. Cada operação tem sua tratativa de erros correspondente
<h1 align="center">
  Desenvolvimento das APIs
</h1>

## Tecnologias

- [Spring Boot](https://spring.io/projects/spring-boot):Modulo derivado do Spring Framework que facilita desenvolvimento de aplicações java implementando injeção e inversão de dependencias
- [H2](https://github.com/h2database/h2database/releases/download/version-2.2.220/h2.pdf): Gerenciador de banco de dados relacional
- [Postman](https://learning.postman.com/docs/developer/postman-api/intro-api/): Ferramenta destinada a desenvolvedores que possibilita testar chamadas API e gerar documentação de forma iterativa.Foi usado neste projeto para gerar collections e realizar teste de chamadas aos endpoints;
- [Tortoise](https://tortoisegit.org/docs/tortoisegit/): Ferramenta gerencial que facilita manipulação de projetos em GIT. Foi usado neste projeto para resolução de conflitos.
- [Sourcetree](https://confluence.atlassian.com/get-started-with-sourcetree): Assim como o Tortoise é uma ferramenta gerencial para facilitar o desenvolvimento de projetos em Git, no entanto possui uma interface mais receptivel e navegabilidade facilitada.Foi usado neste projeto paa navegação e criação de ramos.
## Práticas adotadas


- Uso de DTOs para a API
- Injeção de Dependências


## Como Executar

### Localmente
- Clonar repositório git
- Construir o projeto:
```
./mvnw clean package
```
- Executar:


A API poderá ser acessada em [localhost:8080](http://localhost:8080)

O Swagger poderá ser visualizado em [localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)


<h1 align="center">
  API Pessoas
</h1>

<p align="center">
 https://github.com/jessemusic/FIAP-GRUPO-44 
</p>

API para gerenciar pessoas (CRUD) que faz parte do primeiro Módulo (https://github.com/jessemusic/FIAP-GRUPO-44 ).

O projeto foi elaborado PÓS TECH e Apresentação do Tech Challenge ).


## API Endpoints

Para fazer as requisições HTTP abaixo, foi utilizada a ferramenta [http](https://web.postman.co/workspaces):

Lista de pessoas

- GET /pessoas
```
http GET http://localhost:8080/pessoas

HTTP/1.1 200 OK
Content-Length: 129
Content-Type: application/json

{
    "content": [
        {
            "id": 1,
            "nome": "Fernanda",
            "sobrenome": "Alcantara",
            "dataNascimento": "1971-01-21",
            "sexo": "F"
        },
        {
            "id": 2,
            "nome": "Fernanda",
            "sobrenome": "Alcantara",
            "dataNascimento": "1971-01-21",
            "sexo": "F"
        },
        {
            "id": 3,
            "nome": "Fernanda",
            "sobrenome": "Alcantara",
            "dataNascimento": "1971-01-21",
            "sexo": "F"
        },
        {
            "id": 4,
            "nome": "Fernanda",
            "sobrenome": "Alcantara",
            "dataNascimento": "1971-01-21",
            "sexo": "F"
        },
        {
            "id": 5,
            "nome": "Fernanda",
            "sobrenome": "Alcantara",
            "dataNascimento": "1971-01-21",
            "sexo": "F"
        },
        {
            "id": 6,
            "nome": "Fernanda",
            "sobrenome": "Alcantara",
            "dataNascimento": "1971-01-21",
            "sexo": "F"
        }
    ],
    "pageable": {
        "sort": {
            "empty": true,
            "sorted": false,
            "unsorted": true
        },
        "offset": 0,
        "pageSize": 10,
        "pageNumber": 0,
        "paged": true,
        "unpaged": false
    },
    "last": true,
    "totalElements": 6,
    "totalPages": 1,
    "size": 10,
    "number": 0,
    "sort": {
        "empty": true,
        "sorted": false,
        "unsorted": true
    },
    "first": true,
    "numberOfElements": 6,
    "empty": false
}

```
CADASTRO DE PESSOAS
- POST /pessoas
```
http POST http://localhost:8080/pessoas

HTTP/1.1 200 OK
Content-Length: 129
Content-Type: application/json

{
     "nome": "Fernanda",
     "sobrenome": "Alcantara",
     "dataNascimento": "1971-01-21",
     "sexo": "F"
}
```

- GET /pessoas/{id}
```
http://localhost:8080/pessoas/2
HTTP/1.1 200 OK
Content-Length: 129
Content-Type: application/json

{
    "id": 2,
    "nome": "Fernanda",
    "sobrenome": "Rancho fundo Mauá",
    "dataNascimento": "1971-01-21",
    "sexo": "F"
}
```

- PUT /pessoas/1
```
tp://localhost:8080/pessoas/1
HTTP/1.1 200 OK
Content-Type: application/json
transfer-encoding: chunked

{
    "nome": "James",
    "sobrenome": null,
    "dataNascimento": "1971-01-21",
    "sexo": "M"
}
```
Alteração de campo único por exemplo:
- PATCH /pessoas/{id}
```
PATCH http://localhost:8080/pessoas/1
HTTP/1.1 200 OK
Content-Length: 142
Content-Type: application/json

{

   "sobrenome": "Rancho fundo Mauá"
           
}
 Retorna a alteração 
{
    "id": 1,
    "nome": "Fernanda",
    "sobrenome": "Rancho fundo Mauá",
    "dataNascimento": "1971-01-21",
    "sexo": "F"
}
```

Deletando uma pessoa:
- DELETE /pessoas/{id}
```
DELETE http://localhost:8080/pessoas/1
HTTP/1.1 204 No Content
Content-Length: 142
Content-Type: application/json

```

<h1 align="center">
  API Eletrodomésticos
</h1>

<p align="center">
 https://github.com/jessemusic/FIAP-GRUPO-44 
</p>

API para gerenciamento de eletrodomésticos. Ao consumir esta api o desenvolvedor conseguirá realizar a criação, leitura,atualização,leitura e deleção(CRUD) dos registros de eletrodomésticos. 



## API Endpoints


Lista de eletrodomésticos

- GET /eletrodomesticos
```
http GET http://localhost:8080/eletrodomesticos

{
    "content": [
        {
            "id": 1,
            "nome": "Notebook",
            "modelo": "inspiron 14",
            "marca": "Dell",
            "tensao": "Bivolt",
            "potencia": 500.0,
            "consumo": 12.0
        },
        {
            "id": 2,
            "nome": "Notebook",
            "modelo": "inspiron 14",
            "marca": "Dell",
            "tensao": "Bivolt",
            "potencia": 500.0,
            "consumo": 12.0
        },
        {
            "id": 3,
            "nome": "Notebook",
            "modelo": "inspiron 14",
            "marca": "Dell",
            "tensao": "Bivolt",
            "potencia": 500.0,
            "consumo": 12.0
        },
        {
            "id": 4,
            "nome": "Notebook",
            "modelo": "inspiron 14",
            "marca": "Dell",
            "tensao": "Bivolt",
            "potencia": 500.0,
            "consumo": 12.0
        },
        {
            "id": 5,
            "nome": "Notebook",
            "modelo": "inspiron 14",
            "marca": "Dell",
            "tensao": "Bivolt",
            "potencia": 500.0,
            "consumo": 12.0
        }
    ],
    "pageable": {
        "sort": {
            "empty": true,
            "sorted": false,
            "unsorted": true
        },
        "offset": 0,
        "pageNumber": 0,
        "pageSize": 10,
        "unpaged": false,
        "paged": true
    },
    "last": true,
    "totalElements": 5,
    "totalPages": 1,
    "size": 10,
    "number": 0,
    "sort": {
        "empty": true,
        "sorted": false,
        "unsorted": true
    },
    "first": true,
    "numberOfElements": 5,
    "empty": false
}
```
CADASTRO DE ELETRODOMESTICOS
- POST /eletrodomesticos
```
http POST http://localhost:8080/eletrodomesticos
HTTP/1.1 201 CREATED
Content-Length: 129
Content-Type: application/json

{
    "nome": "Notebook",
    "modelo": "inspiron 14",
    "marca":"Dell",
    "tensao": "Bivolt",
    "potencia": 500.0
}
```

- GET /eletrodomesticos/{id}
```
http://localhost:8080/eletrodomesticos/2{
    "id": 2,
    "nome": "Notebook",
    "modelo": "inspiron 14",
    "marca": "Dell",
    "tensao": "Bivolt",
    "potencia": 500.0,
    "consumo": 12.0
}
```

- PUT /eletrodomesticos/2
```
http://localhost:8080/eletrodomesticos/1
HTTP/1.1 200 OK
Content-Length: 129
Content-Type: application/json
{
    "nome": "Notebook",
    "modelo": "inspiron 14",
    "marca": "Dell",
    "tensao": "Bivolt",
    "potencia": 800.0
}
```


Deletando uma eletrodomestico:
- DELETE /eletrodomesticos/{id}
```
DELETE http://localhost:8080/eletrodomesticos/2
HTTP/1.1 204 No Content
Content-Length: 142
Content-Type: application/json

```


API para gerenciamento de Endereços. Ao consumir esta api o desenvolvedor conseguirá realizar a criação, leitura,atualização e deleção(CRUD) dos registros de enderelos. 

<h1 align="center">
  API Eletrodomésticos
</h1>

## API Endpoints

#LISTA DE ENDEREÇOS

```
GET http://localhost:8080/buscar-todos?pagina=0&tamanho=10

RESPONSE
[
    {
        "id": 1,
        "rua": "Rua dos bravos",
        "numero": 8898,
        "bairro": "Presidente altino",
        "cidade": "Embu das artes",
        "estado": "SP"
    },
    {
        "id": 3,
        "rua": "Rua dos bravos",
        "numero": 990,
        "bairro": "Capão Redondo",
        "cidade": "São Paulo",
        "estado": "SP"
    },
    {
        "id": 4,
        "rua": "Rua do shoping campo limpo",
        "numero": 990,
        "bairro": "Capão Redondo",
        "cidade": "São Paulo",
        "estado": "SP"
    },
    {
        "id": 5,
        "rua": "Presidente altino",
        "numero": 8054,
        "bairro": "Osasco",
        "cidade": "São Paulo",
        "estado": "SP"
    },
    {
        "id": 6,
        "rua": "Lapa",
        "numero": 7867,
        "bairro": "Osasco",
        "cidade": "São Paulo",
        "estado": "SP"
    }
]

```
LISTAR POR ID


```
GET http://localhost:8080/buscar-getID?id=2

RESPONSE:
{
    "id": 4,
    "rua": "Rua do shoping campo limpo",
    "numero": 990,
    "bairro": "Capão Redondo",
    "cidade": "São Paulo",
    "estado": "SP"
}


```
#CADASTRAR ENDEREÇOS

```
POST http://localhost:8080/salvar

HTTP/1.1 201 CREATED
Content-Length: 129
Content-Type: application/json

REQUEST BODY
{
   "rua":"Lapa",
   "numero":"7867",
   "bairro":"Osasco",
   "cidade":"São Paulo",
   "estado":"SP"
}


RESPONSE

{
    "id": 7,
    "rua": "Lapa",
    "numero": 7867,
    "bairro": "Osasco",
    "cidade": "São Paulo",
    "estado": "SP"
}


```
#ATUALIZAR ENDEREÇO

```
PUT http://localhost:8080/atualizar/3

REQUEST BODY
{
   "rua":"Rua dos Imbondeiros",
   "numero":"1000",
   "bairro":"Urbanização nova vida",
   "cidade":"Luanda",
   "estado":"LD"
}

RESPOSE

{
    "id": 3,
    "rua": "Rua dos Imbondeiros",
    "numero": 1000,
    "bairro": "Urbanização nova vida",
    "cidade": "Luanda",
    "estado": "LD"
}

```
DELETANDO ENDEREÇO
```

http://localhost:8080/apagar/10

Removido o endereço de ID: 10