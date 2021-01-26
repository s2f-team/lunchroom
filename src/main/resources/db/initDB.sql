DROP SCHEMA IF EXISTS lunchroom;

DROP TABLE IF EXISTS user_role;
DROP TABLE IF EXISTS dish;
DROP TABLE IF EXISTS vote;
DROP TABLE IF EXISTS menu;
DROP TABLE IF EXISTS restaurant;
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
    created  DATE    DEFAULT CURRENT_DATE NOT NULL,
    enabled  BOOL    DEFAULT TRUE         NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_role
(
    user_id INTEGER NOT NULL,
    role    VARCHAR,
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE restaurant
(
    id      INTEGER DEFAULT GLOBAL_SEQ.nextval PRIMARY KEY,
    name    VARCHAR(50)  NOT NULL,
    phone   VARCHAR(16)  NOT NULL,
    address VARCHAR(300) NOT NULL,
    website VARCHAR(100)
);
CREATE UNIQUE INDEX restaurant_unique_name_phone_idx ON restaurant (name, phone);

CREATE TABLE menu
(
    id      INTEGER DEFAULT GLOBAL_SEQ.nextval PRIMARY KEY,
    created DATE    DEFAULT CURRENT_DATE NOT NULL,
    rest_id INTEGER                      NOT NULL,
    FOREIGN KEY (rest_id) REFERENCES restaurant (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX menu_unique_rest_created_idx ON menu (rest_id, created);

CREATE TABLE dish
(
    id      INTEGER DEFAULT GLOBAL_SEQ.nextval PRIMARY KEY,
    name    VARCHAR(100) NOT NULL,
    price   INTEGER      NOT NULL,
    menu_id INTEGER      NOT NULL,
    FOREIGN KEY (menu_id) REFERENCES menu (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX dish_unique_menu_name_idx ON dish (name, menu_id);

CREATE TABLE vote
(
    id      INTEGER DEFAULT GLOBAL_SEQ.nextval PRIMARY KEY,
    created DATE    DEFAULT CURRENT_DATE NOT NULL,
    rest_id INTEGER                      NOT NULL,
    menu_id INTEGER                      NOT NULL,
    user_id INTEGER                      NOT NULL,
    FOREIGN KEY (rest_id) REFERENCES restaurant (id) ON DELETE CASCADE,
    FOREIGN KEY (menu_id) REFERENCES menu (id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX vote_unique_user_created_idx ON vote (user_id, created);
