# ********************< shell执行 >********************
/home/txazo/shell/run.sh
./run.sh
bash run.sh
sh run.sh
source run.sh

# ********************< ``和$() >********************
# ``和$()相同
echo `ll | wc -l`

# ********************< basename和dirname >********************
basename $0    # 当前脚本的文件名
dirname $0     # 当前脚本的目录名

# ********************< 1.sh >********************
#!/bin/sh

read -p "Please input your name: " name
echo "Your name is $name."
exit 0

# ********************< test >********************
test expression

test expression && echo "true" || echo "false"
test -d htdocs && rm -rf htdocs || mkdir htdocs

test expression; echo $?  # 0：true，1：false
test '1' = '1'; if [ $? -eq 0 ]; then echo "1 = 1"; fi

# 测试文件类型
test -e  # 文件存在，为true
test -f  # 存在且为文件，为true
test -d  # 存在且为目录，为true
test -b  # 存在且为块文件，为true
test -c  # 存在且为字符文件，为true

# 测试文件权限
test -r  # 有读权限，为true
test -w  # 有写权限，为true
test -x  # 有可执行权限，为true

# 文件比较
test file1 -nt file2  # file1是否比file2新
test file1 -ot file2  # file1是否比file2旧

# 字符串比较
test -z str  # 空串，为true
test -n str  # 非空串，为true
test str1 = str2  # 两串相等，为true
test str1 != str2  # 两串不相等，为true

# 整数比较
test int1 -gt int2  # int1 > int2，为true
test int1 -ge int2  # int1 >= int2，为true
test int1 -lt int2  # int1 < int2，为true
test int1 -le int2  # int1 <= int2，为true
test int1 -eq int2  # int1 == int2，为true
test int1 -ne int2  # int1 != int2，为true

# 逻辑判断
test exp1 -a exp2  # 并
test exp1 -o exp2  # 或
test ! exp  # 非

# ********************< [ expression ] >********************
# 类似test expression，[ expression ]多使用在if then fi中
[ expression ]
[ expression ] && echo "true" || echo "false"
[ -d htdocs ] && rm -rf htdocs || mkdir htdocs

[ expression ]; echo $?  # 0：true，1：false
[ '1' == '1' ]; if [ $? -eq 0 ]; then echo "1 = 1"; fi
# [ expression ]字符串相等判断最好使用两个等号
name=txazo
[ "$name" = "txazo" ]; if [ $? -eq 0 ]; then echo "true"; fi
# [ expression ]中字符串变量最好使用引号括起来

# ********************< 2.sh >********************
#!/bin/sh

echo "Script: $0"
echo "Args Length: $#"
k=1
for i in $@
do
  echo "Args[$k]: $i"
  $((k += 1))
done

# ********************< if then >********************
if []; then echo "1"; fi
if []; then
  echo "1"
fi
if []
then
  echo "1"
fi

# 多个条件
if [ -z "" ] && [ -n "1" ]; then echo "true"; fi
if [ -z "" ] || [ -n "" ]; then echo "true"; fi
if ! [ -z "1" ]; then echo "true"; fi

# 多重判断
if []; then
  program
elif []; then
  program
else
  program
fi

# ********************< case esac >********************
case $i in
  v1)
    program
    ;;
  v2)
    program
    ;;
  *)
    program
    ;;
esac

# ********************< function >********************
function hello() {
  program
}
hello

# ********************< while do done >********************
while [ condition ]
do
  program
done

# ********************< until do done >********************
until [ condition ]
do
  program
done

# ********************< for do done >********************
for i in ${array[*]}
do
  program
done

for ((; ; ))
do
  program
done