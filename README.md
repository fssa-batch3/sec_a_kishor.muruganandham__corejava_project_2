# Library Management

## Table of Contents

1. [Introduction](#introduction)
    - Purpose
    - Features
    - User Persona
2. [Prerequisites](#prerequisites)
    - Software Requirements
    - Database Setup
3. [Project Setup](#project-setup)
    - Java Project Creation
    - Library Dependencies
4. [Database](#database)
    - Entity-Relationship Diagram (ERD)
    - Database Tables
5. [Modules](#modules)
    - User Module
    - Book Module
    - Borrow Module
6. [Validations](#validations)
    - User Validations
    - Book Validations
    - Borrow Validations
7. [Testing](#testing)
    - Unit Testing
8. [Exception Handling](#exception-handling)
    - Common Error Messages
    - Exception Handling
9. [Future Improvements](#future-improvements)
    - Planned Features
    - Roadmap
10. [Resources](#resources)
    - External Libraries
    - References

## Introduction

### Purpose

Develop a Java-based Library Management System to efficiently manage the inventory, borrowing, and returning of books
for a local library.

### Features

- Add, Update, View, Delete,Search and List all Book.
- Register, Login, Update, Delete User.
- Borrow, Renew, Return and Check availability of a Book.

### User Persona

- Librarian
- Members

## Prerequisites

### Software Requirements

- Java Development Kit (JDK)
- MySQL Database Server
- Integrated Development Environment

### Database Setup

- Table scripts: [Script](database_setup.sql)

## Project Setup

### Java Project Creation

- Create a new Java project
- Set up a MySQL database

### Library Dependencies

- JDBC,
- MySQL Connector,
- JUnit,

## Database

### Entity-Relationship Diagram (ERD)

[![HtTccyG.md.png](https://iili.io/HtTccyG.md.png)](https://freeimage.host/i/HtTccyG)

### Database Tables

#### Table: users

| Field         | Type          | Null | Key | Default           | Extra |
|---------------|---------------|------|-----|-------------------|-------|
| user_id       | int           | NO   | PRI | auto_increment    |       |
| user_name     | varchar(100)  | NO   |     |                   |       |
| email_id      | varchar(100)  | NO   |     |                   |       |
| mobile_no     | bigint        | NO   |     |                   |       |
| password      | varchar(100)  | NO   |     |                   |       |
| gender        | char(1)       | NO   |     |                   |       |
| dob           | date          | NO   |     |                   |       |
| created_date  | timestamp     | NO   |     | CURRENT_TIMESTAMP |       |
| isActive      | tinyint(1)    | NO   |     | 1                 |       |
| isAdmin       | tinyint(1)    | NO   |     |                   |       |
| profile_image | varchar(2048) | NO   |     |                   |       |

#### Table: books

| Field            | Type         | Null | Key | Default        | Extra |
|------------------|--------------|------|-----|----------------|-------|
| book_id          | int          | NO   | PRI | auto_increment |       |
| title            | varchar(255) | NO   |     |                |       |
| author           | varchar(255) | NO   |     |                |       |
| publisher        | varchar(255) | YES  |     |                |       |
| genre            | varchar(100) | YES  |     |                |       |
| language         | varchar(50)  | YES  |     |                |       |
| description      | text         | YES  |     |                |       |
| total_copies     | int          | YES  |     | 0              |       |
| available_copies | int          | YES  |     | 0              |       |
| loaned_copies    | int          | YES  |     | 0              |       |
| cover_image      | varchar(255) | YES  |     |                |       |
| isActive         | tinyint(1)   | NO   |     | 1              |       |

#### Table: borrows

| Field       | Type       | Null | Key | Default        | Extra |
|-------------|------------|------|-----|----------------|-------|
| borrow_id   | int        | NO   | PRI | auto_increment |       |
| user_id     | int        | NO   |     |                |       |
| book_id     | int        | NO   |     |                |       |
| borrow_date | date       | NO   |     |                |       |
| return_date | date       | YES  |     |                |       |
| isReturned  | tinyint(1) | YES  |     | 0              |       |

## Modules

### User Module :

- Add a user:
    - Allows to register new users.
- Update user details:
    - Enables to modify user information such as name, password, etc.
- Remove a user:
    - Allows to remove a user.
- Search users: (Librarian)
    - Allows to find users by name, ID, or other criteria.
- View user details: (Librarian)
    - Display detailed information about a specific user when selected.
- List all users: (Librarian)
    - Show a list of all registered users.

### Book Module :

- Add a book: (Librarian)
    - Allow to add new book.
- Update book details: (Librarian)
    - Enable to modify book information such as title, author, etc.
- Remove a book: (Librarian)
    - Allows to remove a book.
- Search books:
    - Enables to find books by title, author, or other criteria.
- View book details:
    - Display detailed information about a specific book when selected.
- List all books:
    - Show a list of all books available in the library.
- Check availability:
    - Indicate whether a particular book is available for borrowing or currently checked out.

### Borrow Module :

- Borrow a book:
    - Allow users to borrow books.
- Return a book:
    - Enable users to return borrowed books.
- Due date calculation:
    - Calculates the due date for borrowed books to avoid late returns.
- Borrow history:
    - Gives a history of past book borrowings.
- View borrowed books: (Librarian)
    - Show a list of books currently borrowed.
- Overdue Fine:
    - Calculates the fine for overdue book returns.
- User borrowing limit:
    - Allows User to borrow only a certain number of books.

## Validations

### User Validations :

- Name Validation
- Email Id Validation
- Phone number Validation
- Password Validation
- Gender Validation
- Date of Birth Validation
- Profile Image Url Validation

### Book Validations :

- Title Validation
- Author Validation
- Publisher Validation
- Genre Validation
- Language Validation
- Description Validation
- Total Copies Validation
- Available Copies Validation
- Loaned Copies Validation
- Cover Image Url Validation

### Borrow Validations :

- Borrow Date Validation
- Return Date Validation
- Fine Validation

## Testing

### Unit Testing

- UserServiceUnitTest
    - Register, Login, Update, Delete and List all Users.
- BookServiceUnitTest
    - Add, Update, View, Delete and List all Books
- BorrowServiceUnitTest
    - Borrow, Return, Borrow History and List all Borrows
- UserValidationUnitTest
- BookValidationUnitTest
- BorrowValidationUnitTest

## Exception Handling

### Custom Exceptions :

- DAO Exception
- Validation Exception
- Service Exception

### Common Error Messages :

- Name cannot be null or empty
- Invalid Password
- Invalid email address
- Invalid phone number
- User already exists
- Book not found
- Maximum borrow limit reached
- Book already borrowed
- Invalid book id
- Failed to update user information
- Failed to update book information
- Failed to return the book
- Database connection error

## Future Improvements

### Planned Features

- Book Recommendations
    - Implement a recommendation engine that suggests books based on a user's reading history and preferences.
- Book Reviews and Ratings
    - Enable users to leave reviews and ratings for books they have read.
- Advanced Book Search
    - Enhance the book search functionality with advanced filters, sorting options, and the ability to search within
      specific genres or authors.

### Roadmap

- QR Code Integration
    - Implement QR code generation and scanning for easy book check-ins and check-outs in physical libraries.
- Data Analytics and Insights:
    - Integrate data analytics tools to gather insights on user behavior, popular book genres, and borrowing patterns.

## Resources

### External Libraries

- JDBC:
    - Java Database Connectivity library used for database interactions.
- MySQL Connector:
    - JDBC driver for connecting to the MySQL database.
- JUnit:
    - A testing framework for writing and running unit tests in Java

### References

- Java Platform, Standard Edition Documentation: https://docs.oracle.com/javase/8/docs/api/
- MySQL Documentation: https://dev.mysql.com/doc/
- JUnit 5 User Guide: https://junit.org/junit5/docs/current/user-guide/

