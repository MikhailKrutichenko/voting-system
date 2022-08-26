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
VALUES ('user1', '$2a$12$W/oysnsi9jkA60tKxWIg1uVgd7Yn5t42/853iNOpRJkQXiRYpDyua', 'user1@mail.ru', 'ROLE_USER'),
       ('user2', '$2a$12$RYuB5KF12E.p93izxbCxO.2E1XBJaxZyXWIqbKSTlPHsWczE6Z20G', 'user2@mail.ru', 'ROLE_USER'),
       ('user3', '$2a$12$r1Hlz7Y2vtV3cYXvHQh1Werrxmk6a4.RgnDsMg8AfinIPlCzEQ1wW', 'user3@mail.ru', 'ROLE_USER'),
       ('user4', '$2a$12$8IlRsaUGcIJbWu8dtZ9FmuQu.wSe7jQG8.9EXpyM293GBSsKHUYL6', 'user4@mail.ru', 'ROLE_USER'),
       ('user5', '$2a$12$QAGg1KNJs9JMGWT7gv4xrOwMANW5X.fm8OuJFl0X.rmN0fEGz.Gou', 'user5@mail.ru', 'ROLE_USER'),
       ('user6', '$2a$12$ix8LJ21n3vnsG8QVEYx.a.BIsiRt6VHf2LzvusTCQ4kB4zt9L66FK', 'user6@mail.ru', 'ROLE_USER'),
       ('admin', '$2a$12$awZw0JJNYd4LM8ZESP/Yz.I03oUp8Gs957bZoYJgIh.UknpCGBRUa', 'admin@mail.ru', 'ROLE_ADMIN');

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

