CREATE TABLE RATINGS (
  rating_id SERIAL PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  value INTEGER NOT NULL
);

CREATE TABLE STATES (
  state_id SERIAL PRIMARY KEY,
  name VARCHAR(50) NOT NULL
);

CREATE TABLE AUTHORS (
  author_id SERIAL PRIMARY KEY,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL
);

CREATE TABLE REVIEWS (
  review_id SERIAL PRIMARY KEY,
  creation_date TIMESTAMP NOT NULL,
  text TEXT NOT NULL,
  rating_id INTEGER REFERENCES RATINGS(rating_id)
);

CREATE TABLE APP_USERS (
  user_id SERIAL PRIMARY KEY,
  login_name VARCHAR(50) UNIQUE NOT NULL,
  password VARCHAR(256) UNIQUE NOT NULL,
  email VARCHAR(50) UNIQUE NOT NULL,
  first_name VARCHAR(50),
  last_name VARCHAR(50),
  date_of_birth DATE NOT NULL
);

CREATE TABLE BOOKS (
  book_id SERIAL PRIMARY KEY,
  name VARCHAR(200) NOT NULL,
  picture TEXT NOT NULL,
  description TEXT NOT NULL
);

CREATE TABLE USER_BOOKS (
  user_book_id SERIAL PRIMARY KEY,
  user_id INTEGER REFERENCES APP_USERS(user_id) NOT NULL,
  book_id INTEGER REFERENCES BOOKS(book_id) NOT NULL,
  state_id INTEGER REFERENCES STATES(state_id) NOT NULL,
  review_id INTEGER REFERENCES REVIEWS(review_id) ON DELETE CASCADE
);

CREATE TABLE AUTHOR_BOOKS (
    author_id INTEGER REFERENCES AUTHORS(author_id) NOT NULL,
    book_id INTEGER REFERENCES BOOKS(book_id) NOT NULL
);