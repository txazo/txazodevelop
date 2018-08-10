# ********************< 字符串 >********************

# 字符串长度
$(awk 'BEGIN {print length("'$str'")}')
$(expr length $str)
$(echo "$str" | wc -c)  # 结果多1

# 判断字符串为空
[ "$str" = "" ]
[ -z "$str" ]

# 字符串截取
str="^ababa$"
echo ${str#*b}   # aba$
echo ${str##*b}  # a$
echo ${str%b*}   # ^aba
echo ${str%%b*}  # ^a

# %  右向左移除 非贪婪匹配
# %% 右向左移除 贪婪匹配
# #  左向右移除 非贪婪匹配
# ## 左向右移除 贪婪匹配

URL="www.txazo.com"
echo ${URL%.*}   # www.txazo
echo ${URL%%.*}  # www
echo ${URL#*.}   # txazo.com
echo ${URL##*.}  # com