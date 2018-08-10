# ********************< crontab >********************
# 周期性任务调度
# crontab的最小调度周期为1分钟

# 查看crontab的任务
crontab -l

# 编辑crontab的任务
crontab -e

# 移除所有crontab的任务
crontab -r

# 指定用户(只有root才有这个权限)
crontab -l -u txazo
crontab -e -u txazo
crontab -r -u txazo

# crontab服务控制
service crond [start | stop | restart | status]
/etc/init.d/crond [start | stop | restart | status]

# crontab权限，默认所有用户都可使用crontab
# /etc/cron.allow    配置可以使用crontab的用户，优先级高
# /etc/cron.deny     配置不可以使用crontab的用户，优先级低

# 每个用户都有自己的crontab，用户自己的crontab任务记录到/var/spool/cron下
# /var/spool/cron/txazo

# crontab的执行记录
# /var/log/cron

# crontab任务格式
# 周和日月不可并存
# 0和7都是Sunday
#
# *    *    *    *    *    [user]    command
# 0-59 0-23 1-31 1-12 0-7  [user]    command
# 分   时   日   月   星期 [用户]    命令
#
# 1,5 * * * *  # 1分钟和5分钟
# * * * * 1-5  # 周一到周五
# * 0-23/2 * * *  # 每隔2小时
# */5 * * * *  # 每隔5分钟
# */3,*/5 * * * *  # 每隔3分钟或每隔5分钟

# /etc/cron.d
# 系统crontab任务

# /etc/crontab
#
# SHELL=/bin/bash
# PATH=/sbin:/bin:/usr/sbin:/usr/bin
# MAILTO=root
# HOME=/
#
# *  * * * *    root    run-parts /etc/cron.minutely
# 01 * * * *    root    run-parts /etc/cron.hourly
# 02 4 * * *    root    run-parts /etc/cron.daily
# 22 4 * * 0    root    run-parts /etc/cron.weekly
# 42 4 1 * *    root    run-parts /etc/cron.monthly

# /etc/cron.minutely  每分钟
# /etc/cron.hourly    每小时
# /etc/cron.daily     每天
# /etc/cron.weekly    每星期
# /etc/cron.monthly   每月

# crontab任务重定向标准和错误输出
# >> info.log 2>&1