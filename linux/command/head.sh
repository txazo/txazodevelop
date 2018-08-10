# ********************< head >********************
# 读取文件或输入流的头部

# 读取前10行
head 1.tmp
cat 1.tmp | head

# 读取多个文件的头部
head 1.tmp 2.tmp

# 读取前20行
head -20 1.tmp
head -n 20 1.tmp

# 读取去除后20行之外的行
head -n -20 1.tmp