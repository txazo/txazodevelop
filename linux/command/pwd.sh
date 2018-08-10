# ********************< pwd >********************
# 显示当前目录

pwd

# 显示真实目录，而非连接挡目录
pwd -P

cd /var/mail
pwd
# /var/mail
pwd -P
# /var/spool/mail
ll /var/mail
# lrwxrwxrwx. 1 root root 10 Dec 17  2012 /var/mail -> spool/mail