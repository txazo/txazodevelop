explain

1. where子句中尽量避免使用!=或<>
where type != 1

2. where子句中尽量避免Null字段判断
where memberId is null

3. where子句中尽量避免使用or
select * from User where type = 1 or type = 2;
select * from User where type = 1
union all
select * from User where type = 2;

4. where子句中尽量避免使用in和not in

5. where子句中尽量避免对字段进行表达式操作
where id % 2 = 0

6. where子句中尽量避免对字段进行函数操作
where substring(name, 1, 3) = "txa"

7. where子句中"="左边尽量避免进行函数和表达式运算