# ********************< sort >********************
# 文本排序
# sort默认以字符从前向后升序排序
# sort默认以空格(一个或多个)为输入分隔符，无输出分隔符，每行原样输出

cat data.tmp
# 12
# 2
# 12

sort data.tmp
# 12
# 12
# 2

# 降序排序
sort -r data.tmp
# 2
# 12
# 12

# 除去重复行
sort -u data.tmp
# 12
# 2

# 排序结果输出到源文件
sort data.tmp -o data.tmp

# 按照数值排序
sort -n data.tmp
# 2
# 12
# 12

# 设定分隔符和指定排序列
cat data.tmp
# 1:2
# 2:3
# 3:1

sort -t ":" -k2nr data.tmp
# 2:3
# 1:2
# 3:1

# 指定多个排序列
cat data.tmp
# n1 20 300
# n2 40 100
# n3 30 400
# n4 40 200

sort -k2nr -k3nr data.tmp
# n4 40 200
# n2 40 100
# n3 30 400
# n1 20 300