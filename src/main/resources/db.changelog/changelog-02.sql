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
INSERT INTO BOOKS (name, picture, description)
VALUES ('Angels & Demons', 'https://img-cloud.megaknihy.cz/5065845-original/c3400d50d54f292ede06706fd8200f57/angels-and-demons-illuminati-englische-ausgabe.jpg', 'Angels and Demons, the first installment of Dan Brown''s mystery thriller saga, follows symbologist Robert Langdon on an adventure to solve a historical puzzle laid across Rome. Along with recurring protagonist Langdon, the novel establishes many of Brown''s themes including cryptography and conspiracy theories.'),
       ('Season of Storms', 'https://www.hachettebookgroup.com/wp-content/uploads/2022/07/9780316453165.jpg?w=683', '"Season of Storms" is a fantasy novel by Polish author Andrzej Sapkowski, set in the world of The Witcher. The story follows Geralt of Rivia, a monster hunter, who is drawn into a conspiracy involving sorcerers, knights, and a powerful mage. Geralt must navigate through political intrigue and dangerous magical experiments to uncover the truth and protect those he cares about. The novel is a prequel to the main Witcher series and can be read as a standalone story.'),
       ('The Bear and the Nightingale', 'https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1611440212i/25489134.jpg', '"The Bear and the Nightingale" is a historical fantasy novel by Katherine Arden. The story takes place in medieval Russia and follows Vasilisa, a young girl with the ability to communicate with magical creatures. When a new priest arrives in her village and tries to suppress the old beliefs and practices, Vasilisa must use her powers to save her family and community from dark forces that threaten to destroy them. The novel explores themes of faith, family, and the clash between traditional beliefs and modern religion. It is the first book in the Winternight Trilogy.');

-- Insert data into USER_BOOKS table
INSERT INTO USER_BOOKS (user_id, book_id, state_id, review_id)
VALUES (1, 1, 3, 1),
       (1, 2, 3, 2),
       (2, 3, 3, 3),
       (2, 1, 2, NULL);

INSERT INTO AUTHOR_BOOKS (author_id, book_id)
VALUES (1, 1),
       (2, 2),
       (3, 3);
