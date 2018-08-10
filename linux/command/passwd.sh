# ********************< passwd >********************
# 更改用户密码

# 更改当前用户密码
passwd

# 更改用户密码
passwd txazo

# 锁定帐号
passwd -l txazo

# 解锁帐号
passwd -u txazo

# Shell脚本中更改密码
echo "txazo1218" | passwd --stdin txazo
passwd --stdin txazo<< EOF
txazo
EOF