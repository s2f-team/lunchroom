DELETE
FROM user_role;
DELETE
FROM users;
DELETE
FROM restaurant;
DELETE
FROM dish;
DELETE
FROM vote;

ALTER SEQUENCE GLOBAL_SEQ RESTART WITH 100000;

INSERT INTO USERS (name, email, password)
VALUES ('User1', '1@mail.ru', '{noop}password'),
       ('User2', '2@mail.ru', '{noop}password'),
       ('Admin', '3@mail.ru', '{noop}password');

INSERT INTO user_role (role, user_id)
VALUES ('USER', 100000),
       ('USER', 100001),
       ('ADMIN', 100002),
       ('USER', 100002);

INSERT INTO RESTAURANT(name, phone, address, website)
VALUES ('Fish House', '+7(999)111-22-33', 'Moscow, Mayakovskaya, 10', 'www.fishhouse.ru'),
       ('Мясо', '+7(495)111-23-45', 'Moscow, Sretenka, 2', 'www.meat.ru'),
       ('Coffee shop', '+7(499)125-34-23', 'Moscow, Tulskaya, 17', 'www.coffee.ru'),
       ('Masterskaya', '+7(495)975-45-23', 'Moscow, Myasnitskaya, 22', 'www.masterskaya.ru'),
       ('Bad rest', '+7(495)975-46-23', 'Moscow, Nitskaya, 13', 'www.bad.ru');

INSERT INTO MENU(rest_id)
VALUES (100003),
       (100004),
       (100005),
       (100006);

INSERT INTO DISH (name, price, menu_id)
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

INSERT INTO VOTE (rest_id, menu_id, user_id)
VALUES (100003, 100008, 100000),
       (100006, 100011, 100001),
       (100006, 100011, 100002);