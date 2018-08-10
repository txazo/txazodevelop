1 子程序
    1.1 子程序概念
	    子程序是带名的PL/SQL块，能够接受参数和被环境调用
	1.2 子程序分类
	    1)存储过程
		2)函数
	1.3 子程序结构
	    <header>
		{is | as}
		    [Declare Block;]
		begin
		    PL/SQL Block;
		[exception
		    Exception Block;]
		end;

2 函数
    2.1 创建
	    create [or replace] function function_name[(
		    parameter1 {[in] | out | in out} datatype,
			parameter2 {[in] | out | in out} datatype,
			...
		)]
		return return_type  /* return_type不能限定数据长度 */
		{is | as}
		    [Declare Block;]
		begin
		    PL/SQL Block;
			return return_value;
		[exception
		    Exception Block;]
		end;
	    /* 示例 */
		create or replace function get_username(
		    p_id in student.id%type
		)
		return varchar2
		is
		    v_username student.username%type;
		begin
		    select username into v_username from student
			    where id = p_id;
			return v_username;
		end;
	2.2 执行
	    variable g_username varchar2(20);
		exec :g_username := get_username(1);
		print g_username;
		
		select get_username(1) from dual;
	2.3 删除
	    drop function function_name;
		
3 包
    3.1 包简介
	    1)包是一个可以将相关对象存储在一起的PL/SQL结构，是Oracle数据库的一种模式对象
		2)包有两个独立的部分：包的规范和主体，这两部分独立的存储在数据字典中
		3)包包含的程序对象是：过程、函数、变量、常数、游标、异常
	3.2 创建
	    1)包的规范
		create or replace package package_name
		{is | as}
		    Declare Block;
			Declare PL/SQL Block;
		end package_name;
		2)包体
		create or replace package body package_name
		{is | as}
		    Declare Block;
			PL/SQL Block;
		end package_name;
		3)示例
		create or replace package my_package
		is
		    procedure my_test;
		end;
		
		create or replace package body my_package
		is
		    procedure my_test
		    is 
		    begin
    	    	dbms_output.put_line(sysdate);
		    end my_test;
		end;

	3.3 删除包
	    drop package package_name;  /* 删除包的规范 */
		drop package body package_name;  /* 删除包体 */