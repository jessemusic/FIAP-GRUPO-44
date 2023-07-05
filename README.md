# FIAP-GRUPO-44
Controlador de gastos de eletrodomésticos


![img_2.png](img_2.png)

<h1 align="center">
  Cadastro de Pessoas
</h1>

<p align="center">
 https://github.com/jessemusic/FIAP-GRUPO-44 
</p>

API para gerenciar pessoas (CRUD) que faz parte do primeiro Módulo (https://github.com/jessemusic/FIAP-GRUPO-44 ).

O projeto foi elaborado PÓS TECH e Apresentação do Tech Challenge ).

## Tecnologias

- [Spring Boot](https://spring.io/projects/spring-boot)


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
  Cadastro de Eletrodomesticos
</h1>

<p align="center">
 https://github.com/jessemusic/FIAP-GRUPO-44 
</p>

API para gerenciamento de eletrodomesticos. Ao consumir esta api o desenvolvedor conseguirá realizar a criação, leitura,atualização,leitura e deleção(CRUD) dos registros de eletrodomesticos. 



## API Endpoints


Lista de eletrodomesticos

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
            "voltagem": "Bivolt",
            "tensao": 500.0,
            "consumo": 12.0
        },
        {
            "id": 2,
            "nome": "Notebook",
            "modelo": "inspiron 14",
            "marca": "Dell",
            "voltagem": "Bivolt",
            "tensao": 500.0,
            "consumo": 12.0
        },
        {
            "id": 3,
            "nome": "Notebook",
            "modelo": "inspiron 14",
            "marca": "Dell",
            "voltagem": "Bivolt",
            "tensao": 500.0,
            "consumo": 12.0
        },
        {
            "id": 4,
            "nome": "Notebook",
            "modelo": "inspiron 14",
            "marca": "Dell",
            "voltagem": "Bivolt",
            "tensao": 500.0,
            "consumo": 12.0
        },
        {
            "id": 5,
            "nome": "Notebook",
            "modelo": "inspiron 14",
            "marca": "Dell",
            "voltagem": "Bivolt",
            "tensao": 500.0,
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
    "voltagem": "Bivolt",
    "tensao": 500
}
```

- GET /eletrodomesticos/{id}
```
http://localhost:8080/eletrodomesticos/2{
    "id": 2,
    "nome": "Notebook",
    "modelo": "inspiron 14",
    "marca": "Dell",
    "voltagem": "Bivolt",
    "tensao": 500.0,
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
    "voltagem": "Bivolt",
    "tensao": 800.0
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