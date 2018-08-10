# ********************< mv >********************
# 重命名或移动文件

# 重命名文件
mv oldname newname

# 重命名目录
# 目标目录不存在
mv data dataTmp
# 目标目录存在(移动文件)
mv data/* dataTmp

# 移动文件
# 文件 -> 上级目录
mv data.tmp /home/txazo
# 文件 -> 文件
mv data.tmp /home/txazo/data.tmp

# 目录 -> 上级目录
mv data /home/txazo
# 目录 -> 目录(/home/txazo/data不存在)
mv data/* /home/txazo/data

# 重写文件前给出提示
mv -i file1 file2

# 重写文件前无提示
mv -f file1 file2