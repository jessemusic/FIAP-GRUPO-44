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
            "nome": "ABRIDOR/AFIADOR",
            "modelo": "MultiSharp",
            "marca": "Philips",
            "tensao": "110v",
            "potencia": 135.0,
            "usoDiasEstimados": 10,
            "usoDiarioEstimado": 5,
            "consumoDiario": 0.011249999955,
            "consumoMensal": 0.11249999955,
            "custoDiario": 0.0101249999595,
            "custoMensal": 0.101249999595,
            "idPatchCategoria": 6
        },
        {
            "id": 2,
            "nome": "AFIADOR DE FACAS",
            "modelo": "BladeSharp",
            "marca": "Philips",
            "tensao": "110v",
            "potencia": 20.0,
            "usoDiasEstimados": 5,
            "usoDiarioEstimado": 30,
            "consumoDiario": 0.01,
            "consumoMensal": 0.05,
            "custoDiario": 0.009000000000000001,
            "custoMensal": 0.045000000000000005,
            "idPatchCategoria": 6
        },
        {
            "id": 3,
            "nome": "APARELHO DE SOM3 EM 1",
            "modelo": "SoundFusion 3-in-1",
            "marca": "JBL",
            "tensao": "110v",
            "potencia": 80.0,
            "usoDiasEstimados": 20,
            "usoDiarioEstimado": 180,
            "consumoDiario": 0.24,
            "consumoMensal": 4.8,
            "custoDiario": 0.216,
            "custoMensal": 4.32,
            "idPatchCategoria": 9
        },
        {
            "id": 4,
            "nome": "APARELHO DE SOM PEQUENO",
            "modelo": "MiniTunes E-100",
            "marca": "JBL",
            "tensao": "110v",
            "potencia": 20.0,
            "usoDiasEstimados": 30,
            "usoDiarioEstimado": 240,
            "consumoDiario": 0.08,
            "consumoMensal": 2.4,
            "custoDiario": 0.07200000000000001,
            "custoMensal": 2.16,
            "idPatchCategoria": 9
        },
        {
            "id": 5,
            "nome": "AQUECEDOR DE AMBIENTE",
            "modelo": "HeatWave ProHeat",
            "marca": "Daikin",
            "tensao": "220V",
            "potencia": 1550.0,
            "usoDiasEstimados": 15,
            "usoDiarioEstimado": 480,
            "consumoDiario": 12.4,
            "consumoMensal": 186.0,
            "custoDiario": 11.16,
            "custoMensal": 167.4,
            "idPatchCategoria": 3
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
        "pageSize": 5,
        "unpaged": false,
        "paged": true
    },
    "last": false,
    "totalElements": 89,
    "totalPages": 18,
    "size": 5,
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

Possiveis filtros;
- - **pagina**:Parametro não obrigatório que define o numero da pagina que o usuário deseja acessar
- - **tamanho**: Parametro não obrigatório que define a quantidade de itens que serão retornados pela listagem
- - **nome**: Parametro não obrigatório que filtra a lista de eletrodomésticos por nome;
- - **marca**:Parametro não obrigatório que filtra a lista de eletrodoméstico por marca;
- - **modelo**:Parametro não obrigatório que filtra a lista de eletrodoméstico por modelo;
- - **tensao**:Parametro não obrigatório que filtra a lista de eletrodoméstico por tensão (Ex:110v,220v ou Bivolt);
- - **Categoria**:Parametro não obrigatório que filtra a lista de eletrodoméstico por categoria (IDentificador da categoria).Exemplo 1,2,6,8;

* GET /eletrodomesticos/{id}
```
http://localhost:8080/eletrodomesticos/10

 {
    "id": 10,
    "nome": "AR-CONDICIONADO 15.000 BTU",
    "modelo": "15.000 BTU",
    "marca": "Daikin",
    "tensao": "BIVOLT",
    "potencia": 2000.0,
    "usoDiasEstimados": 30,
    "usoDiarioEstimado": 480,
    "consumoDiario": 16.0,
    "consumoMensal": 480.0,
    "custoDiario": 14.4,
    "custoMensal": 432.0,
    "idPatchCategoria": 3
}
```

* GET /eletrodomesticos/aleatorios
```
http://localhost:8080/eletrodomesticos/aleatorios

[
	{
		"id": 31,
		"nome": "CORTADOR DE GRAMA PEQUENO",
		"modelo": "GR1000",
		"marca": "Black+Decker",
		"tensao": "110v",
		"potencia": 500.0,
		"usoDiasEstimados": 2,
		"usoDiarioEstimado": 120,
		"consumoDiario": 1.0,
		"consumoMensal": 2.0,
		"custoDiario": 0.9,
		"custoMensal": 1.8,
		"idPatchCategoria": 1
	},
	{
		"id": 17,
		"nome": "BOILER 200 a 500 L",
		"modelo": "200 a 500 L",
		"marca": "KSB",
		"tensao": "110v",
		"potencia": 3000.0,
		"usoDiasEstimados": 30,
		"usoDiarioEstimado": 360,
		"consumoDiario": 18.0,
		"consumoMensal": 540.0,
		"custoDiario": 16.2,
		"custoMensal": 486.0,
		"idPatchCategoria": 2
	}
]
```
- - Retorna uma lista coom um numero aleatório entre um a doze eletrodomésticos de categoriaas diferentes


CADASTRO DE ELETRODOMESTICOS
* POST /eletrodomesticos

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
ATUALIZAÇÃO DE ELETRODOMÉSTICOS:

- PUT /eletrodomesticos/9
```
http://localhost:8080/eletrodomesticos/9
HTTP/1.1 200 OK
Content-Length: 129
Content-Type: application/json

{
    "nome": "AR-CONDICIONADO 12.000 BTU",
    "modelo": " 12.000 BTU",
    "marca": "Daikin",
    "tensao": "BIVOLT",
    "potencia": 1450.0,
    "usoDiasEstimados": 30,
    "usoDiarioEstimado": 480,
    "idPatchCategoria": 3
}
```
- PATCH: /eletrodomesticos/9
```
http://localhost:8080/eletrodomesticos/9
HTTP/1.1 200 OK
Content-Length: 129
Content-Type: application/json

{
    "nome": "AR-CONDICIONADO 12.000 BTU"
}
```
DELEÇÃO DE ELETRODOMÉSTICO
- DELETE /eletrodomesticos/{id}
```
DELETE http://localhost:8080/eletrodomesticos/9
HTTP/1.1 204 No Content
Content-Length: 142
Content-Type: application/json

```
## Parametos de requisição:
| Parametro        |  Tipo  |       Atribuição                    |   Exemplo   |
|:-----------------|:------:|:-----------------------------------|:------------|
| nome             | String |  nome do eletrodoméstico            | Computador  |
| marca            | String |  Marca do eletrodoméstico           | Sansung     |
| modelo           | String |  Modelo do eletrodoméstico          | Galaxy Book |
| tensão           | String |  Tensão do dispostivo               | 200         |
| usoDiasEstimados |  Long  |  Numero de dias de uso              | 15          |
|usoDiarioEstimado |  Long  |  Tempo de uso por dia em minutos    | 180         |
| idPatchCategoria |  Long  |  Categoria do eletrodoméstico       | 1 (Limpeza) |
| consumoDiario    | Double |  Consumo diário do dispositivo em Kw/h | -           |
| consumoMensal    | Double |  Consumo mensal do dispositivo em Kw/h | -           |
| custoDiario      | Double |  Custo de consumo diário em R$      | -           |
| custoMensal      | Double |  Custo de consumo mensal em R$      | -           |

**CATEGORIAS**
1. Acessório de aquário
2. Conforto térmico
3. Construção
4. Cucção
5. Culinária
6. Entretenimento
7. Hidráulica
8. Higiene pessoal
9. Iluminação
10. Lavanderia
11. Limpeza
12. Processador de alimentos
13. Refrigerador
14. Tecnologia
15. Costura


- **Cálculos de consumo/custo diário/mensal**:Os calculos de consumo diários e mansais assim como o calculo de custos são efetuados a partir dos dados enviados pelos parametros usoDiasEstimados que são o numero de dias em que o dispositivo foi usado e usoDiarioEstimado que é o tempo em minutos que o dispositivo se manteve ativo no dia
<p align="center">
  consumoDiário (KW/H) = potencia * (usoDiario / 60) / 1000;
</p>
<p align="center">
  consumoMensal (KW/H) = consumoDiario *  usoDiasEstimados;
</p>
<p align="center">
  custoDiario R$: consumoDiario * 0.9 (Tarifa usado como base de cálculo)
</p>
<p align="center">
  custoMensal R$: consumoDiario * usoDiasEstimados
</p>

<h1 align="center">
  API ENDEREÇOS
</h1>

API para gerenciamento de Endereços. Ao consumir esta api o desenvolvedor conseguirá realizar a criação, leitura,atualização e deleção(CRUD) dos registros de enderelos.


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