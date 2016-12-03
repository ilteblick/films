CREATE TABLE books
(
    id INT(11) PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    avatar VARCHAR(255),
    rating DOUBLE,
    count_pages INT(11) NOT NULL,
    year INT(11) NOT NULL,
    genre_id INT(11) NOT NULL,
    CONSTRAINT books_genre_id_fk FOREIGN KEY (genre_id) REFERENCES genre (id)
);
CREATE INDEX books_genre_id_fk ON books (genre_id);
CREATE TABLE users
(
    id INT(11) PRIMARY KEY NOT NULL,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    fio VARCHAR(255),
    email VARCHAR(255) NOT NULL
);
CREATE TABLE user_book
(
    id INT(11) PRIMARY KEY NOT NULL,
    user_id INT(11) NOT NULL,
    book_id INT(11) NOT NULL,
    value TINYINT(4) NOT NULL,
    status_id INT(11) NOT NULL,
    CONSTRAINT user_book_books_id_fk FOREIGN KEY (book_id) REFERENCES books (id),
    CONSTRAINT user_book_read_status_id_fk FOREIGN KEY (status_id) REFERENCES read_status (id),
    CONSTRAINT user_book_users_id_fk FOREIGN KEY (user_id) REFERENCES users (id)
);
CREATE INDEX rating_book_id_index ON user_book (book_id);
CREATE INDEX rating_user_id_index ON user_book (user_id);
CREATE INDEX user_book_read_status_id_fk ON user_book (status_id);
CREATE TABLE read_status
(
    id INT(11) PRIMARY KEY NOT NULL,
    value VARCHAR(255) NOT NULL
);
CREATE UNIQUE INDEX read_status_value_uindex ON read_status (value);
CREATE TABLE genre
(
    id INT(11) PRIMARY KEY NOT NULL,
    value VARCHAR(255) NOT NULL
);
CREATE UNIQUE INDEX genre_value_uindex ON genre (value);