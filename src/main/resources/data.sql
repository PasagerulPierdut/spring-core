INSERT INTO roles(id, name)
VALUES
(1, 'ROLE_USER'),
(2, 'ROLE_ADMIN');

INSERT INTO users(id, username, password)
VALUES
(1, 'test1', 'password1'),
(2, 'test2', 'password2'),
(3, 'test3', 'password3');

INSERT INTO products(id, name, description, created, modified)
VALUES
(1, 'Headphones', 'Bluetooth headphones', '2017-08-11 02:34:11', '2019-09-11 02:34:11'),
(2, 'Headphones', 'Stereo wired headphones', '2016-12-11 02:34:11', '2018-04-11 02:34:11'),
(3, 'Walkman', 'Bluetooth walkman', '2018-09-11 02:34:11', '2019-09-11 02:34:11'),
(4, 'Guitar', 'Wired flame guitar', '2017-08-11 02:34:11', '2017-09-11 04:24:11'),
(5, 'Microphone', 'Stereo professional microphone', '2017-08-11 02:34:11', '2017-09-12 02:34:11'),
(6, 'Piano', 'Classic piano', '2015-08-11 02:34:11', '2020-10-11 02:34:11'),
(7, 'CD', 'Fine selection of music', '2018-08-11 02:34:11', '2018-11-11 02:34:11'),
(8, 'Drums', 'Natural drums', '2008-08-11 02:34:11', '2020-10-11 02:34:11');

INSERT INTO transactions (id,  user_id, transaction_type, amount, created, confirmed, product_id)
VALUES
(1, 1, 0, 12, '2021-08-05 09:08:08', 0, 1),
(2, 1, 0, 24.5, '2021-06-05 09:08:27', 1, 1),
(3, 1, 1, 32.3, '2022-02-07 01:44:24', 0, 1),
(4, 2, 0, 29.4, '2020-08-11 02:52:11', 0, 4),
(5, 3, 0, 29.4, '2020-06-10 02:31:11', 0, 3),
(6, 2, 1, 29.4, '2019-08-11 02:39:11', 0, 4),
(7, 3, 0, 24.3, '2019-04-01 02:37:11', 0, 2),
(8, 2, 0, 35.4, '2020-02-08 02:24:11', 0, 1),
(9, 2, 0, 29.4, '2019-10-10 02:14:11', 0, 5),
(10, 2, 1, 29.4, '2020-04-03 01:04:11', 0, 7),
(11, 3, 0, 29.4, '2019-06-08 01:34:11', 0, 5),
(12, 2, 0, 29.4, '2019-12-11 05:34:11', 0, 1);
