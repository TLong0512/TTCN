/* CREATE TABLE roles(
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    rolename VARCHAR(150) UNIQUE NOT NULL,
    createddate DATETIME NULL,
    modifieddate DATETIME NULL,
    createdby VARCHAR(255) NULL,
    modifiedby VARCHAR(255) NULL
);

CREATE TABLE users(
	id BIGINT NOT NULL PRIMARY KEY auto_increment,
    username VARCHAR(150) UNIQUE NOT NULL,
    userpassword VARCHAR(150) NOT NULL,
    fullname VARCHAR(150) NOT NULL,
    birthday DATETIME NOT NULL,
    gender VARCHAR(5) NOT NULL,
    address VARCHAR(255) NOT NULL,
    phonenumber VARCHAR(12) NOT NULL,
	roleid BIGINT NOT NULL,
    createddate DATETIME NULL,
    modifieddate DATETIME NULL,
    createdby VARCHAR(255) NULL,
    modifiedby VARCHAR(255) NULL
);

CREATE TABLE category(
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    categoryname VARCHAR(150) NOT NULL,
    parentid BIGINT NOT NULL,
    createddate DATETIME NULL,
    modifieddate DATETIME NULL,
    createdby VARCHAR(255) NULL,
    modifiedby VARCHAR(255) NULL
);

CREATE TABLE film(
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    filmname VARCHAR(150) NOT NULL,
    length VARCHAR(150) NOT NULL,
    producer VARCHAR(150) NOT NULL,
    director VARCHAR(150) NOT NULL,
    actor VARCHAR(150) NOT NULL,
    nation VARCHAR(150) NOT NULL,
    starteddate DATETIME NOT NULL,
    categoryid BIGINT NOT NULL,
    createddate DATETIME NULL,
    modifieddate DATETIME NULL,
    createdby VARCHAR(255) NULL,
    modifiedby VARCHAR(255) NULL
);

CREATE TABLE cinema(
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    cinemaname VARCHAR(155) NOT NULL,
    address VARCHAR(255) NOT NULL,
    phonenumber VARCHAR(12) NOT NULL,
    cinemadescription VARCHAR(255) NOT NULL,
    city VARCHAR(155) NOT NULL,	
    createddate DATETIME NULL,
    modifieddate DATETIME NULL,
    createdby VARCHAR(255) NULL,
    modifiedby VARCHAR(255) NULL
);

CREATE TABLE seat(
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    seat_type VARCHAR(255) NOT NULL,
    price BIGINT NOT NULL,
    position VARCHAR(5) NOT NULL,
    available BOOL NOT NULL,
    cinemaid BIGINT NOT NULL,
    createddate DATETIME NULL,
    modifieddate DATETIME NULL,
    createdby VARCHAR(255) NULL,
    modifiedby VARCHAR(255) NULL
);

CREATE TABLE show_time(
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    specificTime DATETIME NOT NULL,
    cinemaid BIGINT NOT NULL,
    filmid BIGINT NOT NULL,
    createddate DATETIME NULL,
    modifieddate DATETIME NULL,
    createdby VARCHAR(255) NULL,
    modifiedby VARCHAR(255) NULL
);

CREATE TABLE ticket_type(
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    ticketname VARCHAR(150),
    price BIGINT NOT NULL,
    filmid BIGINT NOT NULL,
    createddate DATETIME NULL,
    modifieddate DATETIME NULL,
    createdby VARCHAR(255) NULL,
    modifiedby VARCHAR(255) NULL
);

CREATE TABLE order_ticket_type(
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,	
    quantity INT NOT NULL,
    ticket_type_id BIGINT NOT NULL,
    createddate DATETIME NULL,
    modifieddate DATETIME NULL,
    createdby VARCHAR(255) NULL,
    modifiedby VARCHAR(255) NULL
);

CREATE TABLE combo(
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    comboname VARCHAR(150),
    price BIGINT NOT NULL,
    thumbnail VARCHAR(255) NOT NULL,
    createddate DATETIME NULL,
    modifieddate DATETIME NULL,
    createdby VARCHAR(255) NULL,
    modifiedby VARCHAR(255) NULL
);

CREATE TABLE order_combo(
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    quantity INT NOT NULL,
    comboid BIGINT NOT NULL,
    createddate DATETIME NULL,
    modifieddate DATETIME NULL,
    createdby VARCHAR(255) NULL,
    modifiedby VARCHAR(255) NULL
);

CREATE TABLE order_detail(
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    seatid BIGINT NOT NULL,
    order_ticket_id BIGINT NOT NULL,
    order_combo_id BIGINT NOT NULL,
    showtime_id BIGINT NOT NULL,
    createddate DATETIME NULL,
    modifieddate DATETIME NULL,
    createdby VARCHAR(255) NULL,
    modifiedby VARCHAR(255) NULL
);

CREATE TABLE orders(
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    payment_method VARCHAR(155) NOT NULL,
    user_id BIGINT NOT NULL,
    order_detail_id BIGINT NOT NULL,
    createddate DATETIME NULL,
    modifieddate DATETIME NULL,
    createdby VARCHAR(255) NULL,
    modifiedby VARCHAR(255) NULL
); */