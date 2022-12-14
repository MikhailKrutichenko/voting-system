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
VALUES ('user1', '123', 'user1@mail.ru', 'ROLE_USER'),
       ('user2', '456', 'user2@mail.ru', 'ROLE_USER'),
       ('user3', '789', 'user3@mail.ru', 'ROLE_USER'),
       ('user4', '124', 'user4@mail.ru', 'ROLE_USER'),
       ('user5', '125', 'user5@mail.ru', 'ROLE_USER'),
       ('user6', '126', 'user6@mail.ru', 'ROLE_USER'),
       ('admin', '111', 'admin@mail.ru', 'ROLE_ADMIN');

INSERT INTO restaurant (name)
VALUES ('mac'),
       ('iburger'),
       ('m&m');

INSERT INTO dish (restaurant_id, name, date, price)
VALUES (10007, 'Chicken', CURDATE(), 30000),
       (10007, 'Tea', CURDATE(), 15000),
       (10008, 'Burger', CURDATE(), 25000),
       (10008, 'Coffee', CURDATE(), 20000),
       (10009, 'Ice-cream', CURDATE(), 15500),
       (10009, 'Juice', CURDATE(), 25000);

INSERT INTO vote(user_id, restaurant_id, date)
VALUES (10000, 10008, CURDATE()),
       (10001, 10008, CURDATE()),
       (10002, 10009, CURDATE()),
       (10003, 10009, CURDATE()),
       (10004, 10009, CURDATE()),
       (10005, 10009, CURDATE());

