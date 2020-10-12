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

create table policy_record(
    id int NOT NULL AUTO_INCREMENT,
    policy int NOT NULL,
    user_id int NOT NULL,
    expiry_date date NOT NULL,
    status varchar(15) NOT NULL,
    PRIMARY KEY (id),
    foreign key (policy) references policy(id),
    foreign key (user_id) references user(id)
);

create table vehicle(
    id int NOT NULL AUTO_INCREMENT,
    vehicle_number varchar(12) NOT NULL,
    document varchar(100) NOT NULL,
    record_id int,
    user int NOT NULL,
    PRIMARY KEY (id),
    foreign key (user) references user(id),
    foreign key (record_id) references policy_record(id)
);