# ********************< tail >********************
# 读取文件或输入流的尾部

# 读取后10行
tail 1.tmp
cat 1.tmp | tail

# 读取多个文件的尾部
tail 1.tmp 2.tmp

# 读取后20行
tail -20 1.tmp
tail -n 20 1.tmp

# 读取去除前20行之外的行
tail -n +20 1.tmp

# 读取文件新增的内容
tail -f 1.tmp

# 10秒更新一次
tail -f -s 10 1.tmp