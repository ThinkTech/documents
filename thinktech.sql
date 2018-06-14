drop table IF EXISTS projects;

create table projects(id bigint AUTO_INCREMENT PRIMARY KEY,subject varchar(100) not null,service varchar(100) not null,
plan varchar(100), description text, priority varchar(30) default "normal" , date TIMESTAMP DEFAULT NOW(), status varchar(30) default "stand by" not null,duration int default 3,progression int default 0, user_id bigint not null,structure_id bigint not null,domain_id bigint not null,startedOn TIMESTAMP null,closedOn TIMESTAMP null);

drop table IF EXISTS projects_tasks;

create table projects_tasks(id bigint AUTO_INCREMENT PRIMARY KEY,name varchar(200) not null,description text,info text,priority varchar(30) default "normal" not null, duration int, status varchar(30) default "stand by" ,progression int default 0,project_id bigint not null,startedOn TIMESTAMP null,closedOn TIMESTAMP null);

drop table IF EXISTS projects_comments;

create table projects_comments(id bigint AUTO_INCREMENT PRIMARY KEY,message text not null,date TIMESTAMP DEFAULT NOW(),project_id bigint not null,createdBy bigint not null);

drop table IF EXISTS documents;

create table documents(id bigint AUTO_INCREMENT PRIMARY KEY,name varchar(300) not null, size bigint ,date TIMESTAMP DEFAULT NOW(),project_id bigint not null,createdBy bigint not null);

drop table IF EXISTS tickets;

create table tickets(id bigint AUTO_INCREMENT PRIMARY KEY,subject varchar(100) not null,service varchar(100) not null,
message text not null, priority varchar(30) default "normal" not null, date TIMESTAMP DEFAULT NOW(), status varchar(30) default "stand by",progression int default 0, closedOn TIMESTAMP null,closedBy int,user_id bigint not null,structure_id bigint not null);

drop table IF EXISTS tickets_comments;

create table tickets_comments(id bigint AUTO_INCREMENT PRIMARY KEY,message text not null,date TIMESTAMP DEFAULT NOW(),ticket_id bigint not null,createdBy bigint not null);

drop table IF EXISTS bills;

create table bills(id bigint AUTO_INCREMENT PRIMARY KEY,fee varchar(200) not null,service varchar(100) not null,code varchar(200),
date TIMESTAMP DEFAULT NOW(), status varchar(30) default "stand by",amount int not null,paidOn TIMESTAMP null,paidWith varchar(200),paidBy int,product_id bigint,discount_id bigint,group_id bigint,structure_id bigint);

drop table IF EXISTS discounts;

create table discounts(id bigint AUTO_INCREMENT PRIMARY KEY,rate int,date TIMESTAMP DEFAULT NOW(),expireOn TIMESTAMP null);

drop table IF EXISTS structures;

create table structures(id bigint AUTO_INCREMENT PRIMARY KEY, name varchar(300),acronym varchar(30),business varchar(300),size varchar(10) default "small",state varchar(100),
balance bigint,country varchar(200),city varchar(200),location varchar(200),telephone varchar(60),email varchar(100),bp varchar(60),fax varchar(60),website varchar(200));

insert into structures(id,name) values(1,"ThinkTech");

drop table IF EXISTS subscriptions;

create table subscriptions(id bigint AUTO_INCREMENT PRIMARY KEY,service varchar(300) not null,structure_id bigint not null,date TIMESTAMP DEFAULT NOW(),actived boolean default true,endedOn TIMESTAMP null);

drop table IF EXISTS domains;

create table domains(id bigint AUTO_INCREMENT PRIMARY KEY,name varchar(300) not null,extension varchar(40) not null,emailOn boolean default false,email varchar(300),plan varchar(100),year int,action varchar(30),eppCode varchar(300),price int not null,date TIMESTAMP DEFAULT NOW(),status varchar(30) default "stand by" not null,registeredOn TIMESTAMP null,user_id bigint not null,structure_id bigint not null);

drop table IF EXISTS users;

create table users(id bigint AUTO_INCREMENT PRIMARY KEY, name varchar(300) not null,email varchar(200) not null, password varchar(200) not null,
profession varchar(200),role varchar(100) not null,type varchar(200) default 'customer',telephone varchar(100),owner boolean default false,structure_id bigint not null,createdOn TIMESTAMP DEFAULT NOW());

drop table IF EXISTS accounts;

create table accounts(id bigint AUTO_INCREMENT PRIMARY KEY, activation_code varchar(100),activated boolean default false, activatedOn TIMESTAMP null, locked boolean default false,user_id bigint not null);

insert into users(id,name,email,password,role,type,structure_id) values(1,"Équipe Développement","dev@thinktech.sn","8ad7d21c71b049b7003ba31b5f1322974df77ac8","collaborateur","staff",1);
insert into accounts(activated,user_id) values(true,1);

insert into users(id,name,email,password,role,type,structure_id) values(2,"Assistance Technique","support@thinktech.sn","8ad7d21c71b049b7003ba31b5f1322974df77ac8","collaborateur","staff",1);
insert into accounts(activated,user_id) values(true,2);

drop table IF EXISTS others;

create table others(id bigint AUTO_INCREMENT PRIMARY KEY, name varchar(300),acronym varchar(30),business varchar(300),size varchar(10) default "small",state varchar(100),
type varchar(200),converted boolean,country varchar(200),city varchar(200),location varchar(200),telephone varchar(60),email varchar(100),bp varchar(60),fax varchar(60),website varchar(200),createdOn TIMESTAMP DEFAULT NOW(),createdBy bigint);

drop table IF EXISTS contacts;

create table contacts(id bigint AUTO_INCREMENT PRIMARY KEY, name varchar(300) not null,email varchar(200) not null,
profession varchar(200),telephone varchar(100),structure_id bigint not null,createdOn TIMESTAMP DEFAULT NOW());