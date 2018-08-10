# ********************< grep >********************
# 文本搜索
# 格式：grep [OPTION]... PATTERN [FILE]...
# 多文件搜索时行首会显示文件名，单文件搜索行首不显示文件名

# 正则表达式

^          # 匹配行首
$          # 匹配行尾
\<         # 匹配单词首
\>         # 匹配单词尾

.          # 匹配单个字符
\          # 转义符

\?          # 匹配0或1次
*          # 匹配0或多次
\+          # 匹配一次以上

\{N\}      # 匹配N次
\{N,\}     # 至少匹配N次
\{,M\}     # 最多匹配M次
\{N,M\}    # 匹配N到M次

\|         # 匹配两者之一
\()\       # 存储匹配到的字符
\(\|)\     # 匹配两者之一并存储

[]         # 字符集
[-]        # 字符范围
[^]        # 不在指定字符范围的字符

\t         # 制表符
\n         # 换行符
\s         # 空格(任意)

# 匹配正则表达式
grep -e

# 匹配扩展正则表达式
grep -E

# 递归搜索子目录
grep -r 'localhost' /usr/local/nginx

# 只显示匹配的文件
grep -l -r 'localhost' /usr/local/nginx

# 正则表达式中包含-
grep --help | grep -e '--version'
grep --help | grep '\-\-version'

# 查找单词(单词前后非字母和数字)
grep -w 'localhost' /usr/local/nginx/conf/nginx.conf
grep -e '\<localhost\>' /usr/local/nginx/conf/nginx.conf

# 同时搜索输入流和文件
cat /etc/passwd | grep 'txazo' - /etc/group
# (standard input):txazo:x:1000:1000:txazo:/home/txazo:/bin/bash
# /etc/group:txazo:x:1000:

# 回文搜索(最多9个字符的回文搜索)
grep -e '\(.\?\)\(.\?\)\(.\?\)\(.\?\).\?\4\3\2\1' /etc/passwd

# 只输出匹配的行数
grep -c '/bin/bash' /etc/passwd

# 显示匹配pattern1或pattern2的行
grep -e 'txazo\|hello' data.tmp

# 显示匹配pattern1和pattern2的行
grep 'txazo' data.tmp | grep 'hello'

# 不区分大小写
grep -i 'root' /etc/passwd

# 查找不匹配的行
grep -v 'root' /etc/passwd

# 输出行号
grep -n 'root' /etc/passwd

# 匹配关键词加上颜色
grep --color=auto 'root' /etc/passwd