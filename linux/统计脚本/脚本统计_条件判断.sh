# 条件判断

# 字符串判断
[  str1 = str2 ]   # 两串相等为真
[  str1 != str2 ]  # 两串不相等为真
[  -n str ]        # 非空串为真
[  -z str ]        # 空串为真

# 数字判断
[ int1 -eq int2 ]  # 两数相等为真
[ int1 -ne int2 ]  # 两数不等为真
[ int1 -gt int2 ]  # int1 > int2为真
[ int1 -ge int2 ]  # int1 >= int2为真
[ int1 -lt int2 ]  # int1 < int2为真
[ int1 -le int2 ]  # int1 <= int2为真

# 文件判断
[ -r file ]  # 文件可读为真
[ -w file ]  # 文件可写为真
[ -x file ]  # 文件可执行为真
[ -f file ]  # 文件为普通文件为真
[ -d file ]  # 文件为目录为真
[ -c file ]  # 文件为字符文件为真
[ -b file ]  # 文件为块文件为真
[ -s file ]  # 文件大小非0为真
[ -t file ]  #

# 逻辑判断
[ expr1 -a expr2 ]  # 与
[ expr1 -o expr2 ]  # 或
[ ! expr ]          # 非

[ expr1 ] && [ expr2 ]  # 与
[ expr1 ] || [ expr2 ]  # 或
! [ expr ]              # 非