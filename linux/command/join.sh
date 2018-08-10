# ********************< join >********************
# 合并两个文件
# join之前要先sort
# join前后最好指定输入输出分隔符
# join默认以空格(一个或多个)为输入分隔符，一个空格为输出分隔符

cat file1
# 1
# 2

cat file2
# 2
# 3

join file1 file2
# 2

# 加上file1中没有相同栏位的行
join -a1 file1 file2
# 1
# 2

# 加上file1和file2中没有相同栏位的行
join -a1 -a2 file1 file2
# 1
# 2
# 3

# 显示file1中没有相同栏位的行
join -v1 file1 file2
# 1

# 比较栏位时，忽略大小写
join -i file1 file2
# 2

# 指定连接的栏位
join -1 1 -2 1 file1 file2
# 2

# 定义输出格式
join -o 1.1 2.1 file1 file2
# 2 2

# 输入输出分隔符
tabChar=`echo -e "\t"`
join -t "$tabChar" -o 1.1 2.1 file1 file2
# 2    2

# 从流中读取
cat file1 | join - file2