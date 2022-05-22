INSERT INTO roles(id, name)
VALUES
(1, 'USER'),
(2, 'ADMIN');

INSERT INTO users(id, username, password)
VALUES
(1, 'test1', 'password1'),
(2, 'test2', 'password2');

INSERT INTO products(id, name, description, created, modified)
VALUES
(1, 'Headphones', 'Bluetooth headphones', '2017-08-11 02:34:11', '2019-09-11 02:34:11'),
(2, 'Headphones', 'Stereo wired headphones', '2017-12-11 02:34:11', '2018-04-11 02:34:11'),
(3, 'Walkman', 'Bluetooth walkman', '2018-09-11 02:34:11', '2019-09-11 02:34:11'),
(4, 'Guitar', 'Wired flame guitar', '2017-08-11 02:34:11', '2017-09-11 04:24:11'),
(5, 'Microphone', 'Stereo professional microphone', '2017-08-11 02:34:11', '2017-09-12 02:34:11'),
(6, 'Piano', 'Classic piano', '2020-08-11 02:34:11', '2020-10-11 02:34:11'),
(7, 'CD', 'Fine selection of music', '2018-08-11 02:34:11', '2018-11-11 02:34:11'),
(8, 'Drums', 'Natural drums', '2019-08-11 02:34:11', '2020-10-11 02:34:11');

INSERT INTO transactions (id,  user_id, transaction_type, amount, created, confirmed, product_id)
VALUES
(1, 1, 0, 12, '2020-08-05 09:08:08', 0, 1),
(2, 1, 0, 24.5, '2020-08-05 09:08:08', 1, 1),
(3, 1, 1, 32.3, '2018-09-07 01:06:24', 0, 1),
(4, 2, 0, 29.4, '2017-08-11 02:34:11', 0, 1);