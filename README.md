- 👋 Hi, I’m Łukasz Kociuba
- 👀 I’m interested in resolving technical problems, new technology and creating fascinating programs
- 🌱 I’m currently learning Java, Spring Boot, JUnit, PostgreSql, Clean Code, Design Patterns, JavaScript, Node.js, MongoDB
- 💞️ I’m looking to collaborate on simple projects to improve my coding skills
- 📫 How to reach me - kociubalukasz@gmail.com

# myBookstore
This is an e-commerce website to sell books. I used Java for domain layer, Thymeleaf for frontend and Spring Boot. 
To connect to Database I used JPA. I tried to use Hexagonal architecture with DDD.
! This project don't contain validation for POST requests. Better working project is: myBookstore (with MVC architecture).


What functions to implement

For Customers:
1. The can register an account. Log in and log out.
2. They can scan the books and sorted by name and price.
3. They can add books into the cart.
4. They can add Customer Informations to the Cart Summary.
5. They can chceck Cart Summary


For Salesperson:
1. They can log in to the superadmin account.
2. After log in they are able to see "/bookList" and "/bookAdd" Views
3. They can add a new book.

Implemented functions:


## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)

## General info
This is a simple ecommerce website to show my programming skills.
	
## Technologies
Project is created with:
* Java version: 11
* Maven
* Spring Boot version: 2.5.4
	
## Setup
To run this project:

```
$ git clone https://github.com/lkociuba/functionalBookstore.git
$ docker pull lukaszkociuba/functional-bookstore:v1.0.0
```
