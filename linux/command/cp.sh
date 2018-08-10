# ********************< cp >********************
# 复制文件或目录

# 复制文件(文件 -> 文件)
# 文件 -> 上级目录
cp data.tmp /home/txazo
# 文件 -> 文件
cp data.tmp /home/txazo/data.tmp

# 复制目录
# 目录 -> 上级目录
cp -r data /home/txazo
cp -R data /home/txazo
# 目录下文件 -> 目录
cp -r data/* /home/txazo/data
cp -R data/* /home/txazo/data

# 复制时提示确认
cp -i data.tmp /home/txazo

# 复制时删除已经存在的目标而不提示
cp -f data.tmp /home/txazo