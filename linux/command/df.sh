# ********************< df >********************
# 文件系统的磁盘使用情况
# 默认以KB为单位显示

# 列出文件系统的磁盘使用情况
df
# Filesystem           1K-blocks      Used Available Use% Mounted on
# /dev/xvda1            20641404   1778544  17814336  10% /
# tmpfs                   250336         0    250336   0% /dev/shm

# 显示所有分区
df -a
# Filesystem           1K-blocks      Used Available Use% Mounted on
# /dev/xvda1            20641404   1778544  17814336  10% /
# proc                         0         0         0   -  /proc
# sysfs                        0         0         0   -  /sys
# devpts                       0         0         0   -  /dev/pts
# tmpfs                   250336         0    250336   0% /dev/shm
# xenfs                        0         0         0   -  /proc/xen
# none                         0         0         0   -  /proc/sys/fs/binfmt_misc

# 以更已读的方式显示，采用1024转换
df -h
# Filesystem            Size  Used Avail Use% Mounted on
# /dev/xvda1             20G  1.7G   17G  10% /
# tmpfs                 245M     0  245M   0% /dev/shm

# 以更已读的方式显示，采用1000转换
df -H
# Filesystem             Size   Used  Avail Use% Mounted on
# /dev/xvda1              22G   1.9G    19G  10% /
# tmpfs                  257M      0   257M   0% /dev/shm

# 列出文件系统的类型
df -T
# Filesystem    Type   1K-blocks      Used Available Use% Mounted on
# /dev/xvda1    ext4    20641404   1778544  17814336  10% /
# tmpfs        tmpfs      250336         0    250336   0% /dev/shm

# 显示指定文件系统类型的磁盘
df -t ext4
# Filesystem           1K-blocks      Used Available Use% Mounted on
# /dev/xvda1            20641404   1778544  17814336  10% /

# 以KB为单位显示
df -k
# Filesystem           1K-blocks      Used Available Use% Mounted on
# /dev/xvda1            20641404   1778544  17814336  10% /
# tmpfs                   250336         0    250336   0% /dev/shm