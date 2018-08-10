# ********************< du >********************
# 文件和目录的磁盘使用情况

# 当前目录及其子目录的磁盘使用情况
du
# 4       ./www/data/log
# 8       ./www/data
# 12      ./www
# 44      .

# 目录及其子目录的磁盘使用情况
du /home/txazo
# 4       /home/txazo/www/data/log
# 8       /home/txazo/www/data
# 12      /home/txazo/www
# 44      /home/txazo

# 文件的磁盘使用情况
du data.tmp
# 4       data.tmp

# 多个文件的磁盘使用情况
du data.tmp other.tmp
# 4       data.tmp
# 4       other.tmp

# 多个文件的磁盘使用情况及其总和
du -c data.tmp other.tmp
# 4       data.tmp
# 4       other.tmp
# 8       total

# 多个目录的磁盘使用情况及其总和
du -cs www www/data
# 12      www
# 8       www/data
# 20      total

# 当前目录的磁盘使用情况总和
du -s
# 52      .

# 目录的磁盘使用情况总和
du -s /home/txazo
# 52      /home/txazo

# 以更易读的方式显示磁盘使用情况
du -h /home/txazo
# 4.0K    /home/txazo/www/data/log
# 8.0K    /home/txazo/www/data
# 12K     /home/txazo/www
# 52K     /home/txazo

# 目录下所有子目录和文件的磁盘使用情况
du -a www
# 0       www/data/log/1
# 0       www/data/log/3
# 0       www/data/log/2
# 4       www/data/log
# 8       www/data
# 12      www

# 深入到第几层子目录
du --max-depth=1 www
# 8       www/data
# 12      www

# 排除指定文件或目录及其大小
du --exclude="log" www
# 4       www/data
# 8       www