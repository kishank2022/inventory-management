# Quarkus Product Inventory Management

A Quarkus Reactive application for product inventory management with CRUD operations and external price validation service integration using Quarkus REST Client.

## Features

- Product entity with fields: `id`, `name`, `description`, `quantity`, `price`, `supplier`
- Full CRUD REST APIs
	- Create Product
	- URL - http://localhost:8080/products
	Method - POST
	Json Request - {
    "name":"Tata Nexon",
    "description":"EV Car",
    "quantity":5,
    "price":10,
    "supplier":"Tata Motors"
    }

	- Get All Products
	- URL - http://localhost:8080/products
	Method - Get
	
	
	- Get Product By ID
	- URL - http://localhost:8080/products/2
	Method - GET
	
	- Upodate Product
	- URL - http://localhost:8080/products/2
	Method - PUT
	Json Request - {
    "name":"Tata Nexon",
    "description":"EV Car",
    "quantity":5,
    "price":10,
    "supplier":"Tata Motors"
    }
    
    
    - Delete Product
	- URL - http://localhost:8080/products/2
	Method - DELETE
	
	

- External price validation integration using Quarkus REST Client (`PriceClient.java`)
- Business logic: Price validation is performed via external service when creating a new product (in `ProductService.java`)
- Reactive programming using Mutiny
- Persistence with MySQL database
- Unit and integration tests with mocked external services
- Clean RESTful design and proper error handling

## Technologies Used

- Quarkus (Reactive with Mutiny)
- Hibernate ORM with Panache
- Quarkus REST Client
- MySQL
- JUnit 5 + RestAssured for testing

## Prerequisites

- JDK 17 or higher
- Maven 3.8+
- MySQL 8.0+ running locally
- Spring Tool Suite (STS) or any IDE

## Setup Instructions

### 1. Database Configuration

Create the database and user in MySQL:

```sql
CREATE DATABASE inventorydb;
CREATE USER 'root'@'localhost' IDENTIFIED BY 'root';
GRANT ALL PRIVILEGES ON inventorydb.* TO 'root'@'localhost';
FLUSH PRIVILEGES;
