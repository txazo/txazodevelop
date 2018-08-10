# ********************< uniq >********************
# 去重(连续重复的行)
# uniq前一般先sort

cat data.tmp
# 1
# 1
# 2
# 2
# 2
# 3

# 去除重复行
uniq data.tmp
# 1
# 2
# 3

# 统计重复行出现的次数
uniq -c data.tmp
#      2 1
#      3 2
#      1 3

# 只显示重复行
uniq -d data.tmp
# 1
# 2

# 只显示不重复行
uniq -u data.tmp
# 3

# 显示所有重复行
uniq -D data.tmp
# 1
# 1
# 2
# 2
# 2

cat data.tmp
# 1 dog
# 2 dog
# 3 fog
# 4 dog
# 5 cat
# 6 cat

# 忽略前n个字段(和字段前的空白)，忽略前m个字符(包括字段前的空白)
uniq -f 1 -s 2 -c data.tmp
#      4 1 dog
#      2 5 cat