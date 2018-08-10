# ********************< awk命令格式 >********************
awk [options] 'BEGIN {} pattern {} END {}' [var=value ...] file(s)
awk [options] -f scriptFile [var=value ...] file(s)

# awk字符串用双引号括起来

# ********************< awk字符串和数字 >********************
# 字符串连接，通过""连接
echo | awk '{a = "Hello"; b = "World"; c = a""b; print c}'
# HelloWorld

# 字符串转数字，+号实现，自动强制将字符串转为数字，非数字变为0，发现第一个非数字字符，后面自动忽略
echo | awk '{a = "100"; b = "12World34"; c = a + b; print c}'
# 112

# ********************< awk模式匹配 >********************
echo "txazo" | awk '$1 ~ /xa/'                  # 匹配
echo "txazo" | awk '$1 !~ /xsa/'                # 不匹配
echo "txazo" | awk '$1 ~ /^tx/ && $1 ~ /zo$/'   # 匹配并运算
echo "txazo" | awk '$1 ~ /^tx/ || $1 ~ /^zo/'   # 匹配或运算

# ********************< awk脚本文件 >********************
# common.awk
BEGIN {
  print "BEGIN awk"
}
$1 ~ /^[0-9]+/ {
  print $0
}
END {
  print "END awk"
}

# 调用awk脚本文件
echo -e "txazo\n12345" | awk -f common.awk

# a1.awk
BEGIN {
  FS="\t"
  print "BEGIN a1"
  a="var1"
} {
  print $1
  print b, " in ACTION1"
} END {
  print "END a1"
  print b, " in END1"
}

# a2.awk
BEGIN {
  FS="-"
  print "BEGIN a2"
  b="var2"
  print a, " in BEGIN2"
} {
  print $1
  print a, " in ACTION2"
} END {
  print "END a2"
  print a, " in END2"
}

# 调用多个awk脚本文件，每个awk脚本文件可以有单独的BEGIN和END
# 本质：将BEGIN和END合并，awk操作依次执行(多个awk操作)
echo -e "12-ab\n34-cd" | awk -f a1.awk -f a2.awk
# BEGIN a1
# BEGIN a2
# var1  in BEGIN2
# 12
# var2  in ACTION1
# 12
# var1  in ACTION2
# 34
# var2  in ACTION1
# 34
# var1  in ACTION2
# END a1
# var2  in END1
# END a2
# var1  in END2

# ********************< awk操作 >********************
# awk模式和操作可以多次出现，一个模式和一个操作一一对应，共用一个BEGIN和END，每行处理依次调用awk操作
echo -e "abcd\t1234" | awk 'BEGIN {FS="\t"} $1 ~ /bc/ {print $1} $2 ~ /23/ {print $2} END {print "Awk End"}'
# abcd
# 1234
# Awk End

# ********************< awk域分隔符 >********************
# awk默认以空格(一个或多个空格)为输入域分隔符，一个空格为输出域分隔符
echo -e "ab  cd\tef" | awk '{print $1, $2, $3}'
# ab cd ef
echo -e "ab  cd\tef" | awk '{print $1, $2}'
# ab cd

# 指定输入域分隔符
echo -e "ab cd\tef" | awk -F "\t" '{print $1}'
echo -e "ab cd\tef" | awk --field-separator="\t" '{print $1}'
echo -e "ab cd\tef" | awk 'BEGIN {FS="\t"} {print $1}'
# ab cd

# 指定输出域分隔符，把域分隔符放到方括号中
echo -e "ab cd\tef" | awk 'BEGIN {FS="\t"; OFS="\t"} {print $1, $2}'
# ab cd   ef

# 同时指定多个域分隔符
echo -e "1-2\n3\t4" | awk 'BEGIN {FS="[-\t]"} {print $1, $2}'

# ********************< awk >********************
echo -e "txazo\n12345" | awk 'BEGIN {print "BEGIN awk"} $1 ~ /^[0-9]+/ {print $0} END {print "END awk"}'

# ********************< awk传递参数 >********************
# awk外部变量可以直接在awk内部使用，使用单引号括起来
count=10
echo | awk 'BEGIN {print '${count}'} {print '${count}'} END {print '${count}'}'
# 10
# 10
# 10

# awk外部变量通过参数传递到内部
# 方式一，BEGIN块获取不到参数，Action块和END块可以获取到参数值
var1="var1"; var2="var2"
echo | awk 'BEGIN {print var1, var2} {print var1, var2} END {print var1, var2}' var1="$var1" var2="$var2"
#
# var1 var2
# var1 var2

# 方式一，BEGIN块，Action块和END块都可以获取到参数值
var1="var1"; var2="var2"
echo | awk -v var1="$var1" -v var2="$var2" 'BEGIN {print var1, var2} {print var1, var2} END {print var1, var2}'
# var1 var2
# var1 var2
# var1 var2

# ********************< awk模式匹配 >********************
# /.../
echo "txazo" | awk '/xa/'
# < > <= >= == !=
echo "12" | awk '$1 > 0'
echo "12" | awk '$1 == "12"'
# ~ !~
echo "txazo" | awk '$1 ~ /xa/'
# && || !
echo "1 -1" | awk '$1 > 0 || $2 > 0'
echo "12" | awk '! ($1 < 0) && $1 > 0'
# /.../, /.../ 输出两匹配行之间的行，以此类推
echo -e "<div id=\"div1\">\n\
</div>\n\
blank\n\
<div id=\"div2\">\n\
</div>" | awk '/<div/, /<\/div>/'
# <div id="div1">
# </div>
# <div id="div2">
# </div>

# ********************< awk运算 >********************
# + - * / % ^ **(加 减 乘 除 余 幂 幂)
echo | awk '{print 5 ^ 2}'
# 25

# ++ --(一元加减)
echo | awk '{count = 5; print ++count}'
# 6

# = += -= *= /= %= ^= **=，赋值
echo | awk '{count = 5; count **= 4; print count}'
# 625

# ? :
echo "12 23" | awk '{max = ($1 > $2) ? $1 : $2; print max}'

# ********************< awk变量 >********************
# awk内置变量名和全局变量名不能相同
echo -e "1\n2" | awk '{count += 1; print count} END {print "Total: "count}'
# 1
# 2
# Total: 2

# ********************< awk系统变量 >********************
$n            # 当前记录的第n个域
ARGC          # 命令行参数的数量
ARGV          # 命令行参数的数组

FS            # 输入字段分隔符，默认为空格(一个或多个空格)
RS            # 输入记录分隔符，默认为换行符\n
OFS           # 输出字段分隔符，默认为空格(一个空格)
ORS           # 输出记录分隔符，默认为换行符\n

FILENAME      # 当前处理文件名
NF            # 当前记录的字段数量
NR            # 当前记录数(总的记录数)
FNR           # 文件的记录数

FIELDWIDTHS   # 宽度分隔符，此时会忽略FS分隔符

OFMT          # 数字的输出格式，默认为%.6g

SUBSEP        # 数组下标分隔符，默认值是\034

var="var"
awk -F "\t" 'BEGIN {for (i in ARGV) {print "ARGV["i"]: "ARGV[i]}}' var="$var" 1.tmp 2.tmp
awk -F "\t" 'BEGIN {for (i = 0; i < ARGC; i++) {print "ARGV["i"]: "ARGV[i]}}' var="$var" 1.tmp 2.tmp
# ARGV[0]: awk
# ARGV[1]: var=var
# ARGV[2]: 1.tmp
# ARGV[3]: 2.tmp

echo | awk 'BEGIN {OFMT = "%.2f"} {print 1 / 3}'
# 0.33

echo "20130923123000" | awk 'BEGIN {FIELDWIDTHS = "4 2 2 2 2 2"} {print $1"-"$2"-"$3, $4":"$5":"$6}'
# 2013-09-23 12:30:00

# ********************< awk数组 >********************
# awk数组下标可以是数字和字符串

# 数组赋值
array[index] = value;

# 数组长度
length(array)

# 读取数组值
# 方式一，适用于数字下标数组和字符串下标数组，输出无序
for (i in array) {
  print i, array[i]
}

# 方式二，只适用于数字下标数组，输出有序
for (i = 1; i <= length(array); i++) {
  print i, array[i]
}

# 多维数组，array[index, index2, ...]，下标之间使用SUBSEP为分隔符
echo -e "0 0 12\n0 1 23\n1 0 34\n1 1 45" | awk '{
  count[$1, $2] += $3;
} END {
  for (i in count) {
    split(i, p, SUBSEP);
    printf "%s %s %s\n", p[1], p[2], count[i]
  }
}'

# 删除数组
delete array
delete array[i]

# 变量作为数组下标
echo -e "1\n2\n3" | awk '{
  count[k++] = $1;
} END {
  for (i = 0; i < length(count); i++) {
    print i, count[i]
  }
}'

# ********************< awk处理多个文件 >********************
# 通过FILENAME区别文件
awk '{
  if (FILENAME == "map.tmp") {
    map[$1] = $2
  } else {
    printf("%s %s\n", $1, map[$2])
  }
}' map.tmp info.tmp > newInfo.tmp

# ********************< awk内置函数 >********************
# length
echo "txazo" | awk '{print length($1)}'
# 5

# rand
echo | awk '{print rand()}'
# 0.237788

# srand，获得随机数
echo -e "1\n2\n3" | awk 'BEGIN {srand()} {print int(100 * rand())}'
# 27
# 33
# 62

# int
echo "123.23" | awk '{print int($1)}'
# 123

# substr(str, start, length)
# 从位置start开始，截取长度为length的子串
echo "txazo" | awk '{print substr($1, 0, 2)}'
# tx

# index(str1, str2)，返回str2在str1中第一次出现的位置，从1开始编号
echo "txazo" | awk '{print index($1, "a")}'
# 3

# match，正则表达式在字符串中出现的位置，无匹配返回0
# 内置变量RSTART和RLENGTH为子串的起始位置和字符长度
echo "txazo" | awk '{if (match($1, /xa/) > 0) {print substr($1, RSTART, RLENGTH)}}'

# split
echo `date +%Y-%m-%d` | awk '{split($1, d , "-"); print d[1], d[2], d[3]}'
# 2013 08 28

# toupper
echo "txazo" | awk '{print toupper($1)}'
# TXAZO

# tolower
echo "TXAZO" | awk '{print tolower($1)}'
# txazo

# sub，对第一个匹配进行替换
echo -e "abcd\nbcde\ncdef" | awk '{sub(/cd/, "oo", $1); print $1}'
# aboo
# booe
# ooef

# gsub，对所有匹配进行替换
echo -e "abcd\ncdcd\ncdef" | awk '{gsub(/cd/, "oo", $1); print $1}'
# aboo
# oooo
# ooef

# and, 与运算
# or, 或运算
# xor, 异或运算

# ********************< awk函数 >********************
# system(command)，执行command命令
echo | awk '{print system("ls -al | wc -l")}'
# 15

# getline
echo | awk 'BEGIN {"date" | getline date; print date}'
echo | awk 'BEGIN {while ("ls -al" | getline) {print $0}}'
echo | awk 'BEGIN {while ("ls -al" | getline line) {print line}}'
echo | awk 'BEGIN {while (getline < "/etc/passwd" > 0) {lc++}} END {print lc}'

# ********************< awk输出print >********************
# print调用格式
print str1, str2, ...

# ORS和OFS对print有效，默认以空格为域分隔符，换行符为记录分隔符

# print一行分多行
echo | awk '{print "Hello\
 World.\
 Welcome to Beijing!"}'
# Hello World. Welcome to Beijing! 

# print多行输出
echo | awk '{print "Hello\n\
World\n\
Welcome to Beijing"}'
# Hello
# World
# Welcome to Beijing

echo -e "12 23\n23 34" | awk 'BEGIN {OFS="\t"; ORS="\n"} {print $1, $2}'
# 12      23
# 23      34

# ********************< awk格式化输出printf >********************
# printf函数调用格式
printf("", var1, var2, ...)

# printf输出不会自动换行，得手动添加换行符\n，ORS和OFS对printf无效

%d    # 整数
%f    # 浮点数，默认6位小数位
%s    # 字符串
%g    # 自动选择合适的表示法
\t    # 制表符
\n    # 换行

# %d %s，设置输出最大宽度，超过最大宽度，按实际长度输出，否则右对齐
echo -e "123\n12345" | awk '{printf("%4d\n", $1)}'
echo -e "123\n12345" | awk '{printf("%4s\n", $1)}'
#  123
# 12345

# %f，设置浮点数输出最大宽度，整数3位(不够右对齐，否则原样输出)，小数2位(不够补0，否则四舍五入)，小数点1位
echo | awk '{printf("%6.2f\n", 100 / 3)}'
#  33.33
echo | awk '{printf("%6.2f\n", 10000 / 3)}'
# 3333.33
echo | awk '{printf("%6.2f\n", 10)}'
#  10.00
echo | awk '{printf("%6.2f\n", 4556 / 1000)}'
#   4.56

# 左对齐，适用于%d %f %s
echo -e "123\n123456" | awk '{printf("%-6s\n", $1)}'
# 123
# 123456

# %d，整数位数不足最大宽度，前面补0，否则原样输出，右对齐有效
echo -e "1\n12\n123\n1234" | awk '{printf("%03d\n", $1)}'
# 001
# 012
# 123
# 1234

# %s，字符串长度小于2，右对齐，大于3，截掉之后的字符串
echo -e "1\n12\n123\n1234" | awk '{printf("%2.3s\n"), $1}'
#  1
# 12
# 123
# 123

# awk printf中使用变量
width=9
count=1234
echo | awk '{printf("%0'"$width"'d\n", '$count')}'
echo | awk '{printf("%0"'$width'"d\n", '$count')}'
# 000001234

# ********************< 流程控制 >********************
if () {
} else if {
}else{
}

for (; ;){
}

while () {
}

do {
} while ();

# ********************< awk去重 >********************
echo -e "12\n12\n23\n34" | awk '{
  if (!rec[$1]) {
    rec[$1]=1;
    print $1;
  }
}'

echo -e "12\n12\n23\n34" | awk '{
  if (!($1 in rec)) {
    rec[$1]=1;
    print $1;
  }
}'

echo -e "12\n12\n23\n34" | awk '{
  rec[$1]++
}
END {
  for (i in rec) {
    print i
  }
}'

# ********************< awk next >********************
# awk中遇到next，会跳过当前行，进行下行匹配
echo -e "a\nb\nc\nd" | awk 'NR % 2 == 1 {next} {print NR, $1}'
# 2 b
# 4 d

# ********************< awk多文件操作 >********************
cat a.txt
# 100 mimi
# 200 lili
# 300 xixi

cat b.txt
# 100 90 80
# 200 60 40
# 300 80 30

# 合并为
# mimi 100 90 80
# lili 200 60 40
# xixi 300 80 30

# 方式一
cat a.txt b.txt | sort -k1n -k2r | awk 'NR % 2 == 1 {
  name = $2
  next
} {
  print name, $0
}'

# 方式二
awk '{
  if (FILENAME == "a.txt") {
    name[$1] = $2;
  } else {
    print name[$1], $0
  }
}' a.txt b.txt

# ********************< awk数组有序输出 >********************
echo -e "1 12\n2 23\n3 34" | awk '{
  array[$1] = $2;
  id[++j] = $1;
} END {
  for (i = 1; i <= j; i++) {
    print id[i], array[id[i]]
  }
}'

# ********************< awk数组排序 >********************
# 方式一，asort对值进行排序，但会丢失键值
echo -e "12\n45\n34\n56" | awk '{
  array[++a] = $1
} END {
  slen = asort(array, new)
  for(i = 1; i <= slen; i++) {
    print i, array[i], new[i]
  }
}'

# 方式二，asorti对键值进行排序
echo | awk '{
  array["c"] = 100
  array["a"] = 50
  array["b"] = 25
  slen = asorti(array, new)
  for(i = 1; i <= slen; i++) {
    print i, new[i], array[new[i]]
  }
}'

# 方式三，通过管道发送到sort排序
echo -e "1 100\n2 50\n3 80" | awk '{
  print $1, $2 | "sort -k2n"
}'

# ********************< awk累加 >********************
echo -e "1 12\n2 23\n3 34" | awk 'BEGIN {
  a=0; b=0
}
{
  a += $1; b += $2
}
END {
  print a, b
}'

# ********************< awk sql拼接 >********************
echo | awk '{
for (i=0; i < 100; i++) {
  printf "select MemberId from MemberBaseInfo_%02d;\n", i > "sql.tmp"
}
}'

# ********************< awk分组 >********************
# 第一列为分组列，第二列为聚合列
echo -e "1 12\n1 21\n3 34\n3 43" | awk '{
  tot[$1] += $2
}
END {
  for (i in tot) {
    printf "%s %d\n", i, tot[i]
  }
}'

# 第一列出现的次数，按照次数降序排列
echo -e "1\n1\n2\n2\n2\n3" | awk '{
  tot[$1] += 1
}
END {
  for (i in tot) {
    printf "%s %d\n", i, tot[i] | "sort -k 2nr"
  }
}'

# 第一列为分组列，第二列为聚合列，按照第二列降序排列，并输出到文件result.tmp
echo -e "1 12\n1 21\n3 34\n3 43\n2 10\n2 11" | awk '{
  tot[$1] += $2
}
END {
  for (i in tot) {
    printf "%s %d\n", i, tot[i] | "sort -k 2nr > result.tmp"
  }
}'

# 第一列为分组列，第二列和第三列为聚合列
echo -e "1 12 123\n1 21 123\n3 34 234\n3 43 234" | awk '{
  tot1[$1] += $2;
  tot2[$1] += $3
}
END {
  for (i in tot1) {
    printf "%s %d %d\n", i, tot1[i], tot2[i]
  }
}'

# 多个字段作为分组列
echo -e "1 0 10\n1 0 30\n1 1 40\n1 1 50" | awk '{
  tot[$1, $2] += $3
}
END {
  for (i in tot) {
    split(i, p, "\034");
    printf "%s %s %d\n", p[1], p[2], tot[i]
  }
}'

# ********************< awk自定义函数 >********************
# common.awk
function plus(a, b) {
  return a + b;
}
{
  print plus($1, $2);
}

echo -e "1 2\n2 3\n3 4" | awk -f common.awk

# ********************< awk输出重定向 >********************
echo -e "1\n2\n3" | awk '{print $0 > "id.tmp"}'
echo -e "1\n2\n3" | awk '{print $0 >> "id.tmp"}'

# ********************< awk getline >********************
echo | awk 'BEGIN {"date +%Y" | getline d; print d}'
echo | awk 'BEGIN {while ("ls" | getline l) {print l}}'