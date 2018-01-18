drop database IF EXISTS thinktech;

create database thinktech;

use thinktech;

drop table IF EXISTS projects;

create table projects(id int AUTO_INCREMENT PRIMARY KEY,subject varchar(100) not null,service varchar(100) not null,
plan varchar(100), description text, priority varchar(30) default "normal" , date TIMESTAMP DEFAULT NOW(), status varchar(30) default "stand by" not null,duration int default 3,progression int default 0, user_id int not null,structure_id int not null);

drop table IF EXISTS tasks;

create table tasks(id int AUTO_INCREMENT PRIMARY KEY,name varchar(200) not null,description text);

insert into tasks(id,name,description) values(1,"Contrat et Caution","cette phase intiale établit la relation légale qui vous lie à ThinkTech");
insert into tasks(id,name,description) values(2,"Traitement","cette phase d'approbation est celle où notre équipe technique prend en charge votre projet");
insert into tasks(id,name,description) values(3,"Analyse du projet","cette phase est celle de l'analyse de votre projet pour une meilleure compréhension des objectifs");
insert into tasks(id,name,description) values(4,"Définition des fonctionnalités","cette phase est celle de la définition des fonctionnalités du produit");
insert into tasks(id,name,description) values(5,"Conception de l'interface","cette phase est celle de la conception de l'interface utilisateur");
insert into tasks(id,name,description) values(6,"Développement des fonctionnalités","cette phase est celle du développement des fonctionnalités du produit");
insert into tasks(id,name,description) values(7,"Tests","cette phase permet de tester les fonctionnalités du produit");
insert into tasks(id,name,description) values(8,"Validation","cette phase est celle de la validation des fonctionnalités du produit");
insert into tasks(id,name,description) values(9,"Livraison du produit","cette phase est celle du deploiement du produit final");
insert into tasks(id,name,description) values(10,"Formation","cette phase finale est celle de la formation pour une prise en main du produit");

drop table IF EXISTS projects_tasks;

create table projects_tasks(id int AUTO_INCREMENT PRIMARY KEY,info text, priority varchar(30) default "normal" not null, date TIMESTAMP DEFAULT NOW(), duration int, status varchar(30) default "stand by" ,progression int default 0, task_id int not null,project_id int not null);

drop table IF EXISTS projects_comments;

create table projects_comments(id int AUTO_INCREMENT PRIMARY KEY,message text not null,date TIMESTAMP DEFAULT NOW(),project_id int not null,createdBy int not null);

drop table IF EXISTS documents;

create table documents(id int AUTO_INCREMENT PRIMARY KEY,name varchar(300) not null, size long ,date TIMESTAMP DEFAULT NOW(),project_id int not null,createdBy int not null);

drop table IF EXISTS tickets;

create table tickets(id int AUTO_INCREMENT PRIMARY KEY,subject varchar(100) not null,service varchar(100) not null,
message text not null, priority varchar(30) default "normal" not null, date TIMESTAMP DEFAULT NOW(), status varchar(30) default "stand by",progression int default 0, closedOn TIMESTAMP null,closedBy int,user_id int not null,structure_id int not null);

drop table IF EXISTS tickets_comments;

create table tickets_comments(id int AUTO_INCREMENT PRIMARY KEY,message text not null,date TIMESTAMP DEFAULT NOW(),ticket_id int not null,createdBy int not null);

drop table IF EXISTS messages;

create table messages(id int AUTO_INCREMENT PRIMARY KEY,subject varchar(200) not null,
message text not null, date TIMESTAMP DEFAULT NOW(), unread boolean default true, user_id int not null,structure_id int not null);

drop table IF EXISTS bills;

create table bills(id int AUTO_INCREMENT PRIMARY KEY,fee varchar(200) not null,code varchar(200),
date TIMESTAMP DEFAULT NOW(), status varchar(30) default "stand by",amount int not null,paidOn TIMESTAMP null,paidWith varchar(200),paidBy int,project_id int not null);

drop table IF EXISTS structures;

create table structures(id int AUTO_INCREMENT PRIMARY KEY, name varchar(300),acronym varchar(30),business varchar(300),size varchar(10) default "small",state varchar(100),
country varchar(200),city varchar(200),location varchar(200),telephone varchar(60),email varchar(100),bp varchar(60),fax varchar(60),website varchar(200),ninea varchar(100));

insert into structures(name) values("ThinkTech");

drop table IF EXISTS users;

create table users(id int AUTO_INCREMENT PRIMARY KEY, name varchar(300) not null,email varchar(100) not null, password varchar(100) not null,
profession varchar(100),role varchar(100) not null,telephone varchar(100),owner boolean default false,structure_id int not null);

drop table IF EXISTS users;

create table users(id int AUTO_INCREMENT PRIMARY KEY, name varchar(300) not null,email varchar(100) not null, password varchar(100) not null,
profession varchar(100),role varchar(100) not null,telephone varchar(100),owner boolean default false,type varchar(50) default "customer", structure_id int not null);

create table accounts(id int AUTO_INCREMENT PRIMARY KEY, activation_code varchar(100),activated boolean default false, locked boolean default false,user_id int not null);

insert into users(name,email,password,role,type,structure_id) values("Assistance Technique","support@thinktech.sn","initial","collaborateur","staff",1);
insert into accounts(activated,user_id) values(true,1);