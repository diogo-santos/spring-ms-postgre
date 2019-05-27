# Spring Boot with PostgreSQL
## What was build
Demo project for RESTful Spring Boot with PostgreSQL

## What you'll need
PostgreSQL @see: application.properties

Java 1.8

Maven 3.0+

## Instructions
Import the project from GitHub

Run spring-ms-postgresql app
```
mvn clean package && java -jar target/spring-ms-postgresql-0.0.1.jar
```
## Test the App
Now that the app is running, visit http://localhost:8080/product to see the list of products

To Create a new product, do a POST

http POST http://localhost:8080/product  {"description": "a nice product","price": "100.00"}

To Delete a product, do a DELETE
 
http DELETE http://localhost:8080/product/1  
