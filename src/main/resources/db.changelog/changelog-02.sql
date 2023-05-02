-- Insert data into RATINGS table
INSERT INTO RATINGS (name, value)
VALUES ('Poor', 1),
       ('Fair', 2),
       ('Good', 3),
       ('Very Good', 4),
       ('Excellent', 5);

-- Insert data into STATES table
INSERT INTO STATES (name)
VALUES ('Want to read'),
       ('Currently reading'),
       ('Read');

-- Insert data into AUTHORS table
INSERT INTO AUTHORS (first_name, last_name)
VALUES ('Dan', 'Brown'),
       ('Andrzej', 'Sapkowski'),
       ('Katherine', 'Arden');

-- Insert data into REVIEWS table
INSERT INTO REVIEWS (creation_date, text, rating_id)
VALUES ('2022-04-30 12:00:00', 'This is a great book!', 4),
       ('2022-05-01 09:00:00', 'I did not like it very much', 1),
       ('2022-05-01 15:00:00', 'This book is amazing!', 5);

-- Insert data into APP_USERS table
INSERT INTO APP_USERS (login_name, password, email, first_name, last_name, date_of_birth)
VALUES ('user1', 'password1', 'user1@email.com', 'John', 'Doe', '2000-01-01'),
       ('user2', 'password2', 'user2@email.com', 'Jane', 'Doe', '1995-05-05');

-- Insert data into BOOKS table
INSERT INTO BOOKS (name, picture, description, author_id)
VALUES ('Angels & Demons', 'https://img-cloud.megaknihy.cz/5065845-original/c3400d50d54f292ede06706fd8200f57/angels-and-demons-illuminati-englische-ausgabe.jpg', 'This is a great book!', 1),
       ('Season of Storms', 'https://static.wikia.nocookie.net/witcher/images/e/e5/Season_of_storms_cover_us_english.jpg/revision/latest?cb=20180520184003', 'I didn not like it very much', 2),
       ('The Bear and the Nightingale', 'https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1611440212i/25489134.jpg', 'This book is amazing!', 3);

-- Insert data into USER_BOOKS table
INSERT INTO USER_BOOKS (user_id, book_id, state_id, review_id)
VALUES (1, 1, 3, 1),
       (1, 2, 3, 2),
       (2, 3, 3, 3),
       (2, 3, 1, NULL);
