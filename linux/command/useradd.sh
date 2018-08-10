# ********************< useradd >********************
# 添加用户
# 超级用户拥有此权限

# 选项与参数
-u        # UID
-g        # GID或groupname，指定用户所属的群组
-G        # 指定用户所属的附加群组
-m        # 强制创建用户Home目录(一般帐号默认)
-M        # 强制不创建用户Home目录(系统帐号默认)
-c        # 帐号说明，保存在/etc/passwd的说明栏
-d        # 指定用户Home目录
-s        # 指定Shell，默认为/bin/bash
-r        # 创建系统帐号

# 创建用户
groupadd -g 1000 txazo
useradd -u 1000 -g txazo -c txazo -d /home/txazo -s /bin/bash txazo

groupadd admin
useradd -M admin

# 创建系统帐号
useradd -r admin

# 查看useradd的默认值
useradd -D
# GROUP=100
# HOME=/home
# INACTIVE=-1
# EXPIRE=
# SHELL=/bin/bash
# SKEL=/etc/skel
# CREATE_MAIL_SPOOL=yes