create table user(
    id int NOT NULL AUTO_INCREMENT,
    first_name varchar(255) NOT NULL,
    last_name varchar(255) NOT NULL,
    email varchar(255) NOT NULL,
    phone_no varchar(13) NOT NULL,
    status varchar(14) NOT NULL,
    role varchar(100) NOT NULL,
    PRIMARY KEY (id)
);