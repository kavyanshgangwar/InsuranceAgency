create table user(
    id int NOT NULL AUTO_INCREMENT,
    first_name varchar(255) NOT NULL,
    last_name varchar(255) NOT NULL,
    email varchar(255) NOT NULL,
    phone_no varchar(13) NOT NULL,
    password varchar(1000) NOT NULL,
    status varchar(14) NOT NULL,
    role varchar(100) NOT NULL,
    PRIMARY KEY (id)
);

create table verification_token(
  id int NOT NULL AUTO_INCREMENT,
  token varchar(1000) NOT NULL,
  user_id int NOT NULL,
  created_date DATE NOT NULL,
  expiry_date DATE NOT NULL,
  PRIMARY KEY (id),
  foreign key (user_id) references user(id)
);