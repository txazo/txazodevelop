1 存储过程的概念
    1)存储过程是指用于特定操作的PL/SQL块，是由流控制和SQL语句书写的过程
	2)存储过程经编译和SQL优化后存储在数据库中
	3)用户通过指定存储过程的名字并给出参数来调用执行它
	
2 存储过程的优点
    1)提高SQL语句的功能和灵活性
	2)保证数据的安全性和完整性
	3)预编译优化，提升性能
	4)降低网络的通信量，加快执行速度
	
3 存储过程的分类
    1)无参 
	2)有参
	    in：接受输入参数
		out：输出数据
		in out：接受输入数据，并输出数据

4 无参存储过程
    1)创建
	    create [or replace] procedure procedure_name
		is | as
		    [Declare Block;]
		begin
		    PL/SQL Block;
		[exception
		    Exception Block;]
		end [procedure_name];
	2)调用
	    exec[ute] procedure_name[()];
		call procedure_name();  /* 必须带括号 */
		begin 
		    procedure_name[()];
		end;
	3)删除
	    drop procedure procedure_name;
	4)编译
	    alter procedure procedure_name compile;

5 带参存储过程
    1)创建
	    create [or replace] procedure procedure_name(
		    paramater1 [in] | out | in out datetype,
			/* datatype不能指定数据长度 */
			/* 参数：p_作为前缀 */
			paramater2 [in] | out | in out datetype,
			...
		)
		is | as
		    [Declare Block;]
		begin
		    PL/SQL Block;
		[exception
		    Exception Block;]
		end [procedure_name];
	2)调用
	    exec[ute] procedure_name(parameter1, parameter2, ...);
		call procedure_name(parameter1, parameter2, ...);
		begin
		    procedure_name(parameter1, parameter2, ...);
		end;
	3)删除
	    drop procedure procedure_name;
	4)编译
	    alter procedure procedure_name compile;