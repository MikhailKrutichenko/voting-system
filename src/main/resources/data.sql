DELETE
FROM vote;
DELETE
FROM dish;
DELETE
FROM users;
DELETE
FROM restaurant;
ALTER SEQUENCE global_seq RESTART WITH 10000;

INSERT INTO users (name, password, email, role)
VALUES ('user1', '123', 'user1@mail.ru', 'USER'),
       ('user2', '456', 'user2@mail.ru', 'USER'),
       ('user3', '789', 'user3@mail.ru', 'USER'),
       ('user4', '124', 'user4@mail.ru', 'USER'),
       ('user5', '125', 'user5@mail.ru', 'USER'),
       ('user6', '126', 'user6@mail.ru', 'USER'),
       ('admin', '111', 'admin@mail.ru', 'ADMIN');

INSERT INTO restaurant (name)
VALUES ('mac'),
       ('iburger'),
       ('m&m');

INSERT INTO dish (restaurant_id, name, date, price)
VALUES (10007, 'Chicken', '2022-08-17', 30000),
       (10007, 'Tea', '2022-08-17', 15000),
       (10008, 'Burger', '2022-08-17', 25000),
       (10008, 'Coffee', '2022-08-17', 20000),
       (10009, 'Ice-cream', '2022-08-17', 15500),
       (10009, 'Juice', '2022-08-17', 25000);

INSERT INTO vote(user_id, restaurant_id, date)
VALUES (10001, 10008, CURDATE()),
       (10002, 10008, CURDATE()),
       (10003, 10009, CURDATE()),
       (10004, 10009, CURDATE()),
       (10005, 10009, CURDATE()),
       (10006, 10009, CURDATE());

