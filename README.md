# E-commerce Analytics

Precisamos desenvolver uma plataforma que recebe e processa logs por meio de uma API REST, para fazer análises e visualizações em cima desses dados.
Estamos tendo um grande número de acessos então você precisa desenvolver uma solução escalável, confiável e performática.

--- 

# Endpoints

## /access [POST]

Cria um novo registro de evento de acesso. 
Deve ser passado como body da requisicao o log de acesso. 
Formato: 
```
URL_PATH|CALLER_COUNTRY|TIMESTAMP|ACTION
```
Exemplo: 
```
/products|Brazil|1037825323259|GET
```

--- 

## /access [GET]

Retorna todos os logs cadastrados no sistema no formato padrão. `URL_PATH|CALLER_COUNTRY|TIMESTAMP|ACTION`

--- 

## /top [GET]

Retorna as <num> paginas mais acessadas no país <country>.
Dado num=3 e country=Brazil
o retorno seriam as 3 páginas mais acessadas no país Brazil

| Parâmetro | Obrigatório | Default | Exemplo        |
| --------- | ----------- | ------- | -------------- |
| country   | não         | Todos   | country=Brazil |
| num       | não         | 5       | num=3          |

--- 

## /highestaccessvolume [GET]

| Parâmetro | Obrigatório | Default | Exemplo |
| --------- | ----------- | ------- | ------- |
| num       | não         | 1       | num=3   |

--- 

## /mostaandleastccessed [GET]

Páginas mais e menos acessadas.
Com envio do parâmetro `date`, são retornadas a página mais acessada e a menos acessada naquele dia.

| Parâmetro | Obrigatório | Default | Exemplo         |
| --------- | ----------- | ------- | --------------- |
| date      | não         | Todas   | date=01/10/1998 |

--- 

# How to run

```mvn spring-boot:run```