# API Spec

## Authentication

All API must use this authentication

Request :
- Header :
    - X-Api-Key : "your secret api key"


# USER API SPEC
## Create User

Request :
- Method : POST
- Endpoint : `/api/user`
- Header :
    - Content-Type: application/json
    - Accept: application/json
- Body :

```json 
{
    "id_user" : "string, unique",
    "user_ldap" : "string",
    "nama" : "String",
    "unit" : "string",
    "id_role": "String",
    "status": "String"
}
```

Response :

```json 
{
    "code" : "number",
    "status" : "string",
    "data" : {
      "idUser":"String",
      "userLdap":"String",
      "nama":"String",
      "idRole":"String",
      "unit":"String",
      "status":"String",
      "createdAt":"Date",
      "lastLogin":"Date"
     }
}
```

## Get User

Request :
- Method : GET
- Endpoint : `/api/user/{id_user}`
- Header :
    - Accept: application/json

Response :

```json 
{
        "code" : "number",
        "status" : "string",
        "data" : {
          "idUser":"String",
          "userLdap":"String",
          "nama":"String",
          "idRole":"String",
          "unit":"String",
          "status":"String",
          "createdAt":"Date",
          "lastLogin":"Date"
     }
  
}
```

## Update User

Request :
- Method : PUT
- Endpoint : `/api/user/{id_user}`
- Header :
    - Content-Type: application/json
    - Accept: application/json
- Body :

```json 
{
    "name" : "string",
    "unit" : "string",
    "status" : "integer"
}
```

Response :

```json 
{
    "code" : "number",
    "status" : "string",
    "data" : {
         "id" : "string, unique",
         "name" : "string",
         "price" : "long",
         "quantity" : "integer",
         "createdAt" : "date",
         "updatedAt" : "date"
     }
}
```

## List User

Request :
- Method : GET
- Endpoint : `/api/user`
- Header :
    - Accept: application/json
- Query Param :
    - size : number,
    - page : number

Response :

```json 
{
    "code" : "number",
    "status" : "string",
    "data" : [
        {
            "id" : "string, unique",
            "name" : "string",
            "price" : "long",
            "quantity" : "integer",
            "createdAt" : "date",
            "updatedAt" : "date"
        },
        {
            "id" : "string, unique",
            "name" : "string",
            "price" : "long",
            "quantity" : "integer",
            "createdAt" : "date",
            "updatedAt" : "date"
         }
    ]
}
```

## Delete User

Request :
- Method : DELETE
- Endpoint : `/api/user/{id_user}`
- Header :
    - Accept: application/json

Response :

```json 
{
    "code" : "number",
    "status" : "string"
}
```
# ROLE API SPEC

## Create Role

Request :
- Method : POST
- Endpoint : `/api/role`
- Header :
  - Content-Type: application/json
  - Accept: application/json
- Body :

```json 
{
    "idRole":"String",
    "namaRole":"String",
    "idMenu":"String"
}
```

Response :

```json 
{
    "code" : "number",
    "status" : "string",
    "data" : {
      "idRole":"String",
      "namaRole":"String",
      "idMenu":"String"
     }
}
```

## Get Role

Request :
- Method : GET
- Endpoint : `/api/role/{id_role}`
- Header :
  - Accept: application/json

Response :

```json 
{
        "code" : "number",
        "status" : "string",
        "data" : {
          "idRole":"String",
          "namaRole":"String",
          "idMenu":"String"
     }
  
}
```

## Update Role

Request :
- Method : PUT
- Endpoint : `/api/role/{id_role}`
- Header :
  - Content-Type: application/json
  - Accept: application/json
- Body :

```json 
{
    "name" : "string",
    "unit" : "string",
    "status" : "integer"
}
```

Response :

```json 
{
    "code" : "number",
    "status" : "string",
    "data" : {
         "id" : "string, unique",
         "name" : "string",
         "price" : "long",
         "quantity" : "integer",
         "createdAt" : "date",
         "updatedAt" : "date"
     }
}
```

## List User

Request :
- Method : GET
- Endpoint : `/api/user`
- Header :
  - Accept: application/json
- Query Param :
  - size : number,
  - page : number

Response :

```json 
{
    "code" : "number",
    "status" : "string",
    "data" : [
        {
            "id" : "string, unique",
            "name" : "string",
            "price" : "long",
            "quantity" : "integer",
            "createdAt" : "date",
            "updatedAt" : "date"
        },
        {
            "id" : "string, unique",
            "name" : "string",
            "price" : "long",
            "quantity" : "integer",
            "createdAt" : "date",
            "updatedAt" : "date"
         }
    ]
}
```

## Delete User

Request :
- Method : DELETE
- Endpoint : `/api/user/{id_user}`
- Header :
  - Accept: application/json

Response :

```json 
{
    "code" : "number",
    "status" : "string"
}
```
