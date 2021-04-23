### General

A REST API for storing information for Categories and Items

### 
Machineries used

- Spring Boot Framework
- IntelliJ IDEA/Java 11
- Tomcat Server
- Apache Maven
- Dev Profile
- Postman
- Postgresql
- pgAdmin 4

### Spring Security

User authentication and authorization to the existing app, using JSON Web Tokens (JWT) to authenticate
requests.

- Only login API endpoint will only return a JWT in response.
- All other requests will use that token for authentication.

### Functionality(endpoints)

Endpoint | Functionality| Access
------------ | ------------- | ------------- 
POST /auth/users/register | Registers a user | PUBLIC
POST /auth/users/login |Logs a user in | PUBLIC
GET /api/categories | Lists all categories | PRIVATE
GET /api/categories/{categoryId} | Gets a single category with the suppled id | PRIVATE
POST /api/categories | Creates a new category | PRIVATE
PUT /api/categories/{categoryId} | Updates a category with the suppled id | PRIVATE
DELETE /api/categories/{categoryId} | Deletes a category with the suppled id | PRIVATE
POST /api/categories/{categoryId}/items | Creates a new item in the given category | PRIVATE
GET /api/categories/{categoryId}/items | List all item in the given category | PRIVATE
PUT /api/categories/{categoryId}/items/{itemId}| Updates a item in the given category | PRIVATE
DELETE /api/categories/{categoryId}/items/{itemId} | Deletes a item in the given category | PRIVATE

### Installation

- Fork and clone the repository.
- Use Postgres or any other DB manager to create a database called “bookstore”
- Open src/main/resources/application-dev.properties and add your username/password if applicable. You can change the default port from 9090 to any of your choosing.
- Run the application. Type localhost:9090/helloworld and that should be an indication if the application is working. Use Postman or any other similar program to perform CRUD with the endpoints in the Endpoint tables

