mysql -uroot -proot [-h127.0.0.1] [db_txazo]

select version();  //
select user();  //
select current_date;  //
select current_time;  //
select now();

\c  //撤销输入

show databases;

create database db_txazo;

use db_txazo;

show tables;

drop table if exists student;

create table student (
    id int(10) not null auto_increment,
	username varchar(10) not null,
	password varchar(10) not null,
	primary key (id)
);

desc[ribe] student;

insert into student values (1, 'txazo', '534553'), (2, 'awzsd', '524636');
insert into student values (3, 'gsdfs', '423423'), (4, 'xcxsf', '365252');
insert into student values (5, 'gresf', '347545'), (6, 'tehdf', '154363');
insert into student values (7, 'jtjtt', '967746'), (8, 'jffhd', '928463');

update student set password = '364825' where username = 'txazo';

select * from student;
select * from db_txazo.student;
select * from student where username = 'txazo';
select * from student where (username = 'txazo' and password = '364825')
    or (username = 'alps' and password = '528463');
select username as name from student;
select username, password from student;
select distinct username from student;
select username from student order by username [desc];
select username from student order by username, password [desc];

日期函数：
select curdate();
select year(curdate());
select month('2013-03-20');
select dayofmonth(curdate());
select day(curdate());
select date('2013-03-20');

NULL值操作：
select 1 is null, 1 is not null;

模式匹配(like)：
% 匹配任意数目字符
_ 匹配单个字符
select * from student where username like 't%';
select * from student where username like 'tx_zo';

扩展正则表达式匹配(regexp)：
. 匹配单个字符
* 匹配任意数目在它之前的字符
[] 匹配括号中的字符
{n} 重复n次
^ 匹配开始
select * from student where username regexp 't$';
$ 匹配结尾
select * from student where username regexp 'o$';
select * from student where username regexp binary 'o$';  //binary区分大小写
select * from student where username regexp 'o';  //包含字符o
select * from student where username regexp '^.....$';  //匹配正好包含5个字符
select * from student where username regexp '^.{5}$';

计数行：
count()
select count(*) from student;
select username, count(*) from student group by username;

获得数据库和表的信息：
show databases;
select database();  //当前的数据库
show tables;
desc student;

某行是否存在：
select count(1) from student where username = 'txazo';

算术函数：
max() 最大值
min() 最小值
avg() 平均值
sum() 总和

某个列的最大值行：
select * from student where id = (select max(id) from student);
select * from student order by id desc limit 1;
select username,max(password) from student order by password desc;


alter database

alter table
alter table student add nickname varchar(10) not null;
alter table student modify nickname varchar(10);
alter table student drop nickname;

create database

create index