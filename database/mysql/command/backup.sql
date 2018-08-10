1. copy
cp -r $MYSQL_HOME/db $BACKUP_HOME
cp -r $MYSQL_HOME/db/table.* $BACKUP_HOME
scp -r $MYSQL_HOME/db mysql@112.124.6.220:$MYSQL_HOME
scp -r $MYSQL_HOME/db/table.* mysql@112.124.6.220:$MYSQL_HOME

2. mysqldump
mysqldump -uroot -proot -h127.0.0.1 db [table] > $BACKUP_HOME/back.sql
mysqldump -uroot -proot -h127.0.0.1 -d --add-drop-table db [table] > $BACKUP_HOME/back.sql
mysql -uroot -proot -h127.0.0.1 db < $BACKUP_HOME/back.sql
source $BACKUP_HOME/back.sql

mysqldump -uroot -proot -h127.0.0.1 db [table] | gzip > $BACKUP_HOME/back.sql
gzip < $BACKUP_HOME/back.sql | mysql -uroot -proot -h127.0.0.1 db

mysqldump -uroot -proot -h127.0.0.1 db [table] | mysql -uroot -proot -h112.124.6.220 db

3. load
echo 'select * from db.table' | mysql -uroot -proot -h127.0.0.1 > data.tmp
load data local file 'data.tmp' replace into table db.table