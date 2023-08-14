-- Create the 'library_management' database if it doesn't exist and switch to it
CREATE DATABASE IF NOT EXISTS library_management;
USE library_management;

-- Create the 'users' table to store user information
CREATE TABLE IF NOT EXISTS users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(100) NOT NULL,
    email_id VARCHAR(100) NOT NULL,
    mobile_no BIGINT NOT NULL,
    password VARCHAR(100) NOT NULL,
    gender CHAR(1) NOT NULL,
    dob DATE NOT NULL,
    created_date TIMESTAMP NOT NULL,
    isActive BOOLEAN NOT NULL DEFAULT TRUE,
    isAdmin BOOLEAN NOT NULL,
    profile_image VARCHAR(2048) NOT NULL
);

-- Insert a sample user record
INSERT INTO users (user_name, email_id, mobile_no, password, gender, dob, created_date, isAdmin, profile_image)
VALUES ('John Doe', 'johndoe@example.com', 1234567890, 'hashed_password', 'M', '1990-01-01', NOW(), true, 'profile_image_url');

-- Retrieve all records from 'users' table
SELECT * FROM users;


-- Create the 'books' table to store book information
CREATE TABLE IF NOT EXISTS books (
    book_id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    publisher VARCHAR(255),
    genre VARCHAR(100),
    language VARCHAR(50),
    description TEXT,
    total_copies INT DEFAULT 0,
    available_copies INT DEFAULT 0,
    loaned_copies INT DEFAULT 0,
    cover_image VARCHAR(255),
    isActive BOOLEAN NOT NULL DEFAULT TRUE
);

-- Insert a sample book record
INSERT INTO books (title, author, publisher, genre, language, description, total_copies, available_copies, loaned_copies, cover_image)
VALUES ('Sample Book', 'Sample Author', 'Sample Publisher', 'Sample Genre', 'English', 'This is a sample book.', 5, 3, 2, 'cover_image_url');

-- Retrieve all records from 'books' table
SELECT * FROM books;


-- Create the 'borrows' table to track book borrowings
CREATE TABLE IF NOT EXISTS borrows (
    borrow_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    book_id INT NOT NULL,
    borrow_date DATE NOT NULL,
    return_date DATE,
    due_date DATE NOT NULL,
    isReturned BOOLEAN DEFAULT false,
    fine DOUBLE DEFAULT 0,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (book_id) REFERENCES books(book_id)
);

-- Insert a sample borrow record
INSERT INTO borrows (user_id, book_id, borrow_date, return_date, due_date,isReturned, fine)
VALUES (1, 1, '2023-08-05', '2023-08-12', '2023-08-10',TRUE, 20.0);

-- Retrieve all records from 'borrows' table
SELECT * FROM borrows;