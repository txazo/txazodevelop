# 流
|
# 输出到文件
> 
# 追加到文件
>>
# 追加前注意删除文件
rm -f sql.tmp
echo "select MemberId from MemberBaseInfo_00 where Valid = 1;" >> sql.tmp
#  执行命令并将输出结果赋值给变量
count=`cat member.tmp | wc -l | awk '{print $1}'`