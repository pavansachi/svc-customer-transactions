# svc-customer-transactions
web service for customer transactions

## Tools and Libraries
Spring Boot, Maven, Spring Data, H2, Java8

### Requirements
Java8 should be on the classpath and installed

## Usage

mvn clean install
mvn spring-boot:run

Application starts on port 8080

## APIs

GET http://localhost:8080/transactions/customer?id=<customer_id>&mon=<month>&y=<year>

GET http://localhost:8080/transactions/config
  
Example: http://localhost:8080/transactions/customer?id=2>&mon=4&y=2016

