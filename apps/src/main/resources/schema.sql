CREATE TABLE user_tbl (
   id int primary key,
   username varchar(20),
   password varchar(20)
);

CREATE TABLE room_tbl (
   id int primary key,
   kind varchar(20),
   price int,
   is_booked boolean
);

CREATE TABLE reservation_tbl (
    id int primary key,
    user_id int,
    subtotal int

);

CREATE TABLE reservation_detail_tbl (
    id int primary key,
    room_id int,
    price int,
    is_check_in boolean,
    is_check_out boolean
);