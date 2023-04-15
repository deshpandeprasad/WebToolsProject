# INFO6250 - Web Development Tools & Methods Final Project

## Topic: Library Management System

### Project Guidelines:
1. You will create a Web 2.0 application that allows users to consume and produce data.
2. The minimum requirements for the project are:
  - Spring Boot
  - Annotated Controller
  - Hibernate
  - Annotated POJOs
  - DAO Pattern

### Description:
This is a library management system for a public library, which will allow users to register themselves to be able browse their favorite books and reserve them. They will have to pick-up their reserved books from the library. This system will also have employee and admin accounts, which will allow for the management of book inventory and customers.

### Technical Stack:
  - Client Side: HTML, CSS, JSP
  - Server Side: Spring MVC, Hibernate
  - Database: MySQL
  - Server: Apache Tomcat

### Users and their functions:
This project will have 3 types of users:
1. Customer:
  - Able to create an account and login
  - Reserve books for pick-up
  - View book reservation status
2. Admin:
  - Able to edit and delete employee and customer accounts
3. Employee:
  - Change the status of reservation of customer
  - View customer details and their reservations
  - Add/edit/delete books

## Instructions to run the project
1. Download the zip and extract it to your favourite location.
2. Open the project folder in SpringToolSuite4.
3. Go to the hibernate.cfg.xml and set the DBName, username and password of your MYSQL Server.
4. Run the project
