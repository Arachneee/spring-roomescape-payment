INSERT INTO theme (name, description, thumbnail)
VALUES ('이름1', '설명1', 'https://i.pinimg.com/236x/6e/bc/46/6ebc461a94a49f9ea3b8bbe2204145d4.jpg'),
       ('이름2', '설명2', 'https://i.pinimg.com/236x/6e/bc/46/6ebc461a94a49f9ea3b8bbe2204145d4.jpg'),
       ('이름3', '설명3', 'https://i.pinimg.com/236x/6e/bc/46/6ebc461a94a49f9ea3b8bbe2204145d4.jpg'),
       ('이름4', '설명4', 'https://i.pinimg.com/236x/6e/bc/46/6ebc461a94a49f9ea3b8bbe2204145d4.jpg'),
       ('이름5', '설명5', 'https://i.pinimg.com/236x/6e/bc/46/6ebc461a94a49f9ea3b8bbe2204145d4.jpg'),
       ('이름6', '설명6', 'https://i.pinimg.com/236x/6e/bc/46/6ebc461a94a49f9ea3b8bbe2204145d4.jpg'),
       ('이름7', '설명7', 'https://i.pinimg.com/236x/6e/bc/46/6ebc461a94a49f9ea3b8bbe2204145d4.jpg'),
       ('이름8', '설명8', 'https://i.pinimg.com/236x/6e/bc/46/6ebc461a94a49f9ea3b8bbe2204145d4.jpg'),
       ('이름9', '설명9', 'https://i.pinimg.com/236x/6e/bc/46/6ebc461a94a49f9ea3b8bbe2204145d4.jpg'),
       ('이름10', '설명10', 'https://i.pinimg.com/236x/6e/bc/46/6ebc461a94a49f9ea3b8bbe2204145d4.jpg'),
       ('이름11', '설명11', 'https://i.pinimg.com/236x/6e/bc/46/6ebc461a94a49f9ea3b8bbe2204145d4.jpg'),
       ('이름12', '설명12', 'https://i.pinimg.com/236x/6e/bc/46/6ebc461a94a49f9ea3b8bbe2204145d4.jpg'),
       ('이름13', '설명13', 'https://i.pinimg.com/236x/6e/bc/46/6ebc461a94a49f9ea3b8bbe2204145d4.jpg');
INSERT INTO reservation_time (start_at)
VALUES ('09:00'),
       ('10:00'),
       ('11:00'),
       ('12:00'),
       ('13:00'),
       ('14:00'),
       ('15:00');
INSERT INTO member (name, email, password, role)
VALUES ('어드민', 'admin@admin.com', '1234', 'ADMIN'),
       ('유저1', 'user1@user.com', '1234', 'USER'),
       ('유저2', 'user2@user.com', '1234', 'USER'),
       ('유저3', 'user3@user.com', '1234', 'USER');

INSERT INTO reservation (date, time_id, theme_id)
VALUES ('2024-05-04', 1, 1 ),
       ('2024-05-04', 2, 1 ),
       ('2024-05-05', 3, 1 ),
       ('2024-05-05', 1, 2 ),
       ('2024-05-05', 1, 3 ),
       ('2024-05-09', 1, 2 ),
       ('2024-05-05', 1, 4 ),
       ('2024-05-06', 1, 2 ),
       ('2024-05-07', 1, 7 ),
       ('2024-05-08', 1, 8 ),
       ('2024-05-09', 1, 9 ),
       ('2024-05-10', 1, 10 ),
       ('2024-05-29', 2, 2 ),
       ('2024-05-30', 1, 1 ),
       ('2024-05-30', 2, 2 ),
       ('2024-05-30', 3, 2 ),
       ('2024-05-30', 4, 2 ),
       ('2024-05-30', 7, 2 )
;
INSERT INTO booked_member(reservation_id, member_id)
VALUES (1, 1),
       (2, 1),
       (3, 1),
       (4, 2),
       (5, 2),
       (6, 2),
       (7, 2),
       (8, 3),
       (9, 3),
       (10, 3),
       (11, 3),
       (12, 3),
       (13, 3),
       (14, 1),
       (15, 2),
       (16, 2),
       (17, 2),
       (18, 2);

INSERT INTO waiting_member (member_id, reservation_id, is_deleted)
VALUES (3, 15, false),
       (1, 15, false),
       (1, 16, false);
