# ********************< 百分表 >********************
date

date +'%Y-%m-%d %H:%M:%S'
# 2013-08-15 18:02:57

%Y yyyy # 年
%y yy # 年
%m mm # 月
%d dd # 日

%H HH # 时
%M MM # 分
%S SS # 秒

%s  # 当前时间毫秒数

# 当天
date
date -d now
date -d today

# 一天后
date -d 'now 1 day'
date -d 'now 2 day'

# 一天前
date -d 'now 1 day ago'
date -d 'now 2 day ago'

theDay=`date +"%Y-%m-%d" -d "1 days ago"`
echo ${theDay}