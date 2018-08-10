Oracle用户的概念
	1)用户拥有相应的模式(schema)、权限、角色、存储设置、空间限额、存取资源限制、数据库审计等
	2)每个用户都有一个口令，使用正确的用户/口令才能登录到数据库进行数据存取

用户默认表空间

用户和表空间
1)一个用户可以使用多个表空间
2)一个表空间可以供多个用户使用
3)一个表空间对应多个物理的数据文件，一个数据文件只能属于一个表空间

创建临时表空间
create temporary tablespace txazo_temp
tempfile 'J:\Oracle\app\Administrator\oradata\orcl\TXAZO_TEMP01.DBF'
size 50M
autoextend on
next 50M maxsize 100M
extent management local;

创建表空间
create tablespace txazo
logging
datafile 'J:\Oracle\app\Administrator\oradata\orcl\TXAZO01.DBF'
size 50M
autoextend on
next 50M maxsize 200M
extent management local;

alter user txazo
default tablespace txazo
temporary tablespace txazo_temp
quota unlimited on txazo;

创建表时指定表空间
create table table_name() tablespace tablespace_name;
	
查看所有的用户
select username from dba_users;

创建用户
create user username 
identified by password
default tablespace tablespace_name  /* 默认表空间 */
temporary tablespace tablespace_name  /* 临时表空间 */
profile profile_name  /* 用户资源文件 */
quota integer | unlimited on tablespace_name;    /* 用户在表空间上的空间使用限额 */

create user alps 
identified by alps
default tablespace users  
temporary tablespace temp
profile default  
quota 10M on users
quota 10M on system;

alter user username [...];

password修改当前用户密码
password username修改用户username的密码

删除用户
drop user username;