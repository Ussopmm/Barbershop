INSERT INTO barbershop.t_barber (c_barber_name, c_email, c_phone_number)
VALUES
    ('Jackson','jack@gmail.com', '51411122221'),
    ('Lucas','luc@gmail.com', '51411122221'),
    ('Oliver','oli@gmail.com', '51411122221'),
    ('Theo','theok@gmail.com', '51411122221');

INSERT INTO barbershop.t_service
(c_service_name, c_duration, c_price)
VALUES
    ('Mens Haircut',30, 50),
    ('Beard Trimming',30, 20),
    ('Mens Colouration', 30, 30),
    ('Blow-dry / Brushing',30 , 20),
    ('Highlights',30, 50);

INSERT INTO barbershop.t_service_provider
(service_id, barber_id)
VALUES
    (1, 3),
    (1, 4),
    (1, 5),
    (1, 6),
    (2, 3),
    (2, 4),
    (2, 5),
    (3, 3),
    (3, 4),
    (4, 5),
    (4, 6),
    (5, 3),
    (5, 5);