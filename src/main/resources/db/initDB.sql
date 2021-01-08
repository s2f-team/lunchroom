DROP SCHEMA IF EXISTS lunchroom;

CREATE SCHEMA lunchroom;

DROP SEQUENCE IF EXISTS GLOBAL_SEQ;

CREATE SEQUENCE GLOBAL_SEQ START WITH 100000;

CREATE TABLE users
(
    id       INTEGER   DEFAULT GLOBAL_SEQ.nextval PRIMARY KEY,
    name     VARCHAR(25)             NOT NULL,
    email    VARCHAR(100)            NOT NULL,
    password VARCHAR(500)            NOT NULL,
    date     TIMESTAMP DEFAULT now() NOT NULL,
    enabled  BOOL      DEFAULT TRUE  NOT NULL
);

CREATE TABLE restaurants
(
    id      INTEGER DEFAULT GLOBAL_SEQ.nextval PRIMARY KEY,
    name    VARCHAR(50)  NOT NULL,
    phone   VARCHAR(16)  NOT NULL,
    address VARCHAR(300) NOT NULL,
    website VARCHAR(100) NOT NULL
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
    name    VARCHAR(100)                 NOT NULL,
    price   INTEGER                      NOT NULL,
    menu_id INTEGER                      NOT NULL,
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

/*
CREATE TABLE users
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name             VARCHAR                           NOT NULL,
    email            VARCHAR                           NOT NULL,
    password         VARCHAR                           NOT NULL,
    registered       TIMESTAMP           DEFAULT now() NOT NULL,
    enabled          BOOL                DEFAULT TRUE  NOT NULL,
    calories_per_day INTEGER             DEFAULT 2000  NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL,
    role    VARCHAR,
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE meals
(
    id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    user_id     INTEGER   NOT NULL,
    date_time   TIMESTAMP NOT NULL,
    description TEXT      NOT NULL,
    calories    INT       NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX meals_unique_user_datetime_idx ON meals (user_id, date_time);*/
