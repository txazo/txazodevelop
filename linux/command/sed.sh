# ********************< sed >********************
# 文本转换
# 以行为单位进行处理
# sed替换行中字符串有两种写法

# 匹配的行才会被输出到标准输出
sed -n '1p' data.tmp

# 直接修改源文件内容
sed -i '1d' data.tmp

# 多点编辑
sed -e '1d' -e '2d' data.tmp

# 删除行(d)
sed '1d' data.tmp

# 显示行(p)
sed -n '1p' data.tmp

# 插入行(前面)(i)
sed '1i New Line' data.tmp

# 新增行(后面)(a)
sed '1a New Line' data.tmp

# 替换行(c)
sed '1c Replace Line' data.tmp

# 替换行中第一个匹配字符串(s)
sed 's/Txazo/txazo/' data.tmp
sed '/Txazo/s//txazo/' data.tmp

# 替换行中所有匹配字符串(s)
sed 's/Txazo/txazo/g' data.tmp
sed '/Txazo/s//txazo/g' data.tmp

# NUMBER           行号
# FIRST~STEP       从FIRST开始，每隔STEP行
# $                最后一行
# /REGEXP/         正则表达式
# ADDR             NUMBER和/REGEXP/
# ADDR1,ADDR2      ADDR1匹配的行到ADDR2匹配的行
# 0,/REGEXP/       第一行到REGEXP匹配的行
# ADDR,+N          从ADDR匹配的行开始的N行
# ADDR,~N          从ADDR匹配的行开始到N的倍数的行
# /REGEXP/!        不匹配正则表达式的行

# \(\)             保存匹配的字符(\n调用)
# &                被替换的字符串

# 去掉所有空格
sed 's/\s\+//g' data.tmp
sed '/\s\+/s///g' data.tmp

# 删除空行
sed '/^\s*$/d' data.tmp

# 插入多行
sed '1i New Line\nNew Line\nNew Line' data.tmp
sed '1i New Line\
New Line\
New Line' data.tmp

# 保存匹配字符串和被替换的字符串
echo "access" | sed 's/\(acce\)ss/& \1pt/g'
# access accept