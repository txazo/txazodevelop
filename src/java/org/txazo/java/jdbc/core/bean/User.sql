#drop database if exists test;

create database test default character set utf8;

create table if not exists test.User (
	id int(11) not null auto_increment comment 'id',
	user varchar(20) not null unique comment 'user',
	password varchar(20) not null comment 'password',
	primary key (id)
);