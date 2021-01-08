DELETE
FROM users;
DELETE
FROM restaurants;
DELETE
FROM dishes;
DELETE
FROM votes;

ALTER SEQUENCE GLOBAL_SEQ RESTART WITH 100000;

INSERT INTO USERS (name, email, password)
VALUES ('User1', '1@mail.ru', '111'),
       ('User2', '2@mail.ru', '222'),
       ('Admin', '3@mail.ru', '333');

INSERT INTO RESTAURANTS(name, phone, address, website)
VALUES ('Fish House', '+7(999)111-22-33', 'Moscow, Mayakovskaya, 10', 'www.fishhouse.ru'),
       ('Мясо', '+7(495)111-23-45', 'Moscow, Sretenka, 2', 'www.meat.ru'),
       ('Coffee shop', '+7(499)125-34-23', 'Moscow, Tulskaya, 17', 'www.coffee.ru'),
       ('Masterskaya', '+7(495)975-45-23', 'Moscow, Myasnitskaya, 22', 'www.masterskaya.ru'),
       ('Bad rest', '+7(495)975-45-23', 'Moscow, Nitskaya, 13', 'www.bad.ru');

INSERT INTO MENU(rest_id)
VALUES (100003),
       (100004),
       (100005),
       (100006);

INSERT INTO DISHES (name, price, menu_id)
VALUES ('Fish', 150, 100008),
       ('Soup', 50, 100008),
       ('Juice', 70, 100008),
       ('Meat', 200, 100009),
       ('Tea', 20, 100009),
       ('Coffee', 100, 100010),
       ('Pancake', 200, 100010),
       ('Borsch', 70, 100011),
       ('Steak', 400, 100011),
       ('Mors', 100, 100011);

INSERT INTO VOTES (rest_id, menu_id, user_id)
VALUES (100003, 100008, 100000),
       (100006, 100011, 100001),
       (100006, 100011, 100002);

/*INSERT INTO LUNCHES(DATE, REST_ID)
VALUES (  )*/

/*DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password, calories_per_day)
VALUES ('User', 'user@yandex.ru', '{noop}password', 2005),
       ('Admin', 'admin@gmail.com', '{noop}admin', 1900);

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001),
       ('USER', 100001);

INSERT INTO meals (date_time, description, calories, user_id)
VALUES ('2020-01-30 10:00:00', 'Завтрак', 500, 100000),
       ('2020-01-30 13:00:00', 'Обед', 1000, 100000),
       ('2020-01-30 20:00:00', 'Ужин', 500, 100000),
       ('2020-01-31 0:00:00', 'Еда на граничное значение', 100, 100000),
       ('2020-01-31 10:00:00', 'Завтрак', 500, 100000),
       ('2020-01-31 13:00:00', 'Обед', 1000, 100000),
       ('2020-01-31 20:00:00', 'Ужин', 510, 100000),
       ('2020-01-31 14:00:00', 'Админ ланч', 510, 100001),
       ('2020-01-31 21:00:00', 'Админ ужин', 1500, 100001);*/