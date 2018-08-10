# ********************< usermod >********************
# 修改用户帐号

# 选项与参数
-u        # 修改UID
-g        # 修改用户所属的群组
-G        # 修改用户所属的附加群组
-c        # 修改帐号说明
-d        # 修改用户Home目录
-s        # 修改Shell
-l        # 修改用户名
-L        # 锁定帐号
-U        # 解锁帐号

# 修改用户帐号
usermod -u 1000 -g txazo -c txazo -d /home/txazo -s /bin/bash txazo

# 添加次要群组
usermod -ag test txazo

# 锁定帐号
usermod -L txazo

# 解锁帐号
usermod -U txazo

# 更改用户名
usermod -l newname txazo