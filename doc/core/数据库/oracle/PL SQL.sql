1 PL/SQL简介
    1.1 PL/SQL代表面向过程化的语言与SQL语言的结合
	1.2 PL/SQL是过程语言(Procedural Language)与结构化查询语言(SQL)结合而成的编程语言
	1.3 PL/SQL程序块类型
	    匿名块：PL/SQL程序块
	    存储过程：执行特定的任务
		函数：执行任务并返回值
		触发器：自动触发执行
	    

2 PL/SQL结构
    [declare
	    Declare Block;]
		/* 变量、常量、游标、异常 */
		/* 变量：v_作为前缀
		   常量：c_作为前缀
		   游标：c_作为前缀
		   异常：e_作为前缀 */
	begin
	    PL/SQL Block;
		/* SQL语句 */
		/* PL/SQL控制语句 */
	[exception
	    Exception Block;]
		/* 异常处理 */
	end;
	/* 示例：匿名块 */
	declare
	    v_username varchar(20);
	begin
	    select username into v_username from student where id = 1;
		dbms_output.put_line(v_username);
		/* dbms_output： 包
		   put_line：存储过程 */
	exception
	    when no_data_found then
		    dbms_output.put_line('null');
	end;
	/  /* 末尾添加/可以在SQL*Plus中运行 */
	
3 Declare Block
    3.1 变量
	    1)变量声明
		    语法：name [constant] datatype [not null] [:= | default value];
            v_name varchar;
	        v_name varchar(20);
		    v_name varchar(20) := 'root';  /* 初始化 */
		    v_name varchar(20) default 'root';  /* 同上 */
		    v_name constant varchar(20) := 'root';  /* 常量，必须赋初值，不可改变 */
		    v_name varchar(20) not null := 'root';  /* 不为null，必须赋初值 */
		2)%type
		    声明变量类型为数据库表字段类型
			v_name student.name%type;
		3)%rowtype
		    声明变量类型为数据库表类型
			t_student student%rowtype;
	3.2 游标
	    1)游标声明
		    语法：cursor cursor_name is select_statement;
			/* 示例 */
			declare
			    cursor c_student is 
				    select username, password from student
					where id < 5 order by id;
		2)打开游标
		    open cursor_name;
		3)关闭游标
		    close cursor_name;
		4)游标属性
		    %found：fetch得到记录则返回true
			%notfound：fetch得不到记录则返回true
			%isopen：游标打开则返回true
			%rowcount：返回游标推进的行数
		5)游标中获取数据
		    语法：fetch cursor_name into variable1[, variable2, ...];
		    /* 示例 */
			declare 
			    v_username student.username%type;
				v_password student.password%type;
				cursor c_student is select username, password from student
				    where id < 5 order by id;
			begin
			    open c_student;
				loop
				    fetch c_student into v_username, v_password;
				    dbms_output.put_line(v_username || '/' || v_password);
				exit when c_student%notfound;
				end loop;
				close c_student;
			end;
		6)游标定义%rowtype
		    declare
			    cursor c_student is select username, password from student
				    where id < 5 order by id;
			    t_student c_student%rowtype;
		7)带参数的游标
		    declare 
			    v_username student.username%type;
				v_password student.password%type;
				cursor c_student(p_password student.password%type) is 
				    select username, password from student
				        where password = p_password order by id;
			begin
			    open c_student('123456');  /* 打开时传入参数 */
				loop
				    fetch c_student into v_username, v_password;
				    dbms_output.put_line(v_username || '/' || v_password);
				exit when c_student%notfound;
				end loop;
				close c_student;
			end;
		8)游标for循环
		    语法：
			    for record_name in cursor_name[(parameter, ...)] | query_difinition
			        loop
				        PL/SQL Block;
				    end loop;
			/* 示例 */
			declare 
			    v_username student.username%type;
				v_password student.password%type;
				cursor c_student is select username, password from student
				    where id < 5 order by id;
			begin
			    for r_student in c_student
				loop
				    dbms_output.put_line(r_student.username || '/' || r_student.password);
				end loop;
			end;
			/* 示例 */
			declare 
			    v_username student.username%type;
				v_password student.password%type;
			begin
			    for r_student in (select username, password from student
				    where id < 5 order by id)
				loop
				    dbms_output.put_line(r_student.username || '/' || r_student.password);
				end loop;
			end;
4 PL/SQL Block
    4.1 SQL赋值语句
        declare
            v_name varchar(20);
	    begin
            select name into v_name from student where id = 1;
		    dbms_output.put_line(v_name);
		end;
    4.2 赋值
        语法：变量 := 表达式;
        declare 
            v_name varchar(20);
        begin
            v_name := 'txazo';
            dbms_output.put_line(v_name);
        end;	
    4.3 字符串连接
	    语法：str1 || str2
        v_name := 'txazo' || 'txazo';	
    4.4 条件结构
        if condition then
		    PL/SQL Block 1;
		[elsif condition then  /* elsif，不能写成elseif */
		    PL/SQL Block 2;]
		[else
		    PL/SQL Block 3;]
		end if;
	4.5 循环结构
	    1)loop循环
		    loop
			    PL/SQL Block;
			exit when condition;
			end loop;
			/* 示例 */
			declare
                v_i number(10) := 1;
			begin
    			loop
                    dbms_output.put_line(v_i);
                    v_i := v_i + 1;
                exit when v_i > 10;
                end loop;
			end;
		2)while循环
		    while condition
			loop 
			    PL/SQL Block;
			end loop;
			/* 示例 */
			declare
			    v_i number(10) := 1;
			begin
			    while v_i <= 10
				loop
				    dbms_output.put_line(v_i);
                    v_i := v_i + 1;
				end loop;
			end;
		3)for循环
		    for variable in lower_data .. upper_data loop
			    PL/SQL Block;
			/* variable不必定义 */
			end loop;
			/* 示例 */
			declare
			begin
    			for v_i in 1 .. 10 loop
                    dbms_output.put_line(v_i);
                end loop;
			end;
	4.6 游标
	
5 PL/SQL记录(record)
    1)单行多列
	2)自定义记录
	    type type_name is record(
		    parameter1 datatype,
			parameter2 datatype,
			...
		);
		variable_name type_name;
		/* 示例 */
		type t_student is record(
		    id number(10),
			username student.username%type,
			password student.password%type
		);
		v_student t_student;
	3)%rowtype定义记录
	    variable_name [table_name | view_name]%rowtype;
		/* 示例 */
		v_student student%rowtype;
	4)赋值
	    v_student.username := 'root';
		/* record的列赋值 */
		select * into v_student from student;
		/* record赋值 */
	5)record插入
	    insert into student values v_student;
	6)record更新
	    v_student.id := 1;
		v_student.username := 'root';
		v_student.password := 'root';
	    update student set row = v_student where id = 1;

6 PL/SQL表(table)
    1)多行多列
	2)定义
	    type type_name is table of table_name%rowtype
		    [not null] index by key_type;
		/* key_type：表下标的数据类型
                    binary_integer
                    pls_integer
                    varchar2 */
		variable_name type_name;
		/* 示例 */
		type t_student is table of student%rowtype
		    index by binary_integer;
		v_student t_student;
	3)

7 PL/SQL嵌套表(table)

8 变长数组(varry)

9 Common Table Expression(CTE)

10 事务处理
    10.2 事务处理结构
	    begin
		    PL/SQL Block;
		    commit;
		exception
		    when others then
			    rollback;
		end;

11 异常处理
    11.1 异常处理结构
	    exception
		    when exception1 then
			    PL/SQL Block;
			[when exception2 then
			    PL/SQL Block;]
			[when others then
			    PL/SQL Block;]
    