# ********************< sql导出 >********************
# sql导出的数据默认以制表符为分隔符
select MemberId, CreateTime from UserInfo;

# ********************< join处理 >********************
# join处理前后保持制表符为分隔符
tabChar=`echo -e "\t"`
join -t "$tabChar" file1 file2

# ********************< awk处理 >********************
# awk处理前后保持制表符为分隔符
echo -e "1\ta\n2\tb\n3\tc" | awk 'BEGIN {FS="\t"} {
  printf("%s\t%s\n", $1, $2)
}'

echo -e "1\ta\n2\tb\n3\tc" | awk 'BEGIN {FS="\t"; OFS="\t"} {
  print $1, $2
}'