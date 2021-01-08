DROP SCHEMA IF EXISTS lunchroom;

DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS dishes;
DROP TABLE IF EXISTS menu;
DROP TABLE IF EXISTS restaurants;
DROP TABLE IF EXISTS votes;
DROP TABLE IF EXISTS users;

CREATE SCHEMA lunchroom;

DROP SEQUENCE IF EXISTS GLOBAL_SEQ;

CREATE SEQUENCE GLOBAL_SEQ START WITH 100000;

CREATE TABLE users
(
    id       INTEGER DEFAULT GLOBAL_SEQ.nextval PRIMARY KEY,
    name     VARCHAR(25)                  NOT NULL,
    email    VARCHAR(100)                 NOT NULL,
    password VARCHAR(500)                 NOT NULL,
    date     DATE    DEFAULT CURRENT_DATE NULL,
    enabled  BOOL    DEFAULT TRUE         NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL,
    role    VARCHAR,
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE restaurants
(
    id      INTEGER DEFAULT GLOBAL_SEQ.nextval PRIMARY KEY,
    name    VARCHAR(50)        NOT NULL,
    phone   VARCHAR(16) UNIQUE NOT NULL,
    address VARCHAR(300)       NOT NULL,
    website VARCHAR(100) UNIQUE
);

CREATE TABLE menu
(
    id      INTEGER DEFAULT GLOBAL_SEQ.nextval PRIMARY KEY,
    date    DATE    DEFAULT CURRENT_DATE NOT NULL,
    rest_id INTEGER                      NOT NULL,
    FOREIGN KEY (rest_id) REFERENCES restaurants (id) ON DELETE CASCADE
);

CREATE TABLE dishes
(
    id      INTEGER DEFAULT GLOBAL_SEQ.nextval PRIMARY KEY,
    name    VARCHAR(100) NOT NULL,
    price   INTEGER      NOT NULL,
    menu_id INTEGER      NOT NULL,
    FOREIGN KEY (menu_id) REFERENCES menu (id) ON DELETE CASCADE
);

CREATE TABLE votes
(
    id      INTEGER   DEFAULT GLOBAL_SEQ.nextval PRIMARY KEY,
    date    TIMESTAMP DEFAULT now() NOT NULL,
    rest_id INTEGER                 NOT NULL,
    menu_id INTEGER                 NOT NULL,
    user_id INTEGER                 NOT NULL,
    FOREIGN KEY (rest_id) REFERENCES restaurants (id) ON DELETE CASCADE,
    FOREIGN KEY (menu_id) REFERENCES menu (id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);