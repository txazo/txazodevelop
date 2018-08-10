Oracle 11g学习指导
/*  **********************************************************************************************************************  */
1 SQL语言
1.1 DDL数据定义语言
    create alter drop [rename truncate comment]
1.2 DML数据操作语言
    select insert update delete [merge]
1.3 DCL数据控制语言
    grant deny revoke [commit rollback savepoint]

/*  **********************************************************************************************************************  */
2 SQL Plus
2.1 SQL Plus方式登录
    scott/tiger[@[127.0.0.1:1521/]orcl] [as sysdba]
2.2 SQL Plus命令登录
    sqlplus scott/tiger[@[127.0.0.1:1521/]orcl] [as sysdba]
2.3 SQL Plus连接用户
    conn[ect] txazo/txazo[@[127.0.0.1:1521/]orcl] [as sysdba]

/*  **********************************************************************************************************************  */
3 SQL Developer
3.1 SQL Developer新建连接
    连接名：scott@orcl
	用户名：scott
	口令：tiger
	主机名：127.0.0.1
	端口：1521
	SID：orcl

/*  **********************************************************************************************************************  */
4 Oracle专有名词
4.1 Oracle全局数据库名
    1)数据库的唯一标识，参数db_name表示。一台机器上装了多个数据库，每个数据库都有一个数据库名
	2)select name from v$database
	3)show parameter db
	4)参数文件$ORACLE_HOME$/database/SPFILE$DB_NAME$.ORA中的db_name='orcl'
4.2 Oracle系统标识符SID(Oracle System Identity)
    1)用于和操作系统进行联系的标识，参数instance_name表示
	2)select instance_name from v$instance
	3)show parameter instance
4.3 数据库用户user
    用户视图dba_users
	select name from dba_users
	解锁用户：alter user hr account unlock identified by hr
4.4 数据库模式schema

/*  **********************************************************************************************************************  */
5 Oracle监听器
5.1 监听器功能
    1)监听客户端请求，默认监听端口：1521
	2)为客户端请求分配Server Process
	3)注册实例服务
5.2 监听器操作
    1)cmd -> lsnrctl -> lsnrctl>
	    status 监听器状态
	    stop 停止监听器
	    start 启动监听器
	2)cmd -> lsnrctl status 监听器状态
	3)cmd -> lsnrctl stop 停止监听器
	4)cmd -> lsnrctl start 启动监听器

/*  **********************************************************************************************************************  */
6 Oracle启动与停止
6.1 启动Oracle实例
    oradim -startup -sid orcl
6.2 停止Oracle实例
    oradim -shutdown -sid orcl
6.3 SQL Plus启动数据库
    1)startup nomount 启动实例不加载数据库
	2)startup mount 加载数据库不打开数据库
	3)startup [open] 打开数据库
	4)startup force 重启
	5)启动Oracle数据库顺序
        sqlplus /nolog
	    conn / as sysdba
	    startup nomount
	    alter database mount;
	    alter database open;
6.5 SQL Plus关闭数据库
    1)shutdown [normal] 正常关闭
	2)shutdown immediate 立即关闭
	3)shuwdown transactional 关闭事务
	4)shutdown abort 强制关闭
	

/*  **********************************************************************************************************************  */
7 Oracle权限
7.1 系统权限
    1)指创建Table、创建View、创建TableSpace等DDL操作权限以及对系统修改、用户管理等方面的操作权限
	2)所有的系统权限位于system_privilege_map表中
	    select * from system_privilege_map;  /* Oracle包含的所有系统权限 */
	3)查看用户拥有的系统权限
	    select * from dba_sys_privs where grantee = 'SCOTT';  /* 用户SCOTT拥有的系统权限 */
	  当前用户的系统权限
	    select * from user_sys_privs;  /* 当前用户拥有的系统权限 */
	4)系统权限授权和收权
	    grant create session to txazo[ with admin option];  /* 授权给用户TXAZO */
		revoke create session from txazo;  /* 撤销授权 */
7.2 对象权限
    1)指将用户自己创建的对象授权给其它用户
	2)查看用户拥有的对象权限
	    select * from dba_tab_privs where grantee = 'SCOTT' or grantor = 'SCOTT';  /* 用户SCOTT拥有的对象权限 */
	  当前用户的对象权限
	    select * from user_tab_privs;  /* 当前用户拥有的对象权限 */
	3)对象权限授权和收权
	    grant select on music to txazo[ with grant option];  /* 授权给用户TXAZO */
		revoke select on music from txazo;  /* 撤销授权 */
	4)
7.3 权限操作
    1)public代表所有用户
	    grant create session to public;  /* 授权给所有用户 */
	2)对象权限可以控制到列
	    grant update(username) on user to txazo;  /* 更新user.username的权限 */
		grant insert(password) on user to txazo;  /* 插入user.password的权限 */

/*  **********************************************************************************************************************  */
8 Oracle角色
8.1 角色
    1)角色是一组权限的集合，将角色赋给一个用户，该用户就拥有了这个角色中的所有权限
    2)Oracle内置角色
        CONNECT：拥有create session权限
		    select * from dba_sys_privs where grantee = 'CONNECT';  /* 角色CONNECT拥有的所有系统权限 */
        RESOURCE：一般用来授予开发人员
		    select * from dba_sys_privs where grantee = 'RESOURCE';  /* 角色RESOURCE拥有的所有系统权限 */
        DBA：拥有所有系统权限
		    select * from dba_sys_privs where grantee = 'DBA';  /* 角色DBA拥有的所有系统权限 */
		赋予一般用户CONNECT和RESOURCE角色
		    grant resource, connect to txazo;  /* 授予角色RESOURCE和CONNECT给用户TXAZO */
		查看Oracle的所有角色
            select * from dba_roles;  /* 所有角色 */
	3)角色管理
	    create role role_txazo;  /* 创建角色 */
		grant create session to role_txazo[ with admin option];  /* 授权给角色 */
		grant role_txazo to txazo;  /* 授予角色给用户 */
		revoke role_txazo from txazo;  /* 撤销授予给用户的角色 */
		drop role role_txazo;  /* 删除角色 */
8.2 用户和角色
    1)查看用户拥有的角色
	    select * from dba_role_privs where grantee = 'TXAZO';  /* 用户TXAZO拥有的所有角色 */
	2)当前用户的角色以及相应的权限
	    select * from role_sys_privs;  /* 当前用户拥有的所有系统权限角色以及角色相应的系统权限 */
		select * from role_tab_privs;  /* 当前用户拥有的所有对象权限角色以及角色相应的对象权限 */
	3)角色包含的系统权限
	    select * from dba_sys_privs where grantee = 'RESOURCE';  /* 角色RESOURCE包含的系统权限 */
		select * from role_sys_privs where role = 'RESOURCE';  /* 角色RESOURCE包含的系统权限 */
    4)角色包含的对象权限	
	    select * from role_tab_privs where role = 'ROLE_TXAZO';  /* 角色ROLE_TXAZO包含的对象权限 */

/*  **********************************************************************************************************************  */
9 Oracle数据完整性约束
    1)primary key：主键
	    create table user(
		    id number(10),
		    username varchar(20),
		    password varchar(20),
		    constraint user_id_pk primary key(id)  /* 主键约束 */
		);
	2)foreign key：外键
	    create table user(
		    id number(10),
		    username varchar(20),
		    password varchar(20),
		    music_id number(10),
		    constraint user_id_pk primary key(id),
		    constraint user_music_id_fk foreign key(music_id) references music(id) [on delete cascade]  /* 外键约束 */
		);  /* user参照表，music被参照表 */
		[on delete cascade]：被参照的数据删除后，参照的数据将一并删除
		[on delete set null]：被参照的数据删除后，参照的数据对应的外键将变为null值
	3)unique：唯一
	    create table user(
		    id number(10),
		    username varchar(20) constraint user_username_unique unique,  /* 唯一键约束 */
		    password varchar(20),
		    music_id number(10),
		    constraint user_id_pk primary key(id),
		    constraint user_music_id_fk foreign key(music_id) references music(id)
		);
	4)not null：非空
	    create table user(
		    id number(10),
		    username varchar(20) constraint user_username_unique unique constraint user_username_notnull not null,  /* 非空约束 */
		    password varchar(20) constraint user_password_notnull not null,  /* 非空约束 */
		    music_id number(10),
		    constraint user_id_pk primary key(id),
		    constraint user_music_id_fk foreign key(music_id) references music(id)
		);
	5)check：值域限制
	    create table user(
		    id number(10),
		    username varchar(20) constraint user_username_unique unique constraint user_username_notnull not null,
		    password varchar(20) constraint user_password_notnull not null,
		    music_id number(10),
		    constraint user_id_pk primary key(id),
		    constraint user_music_id_fk foreign key(music_id) references music(id)，
			constraint user_username_check check(username in('admin', 'manager', 'root'))  /* 值域约束 */
		);
		check(age > 20)  /* 值域约束 */

/*  **********************************************************************************************************************  */
10 Oracle事务隔离机制
10.1 并发事务问题
    1)幻想读
	    事务A读，事务B插入新记录，事务A再次读，结果集中有B插入的记录
	2)不可重复读
	    事务A读，事务B修改A读的记录，事务A再次读，读取的记录不同
	3)脏读
	    事务A更新记录，未提交，事务B读取更新后的记录，然后事务A执行回滚，事务B读取的记录无效
10.2 Oracle事务隔离级别
    1)READ COMMITTED：允许幻想读和不可重复读，不允许脏读，Oracle默认隔离级别
	2)SERIALIZABLE：都不允许
	3)设置隔离级别
	    set transaction isolation level READ [COMMITTED | SERIALIZABLE]  /* 设置Oracle事务隔离级别 */

/*  **********************************************************************************************************************  */
11 Oracle事务控制
11.1 事务开始
    SQL中，事务开始是隐含的
11.2 事务提交
    commit  /* 事务提交 */
11.3 事务回滚
    rollback  /* 事务回滚 */
	rollback to savepoint a;  /* 事务回滚到检查点 */
	savepoint a;  /* 设置检查点 */

/*  **********************************************************************************************************************  */
12 Oracle DDL语句
12.1 表Table
    1)创建表
	    create table t_user(
		    id number(10) primary key,
			username varchar(20),
			password varchar(20) default '123456'
		);  /* 创建表 */
	2)删除表
	    drop table user;  /* 删除表 */
	3)更新表
	    alter table user
		    add nickname varchar(20),  /* 添加 */
			drop password,  /* 删除 */
			modify username not null;  /* 修改 */
	4)表的描述
	    desc[ribe] user;
	5)删除表数据
	    truncate table user;  /* 清空表记录，效率高 */
		delete from user;  /* 修改表记录，效率低 */
12.2 约束Constraint
    1)添加约束
        alter table user modify password not null;  /* 增加非空约束 */
	    alter table user add constraint user_username_unique unique(username);  /* 增加唯一键约束 */
	2)删除约束
	    alter table user drop constraint user_username_unique;  /* 删除唯一键约束 */
		alter table user drop primary key [cascade];  /* 删除主键 */
	3)当前用户某个表的所有约束
	    select constraint_name,constraint_type from user_constraints where table_name='MUSIC';  /* 当前用户的表MUSIC的所有约束 */
12.3 索引Index
    1)创建索引
	    create index user_username_index on user(username);  /* 创建索引 */
		create unique index user_username_index on user(username);  /* 创建唯一索引 */
		当创建primary key(主键)或unique(唯一约束)时，唯一索引将被自动创建
		create index user_username_password_index on user(username, password);  /* 创建组合索引 */
	2)删除索引
	    drop index user_username_index;  /* 删除索引 */
	3)当前用户的所有索引
	    select index_name, index_type from user_indexes;  /* 查看当前用户的所有索引 */
12.4 视图View
    1)定义视图
	    create view user_view(username, password) as
		    select username, password from user;  /* 创建视图 */
	2)删除视图
	    drop view user_view;  /* 删除视图 */
	3)视图的描述
	    desc[ribe] user_view;  /* 视图的描述 */
	4)当前用户的所有视图
	    select view_name from user_views;  /* 查看当前用户的所有视图 */
12.5 序列Sequence	
    1)创建序列
	    create sequence user_sequence
		    minvalue 1  /* 最小值1 */
			maxvalue 999999999  /* 最大值999999999 */
			start with 1  /* 开始值1 */
			increment by 1  /* 自增数1 */
			cache 20 | nocache  /* 缓存20 */
			cycle | nocycle  /* 循环 */
			order;  /* 排序 */
	2)删除序列
	    drop sequence user_sequence;  /* 删除序列 */
	3)查看当前用户拥有的序列
	    select * from user_sequences;  /* 当前用户的所有序列 */
	4)序列绑定到表字段
	    insert into user values(user_sequence.nextval, 'root', 'root');  /* 将序列添加到表中 */
	5)查看当前序列
	    select user_sequence.currval from user;  /* 查看当前序列的值 */

/*  **********************************************************************************************************************  */
13 Oracle DML语句
13.1 insert语句
    insert into user values(1, 'root', 'root');  /* 插入数据 */
	insert into user(username, password) values('root', 'root');  /* 插入数据 */
	insert into user (select * from user);  /* 插入子查询的结果集 */
13.2 update语句
    update user set password = '123456';  /* 更新所有记录的相应字段 */
	update user set password = 'root' where username = 'root';  /* 更新满足条件的记录的相应字段 */
13.3 delete语句
    delete from user;  /* 删除表的全部记录 */
	delete from user where username = 'root';  /* 删除满足条件的记录 */

/*  **********************************************************************************************************************  */
14 Oracle 查询语句
14.1 基本查询
    1)查询所有字段
	    select * from user;
	2)查询指定字段
	    select username from user;
	3)字段别名
	    select username [as] “姓名” from user;
	4)表别名
	    select u.username from user u;
	5)常量值
	    select username, '2012' as time from user;
	6)where条件查询
	    select * from user where username = 'root';
	7)distinct去掉重复行
	    select distinct password from user;
	8)算术表达式(+ - * /)
	    select username, age + | - | * | / 10 as age from user;
	9)and
	    select * from user where username = 'root' and password = 'root';
	10)or
	    select * from user where username = 'root' or password = 'root';
	11)[not] between and
	    select * from user where age [not] between 20 and 30;
	12)[not] in
	    select * from user where username [not] in ('root', 'admin', 'manager');
	13)比较运算(= < <= > >= !=)
	    select * from user where age = < | <= | > | >= | != 20;
	14)like模糊查询
	    select * from user where username [not] like '%oo%' | 'roo_';
	15)order by查询结果排序
	    select * from user order by username desc | asc;
	16)is null空值查询
	    select * from user where password is null;
	17)group by分组查询
	    select password, count(*) from user group by password;
	18)having分组查询条件
	    select password, count(*) from user 
		    group by password having password in('111111', '123456'); 
14.2 关联查询
    1)等值连接
	    select u.username, m.title
		    from user u, music m
			where m.user_id = u.id and u.username = 'admin';
	2)非等值连接：笛卡尔积
	    select u.username, m.title
		    from user u, music m
			where u.username = 'admin';
	3)内连接inner join：一般的等值连接，同(1)
	    select u.id, m.id
		    from user u inner join music m
		    on(m.user_id = u.id);
	4)外连接
	    左外连接：left outer join
		    select u.id, m.id
		        from user u left outer join music m
		        on(m.user_id = u.id);
		右外连接：right outer join
		    select u.id, m.id
		        from user u right outer join music m
		        on(m.user_id = u.id);
		全外连接：full outer join
		    select u.id, m.id
		        from user u full outer join music m
		        on(m.user_id = u.id);
	5)自连接
	    select m.username, f.username
		    from user u, user f
			where u.friend_id = f.id;
14.3 子查询
    1)单属性单元组(单行子查询= > >= < <= !=)
	    select * from user
		    where age > (select avg(age) from user);
	2)单属性多元组(多行子查询in all any)
	    select * from user
		    where username in (select password from user);
    3)多属性多元组(多行子查询in all any)
	    select * from user
		    where (username, password) in 
			(select username, password from user where username = 'root');
		insert into user
		    (select * from user);
	4)内嵌视图
	    select u.username, v.username
		    from user u, (select username, password from user) v
			where u.username > v.username;
14.4 Oracle查询的交并差
    1)交intersect
	    select * from user
		intersect
		select * from user where username = 'root';
	2)并union
	    select * from user where username = 'admin'
		union
		select * from user where username = 'root';
	3)差minus
	    select * from user
		minus
		select * from user where username = 'root';

/*  **********************************************************************************************************************  */
15 Oracle DCL语句
15.1 commit语句
    commit  /* 事务提交 */
15.2 rollback语句
    rollback  /* 事务回滚 */
	rollback to p_a  /* 事务回滚到检查点 */
15.3 savepoint语句
    savepoint p_a  /* 设置检查点 */

/*  **********************************************************************************************************************  */
16 Oracle存储过程
16.1 存储过程
    1)存储过程是指用于特定操作的PL/SQL块，是由流控制和SQL语句书写的过程
    2)存储过程结构
	    create [or replace] procedure procedure_name(
		parameter1 mode datatype,  /* parameter1参数，mode参数模式，datatype参数类型(不能指定长度) */
		parameter2 mode datatype,
		...)
		is | as
		begin
		    PL/SQL Block;  /* PL/SQL代码块 */
		end;
	3)删除存储过程
	    drop procedure procedure_name;  /* 删除存储过程 */
	4)编译存储过程
	    alter procedure procedure_name compile;  /* 编译存储过程 */
	5)调用执行存储过程
	    exec[ute] procedure_name[(parameter1, ...)];  /* 执行存储过程 */
		call procedure_name([parameter1, ...]);  /* 调用存储过程 */
		exec procedure_name;  /* 无参 */
		call procedure_name();  /* 无参 */
	6)查看存储过程的代码
	    select text from user_source where name = 'procedure_name';  /* 查看存储过程的代码 */
16.2 存储过程实例
    1)插入
	    create or replace procedure insert_into_student(
        p_username in varchar,
        p_password in varchar
		)
        is
        p_id number;
        begin
            select student_sequence.nextval into p_id from dual;
            insert into student values(p_id, p_username, p_password);
        end;
	2)删除
	    create or replace procedure delete_from_student(
        p_username in varchar
		)
        is
        begin
            delete from student where username = p_username;
        end;
	3)更新
	    create or replace procedure update_to_student(
        p_username in varchar,
		p_password in varchar
		)
        is
        begin
            update set student password = p_password
			    where username = p_username;
        end;
	4)查询
		
/*  **********************************************************************************************************************  */
17 Oracle PL/SQL

/*  **********************************************************************************************************************  */
18 Oracle触发器
18.1 触发器
    1)触发事件是在数据库表上执行的DML(insert update delete)操作
	2)触发器结构
	    create [or replace] trigger trigger_name
		{before | alter} {insert | delete | update [of column_name]} 
		or {insert | delete | update [of column_name]}
		on table_name  /* 表 */
		when trigger_condition  /* 触发条件 */
		[for each row]  /* 行级触发器 */
		declare
		    Declare Block;  /* 声明部分 */
		begin
		    PL/SQL Block;  /* PL/SQL块 */
		end;
	2)删除触发器
	    drop trigger trigger_name;  /* 删除触发器 */
	3)设置触发器状态
	    alter trigger trigger_name {disable | enable};  /* 设置触发器可用状态 */

/*  **********************************************************************************************************************  */
19 Oracle单行函数
19.1 字符函数 
    1)lower()：转换为小写
	    select lower(username) from student;
	2)upper()：转换为大写
	    select upper(username) from student;
	3)initcap()：首字母大写
	    select initcap(username) from student;
	4)concat()：字符串连接
	    select concat(username, password) from student;
	5)substr：截取字符串
	    select substr(username, 1, 2) from student;
	6)length()：字符串长度
	    select length(username) from student;
19.2 数值函数
    1)round()：四舍五入
	    select round(23.435, 2) from dual;
	2)trunc()：截取
	    select trunc(23.435, 2) from dual;
19.3 日期函数
    1)当前系统日期：默认格式：DD-MM月-YY
	    select sysdate from dual;
	2)months_between()：两个日期之间相差的月数
	    select months_between(sysdate + 31, sysdate) from dual;
	3)add_months()：日期加上几个月后的日期
	    select add_months(sysdate, 2) from dual;
	4)next_day()：下一个星期几的日期
	    select next_day(sysdate, '星期五') from dual;
	5)last_day()：当月最后一天的日期
	    select last_day(sysdate) from dual;
19.4 转换函数
	1)字符串->日期：to_date(char, 'format')
	    select to_date('2012-08-08', 'yyyy-mm-dd') from dual;
	2)日期->字符串：to_char(date, 'format')
	    select to_char(sysdate, 'yyyy-mm-dd day am hh:mi:ss') from dual;
	3)日期字符串转换规则
	    yy | yyyy  /* 年 */
	    mm  /* 月 */
	    d | dd | ddd  /* 日(某星期的第几天 | 某月的第几天 | 某年的第几天) */
	    day  /* 星期几 */
	    am | pm  /* 上午/下午(随便选一个即可) */
	    hh | hh12 | hh24  /* 小时(12小时制 | 12 小时制 | 24小时制) */
	    mi  /* 分 */
	    ss  /* 秒 */
	    'format'中可以直接使用如下字符(-:/)
		'format'中要显示其它文本，要用双引号""括起来
		    select to_char(sysdate, '"日期："yyyy-mm-dd day am hh:mi:ss') from dual;
	    'format'中可以在模式串的开头使用fm标记以去掉数字前面的零
		    select to_char(sysdate, 'fmyyyy-mm-dd day am hh:mi:ss') from dual;

/*  **********************************************************************************************************************  */
20 Oracle组函数
20.1 min()：最小值
    select min(age) from student;
20.2 max()：最大值
    select max(age) from student;
20.3 avg()：平均值
    select avg(age) from student;
20.4 count()：结果集数
    select count(distinct age) from student;
20.5 sum()：总值
    select sum(age) from student;

/*  **********************************************************************************************************************  */

/*  **********************************************************************************************************************  */

/*  **********************************************************************************************************************  */

/*  **********************************************************************************************************************  */

/*  **********************************************************************************************************************  */

/*  **********************************************************************************************************************  */

/*  **********************************************************************************************************************  */

/*  **********************************************************************************************************************  */

/*  **********************************************************************************************************************  */

/*  **********************************************************************************************************************  */

/*  **********************************************************************************************************************  */

/*  **********************************************************************************************************************  */