drop database IF EXISTS thinktech;

create database thinktech;

use thinktech;

drop table IF EXISTS tickets;

create table tickets(id int AUTO_INCREMENT PRIMARY KEY,subject varchar(100) not null,service varchar(100),
message text not null, priority varchar(30) default "normal" not null, date TIMESTAMP DEFAULT NOW(), status varchar(30) default "stand by" not null,progression int default 0, user_id int not null,structure_id int not null);

drop table IF EXISTS messages;

create table messages(id int AUTO_INCREMENT PRIMARY KEY,subject varchar(200) not null,
message text not null, date TIMESTAMP DEFAULT NOW(), unread boolean default true, user_id int not null,structure_id int not null);

drop table IF EXISTS tickets_comments;

create table tickets_comments(id int AUTO_INCREMENT PRIMARY KEY,message text not null,date TIMESTAMP DEFAULT NOW(),ticket_id int,createdBy int);

drop table IF EXISTS users;

create table users(id int AUTO_INCREMENT PRIMARY KEY, name varchar(300) not null,email varchar(100) not null, password varchar(100) not null,
profession varchar(100),telephone varchar(100),owner boolean);
insert into users(name,email,password) values("Malorum Diaz","malorum@gmail.com","passer");

