# ********************< hostname >********************
# 主机名

# 显示主机名
hostname
echo $HOSTNAME
cat /proc/sys/kernel/hostname

# 修改主机名(临时生效)
hostname newname

# 显示IP地址(/etc/hosts)
hostname -i