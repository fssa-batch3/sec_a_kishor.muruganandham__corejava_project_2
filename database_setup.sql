-- Create the 'kishor_muruganandham_corejava_project' database if it doesn't exist and switch to it
CREATE DATABASE IF NOT EXISTS kishor_muruganandham_corejava_project;
USE kishor_muruganandham_corejava_project;

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
VALUES ('John Doe', 'johndoe@gmail.com', 9691285360, '$10$fba51bObkP.3nMOpwioNe.Q7SPYH5caWrQKX9NdBivfkOjynDaUYq', 'M', '1990-01-01', NOW(), FALSE, 'https://ui-avatars.com/api/?name=Jhone%20Doe&rounded=true&uppercase=false&background=random');

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
VALUES
  ('The Great Gatsby', 'F. Scott Fitzgerald', 'Scribner', 'Fiction', 'English', 'A captivating story set in the Jazz Age, exploring themes of wealth, love, and the American Dream.', 5, 4, 1, 'https://m.media-amazon.com/images/I/71FTb9X6wsL._AC_UF1000,1000_QL80_.jpg'),
  ('To Kill a Mockingbird', 'Harper Lee', 'J. B. Lippincott & Co.', 'Fiction', 'English', 'A powerful novel that addresses racial inequality, injustice, and the loss of innocence in the Deep South during the 1930s.', 8, 8, 0, 'https://books.google.co.in/books/publisher/content?id=u019AwAAQBAJ&pg=PP1&img=1&zoom=3&hl=en&bul=1&sig=ACfU3U2ulEEQiCZ1Uy1JW7k6EEUcCKdYiw&w=1280'),
  ('1984', 'George Orwell', 'Secker & Warburg', 'Dystopian Fiction', 'English', 'Set in a totalitarian society, this classic novel explores themes of oppression, government surveillance, and the power of language.', 1, 1, 0, 'https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcSEgt7K5xQbX0XKb664rZAcBQR0fqT46OFwFSnM1X0cnkSBqY0z'),
  ('Pride and Prejudice', 'Jane Austen', 'T. Egerton, Whitehall', 'Romance', 'English', 'A witty and socially astute novel that follows the tumultuous relationship between Elizabeth Bennet and Mr. Darcy in Georgian England.', 10, 10, 0, 'https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcSTHJWxM-a_YVg8VfdJ7e-hdGFp2u1QLM2ytYuNCYgZbbvvWutr'),
  ('The Catcher in the Rye', 'J.D. Salinger', 'Little, Brown and Company', 'Fiction', 'English', 'A coming-of-age novel that explores teenage angst, alienation, and the search for authenticity in post-World War II America.', 6, 6, 0, 'https://books.google.co.in/books/publisher/content?id=mZunDwAAQBAJ&pg=PP1&img=1&zoom=3&hl=en&bul=1&sig=ACfU3U07lP3riniWKMhZBYyJJ6DAX_jcFg&w=1280'),
  ('Harry Potter and the Sorcerer''s Stone', 'J.K. Rowling', 'Bloomsbury Publishing', 'Fantasy', 'English', 'The first book in the beloved Harry Potter series, introducing readers to the magical world of Hogwarts, friendship, and the fight against evil.', 4, 4, 0, 'https://m.media-amazon.com/images/I/71HbYElfY0L._AC_UF1000,1000_QL80_.jpg'),
  ('The Lord of the Rings', 'J.R.R. Tolkien', 'George Allen & Unwin', 'Fantasy', 'English', 'An epic fantasy trilogy that follows the quest to destroy the One Ring and save Middle-earth from the dark forces of Sauron.', 5, 5, 0, 'https://m.media-amazon.com/images/M/MV5BN2EyZjM3NzUtNWUzMi00MTgxLWI0NTctMzY4M2VlOTdjZWRiXkEyXkFqcGdeQXVyNDUzOTQ5MjY@._V1_.jpg'),
  ('To the Lighthouse', 'Virginia Woolf', 'Hogarth Press', 'Modernist Literature', 'English', 'A stream-of-consciousness novel that explores the shifting dynamics of relationships, self-discovery, and the passage of time.', 7, 7, 0, 'https://books.google.co.in/books/content?id=O61n4NcQlUkC&pg=PA5&img=1&zoom=3&hl=en&bul=1&sig=ACfU3U0GjHkX5fXpQbESCcLCbCSCzLQJ1w&w=1025'),
  ('Who''s Afraid of Virginia Woolf?', 'Andria', 'Roob, Dare and Carter', 'Drama', 'West Frisian', 'Complications of anesthesia during the puerperium', 10, 10, 0, 'https://image.tmdb.org/t/p/original/wF7ihB5V5gSm6zxjv3ZhHOpgREI.jpg'),
  ('The Hobbit', 'J.R.R. Tolkien', 'George Allen & Unwin', 'Fantasy', 'English', 'A classic fantasy novel that follows the adventures of Bilbo Baggins as he joins a group of dwarves on a quest to reclaim their homeland from the dragon Smaug.', 5, 5, 0, 'https://m.media-amazon.com/images/I/61-v4nRBw+L._AC_UF1000,1000_QL80_.jpg');


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
    isReturned BOOLEAN DEFAULT FALSE,
    fine DOUBLE DEFAULT 0,
    FOREIGN KEY (user_id)
        REFERENCES users (user_id),
    FOREIGN KEY (book_id)
        REFERENCES books (book_id)
);

-- Insert a sample borrow record
INSERT INTO borrows (user_id, book_id, borrow_date, return_date, due_date,isReturned, fine)
VALUES (1, 1, '2023-08-05', '2023-08-12', '2023-08-10',TRUE, 20.0);

-- Retrieve all records from 'borrows' table
SELECT * FROM borrows;