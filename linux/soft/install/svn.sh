# ********************< Subversion安装 >********************
yum install subversion

# ********************< 配置SVN服务 >********************
mkdir /svn

svnadmin create /svn/txazo

vi /svn/txazo/conf/svnserve.conf
anon-access = none
auth-access = write
password-db = passwd
authz-db = authz

vi /svn/txazo/conf/passwd
txazo = txazo1218

vi /svn/txazo/conf/authz
[groups]
admin = txazo,test
[txazo:/]
txazo = rw
@admin = rw

# ********************< 启动SVN服务 >********************
svnserve -d -r /svn
svnserve -d --foreground -r /svn

# ********************< 更新SVN >********************
svn checkout --username txazo --password txazo1218 svn://112.124.6.220/txazo/txazodevelop /www/txazo/txazodevelop