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

create table policy(
    id int NOT NULL AUTO_INCREMENT,
    name varchar(100) NOT NULL,
    category varchar(10) NOT NULL,
    premium int NOT NULL,
    max_claim_amount int NOT NULL,
    things_covered longtext NOT NULL,
    expiration_status varchar(15) NOT NULL,
    primary key (id)
);

create table faq(
    id int NOT NULL AUTO_INCREMENT,
    question text NOT NULL ,
    answer LONGTEXT NOT NULL ,
    on_topic varchar(15) NOT NULL,
    primary key (id)
);