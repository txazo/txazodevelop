show full processlist;

explain select * from UserInfo where memberId = 1;

show create table UserInfo\G;

show index from UserInfo;

show table status;
show table status like 'UserInfo'\G;

show [session | global] status;
show session status like 'Created%';

show [session | global] variables;
show session variables like 'query%';

use test; show table status;
select * from information_schema.tables where table_schema = 'test';

MYSQL_HOME/db/table.MYD
MYSQL_HOME/db/table.MYI