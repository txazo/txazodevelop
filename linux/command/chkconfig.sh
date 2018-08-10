# ********************< chkconfig >********************
# 启动停止和查询系统服务的运行级别
# 系统服务有7个运行级别(0-6)，每个运行级只能有一个启动脚本或停止脚本

# 添加服务(从/etc/rc[0-6].d中添加S90resin连接，删除K10resin连接)
chkconfig --add resin

# 删除服务(从/etc/rc[0-6].d中添加K10resin连接，删除S90resin连接)
chkconfig --del resin

# 列出服务
chkconfig --list resin
resin           0:off   1:off   2:on    3:on    4:on    5:on    6:off

# 列出所有服务
chkconfig [--list]

# 运行级别上开启服务(默认开启2345)
chkconfig resin on
chkconfig --level 2345 resin on

# 运行级别上关闭服务(默认关闭0123456)
chkconfig resin off
chkconfig --level 2345 resin off

# 系统服务脚本(/etc/init.d下)
# chkconfig: 2345 90 10

### BEGIN INIT INFO
# Provides:          resin
# Required-Start:    $remote_fs $network
# Required-Stop:     $remote_fs $network
# Default-Start:     2 3 4 5
# Default-Stop:      0 1 6
# Short-Description: The Resin Java Application Server
### END INIT INFO

# 添加服务
# 1. 服务脚本放到/etc/init.d下
# 2. chkconfig -add servicename

# Shell中判断服务是否自启动(至少有一个运行级为on)
chkconfig resin && echo "on" || echo "off"

# Shell中判断服务在某个运行级上是否自启动
chkconfig resin --level 4 && echo "on" || echo "off"