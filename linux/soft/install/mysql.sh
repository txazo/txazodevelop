# ********************< MySQL卸载 >********************
service mysqld stop
rpm -qa | grep mysql
# mysql-5.1.69-1.el6_4.x86_64
# mysql-devel-5.1.69-1.el6_4.x86_64
# mysql-libs-5.1.69-1.el6_4.x86_64
# mysql-server-5.1.69-1.el6_4.x86_64
rpm -e mysql-devel-5.1.69-1.el6_4.x86_64
rpm -e mysql-server-5.1.69-1.el6_4.x86_64
rpm -e mysql-5.1.69-1.el6_4.x86_64
rm -rf /var/lib/mysql

# ********************< MySQL安装 >********************
yum install mysql-server mysql mysql-devel

# MySQL启动
service mysqld start
service mysqld restart

# 设置MySQL开机启动
chkconfig mysqld on

# MySQL设置root密码
mysqladmin -u root password 'txazo1218'
/usr/bin/mysqladmin -u root password 'txazo1218'

# MySQL登录
mysql -uroot -ptxazo1218

# MySQL目录
/var/lib/mysql
/usr/share/mysql

# MySQL配置文件
cat /etc/my.cnf
[mysqld]
datadir=/var/lib/mysql
socket=/var/lib/mysql/mysql.sock
user=mysql
# Disabling symbolic-links is recommended to prevent assorted security risks
symbolic-links=0

[mysqld_safe]
log-error=/var/log/mysqld.log
pid-file=/var/run/mysqld/mysqld.pid

/var/lib/mysql，MySQL数据库文件存放目录
/var/log/mysqld.log，MySQL数据库日志文件

# 查看MySQL端口
netstat -tlnp | grep mysqld

# 设置MySQL访问权限
GRANT ALL PRIVILEGES ON blog.* TO blog@127.0.0.1 IDENTIFIED BY "blog";
GRANT ALL PRIVILEGES ON blog.* TO blog@112.124.6.220 IDENTIFIED BY "blog";
GRANT ALL PRIVILEGES ON blog.* TO blog@183.62.134.100 IDENTIFIED BY "blog";
GRANT ALL PRIVILEGES ON blog.* TO blog@14.103.65.198 IDENTIFIED BY "blog";

GRANT ALL PRIVILEGES ON jms.* TO txazo@183.62.134.100 IDENTIFIED BY "txazo";

GRANT ALL PRIVILEGES ON *.* TO txazo@112.124.6.220 IDENTIFIED BY "txazo";

GRANT ALL PRIVILEGES ON *.* TO txazo@116.77.170.159 IDENTIFIED BY "txazo";
GRANT ALL PRIVILEGES ON *.* TO txazo@183.62.134.100 IDENTIFIED BY "txazo";

GRANT ALL PRIVILEGES ON test.* TO txazo@localhost IDENTIFIED BY "txazo";

GRANT ALL PRIVILEGES ON test.* TO txazo@116.77.161.71 IDENTIFIED BY "txazo";

GRANT ALL PRIVILEGES ON *.* TO txazo@183.62.134.100 IDENTIFIED BY "txazo";