# ********************< wc >********************
# 统计文件的行数单词数字符数和字节数

cat data.tmp
# Hello World
# txazo
# Welcome to 北京

# 行数 单词数 字节数 文件名
wc data.tmp
# 3  6 36 data.tmp

# 统计行数
wc -l data.tmp
# 3 data.tmp

# 统计单词数
wc -w data.tmp
# 6 data.tmp

# 统计字符数
wc -m data.tmp
# 32 data.tmp

# 统计字节数
wc -c data.tmp
# 36 data.tmp

# 统计最长行的长度
wc -L data.tmp
# 15 data.tmp

cat other.tmp
# This is others

# 多个文件统计
wc data.tmp other.tmp
# 3  6 36 data.tmp
# 1  3 15 other.tmp
# 4  9 51 total