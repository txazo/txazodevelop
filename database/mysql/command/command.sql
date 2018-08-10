// MySQL授权

GRANT ALL PRIVILEGES ON txazo.* TO txazo@183.62.134.100 IDENTIFIED BY 'txazo' [WITH GRANT OPTION];
grant select on txazo.* to txazo@'%'  
grant insert on txazo.* to txazo@'%'  
grant update on txazo.* to txazo@'%'  
grant delete on txazo.* to txazo@'%'
grant select, insert, update, delete on txazo.* to txazo@'%'

// MySQL取消授权
REVOKE ALL PRIVILEGES ON stat.* FROM txazo@183.62.134.100;