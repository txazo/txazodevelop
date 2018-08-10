# ********************< netstat >********************
# 显示网络信息

netstat -a        # 列出所有联机状态
netstat -t        # 仅列出TCP的联机状态
netstat -u        # 仅列出UDP的联机状态
netstat -u        # 仅列出Unix的联机状态
netstat -n        # 不显示别名，以数字(IP)显示
netstat -l        # 仅列出在Listen的联机状态
netstat -p        # 列出PID和档名
netstat -c        # 设置每几秒更新一次

# 列出所有网络联机状态
netstat -a

# 列出所有TCP联机状态
netstat -at

# 列出所有UDP联机状态
netstat -au

# 列出监听的TCP和UDP联机状态
netstat -tulnp

# 列出所有TCP和UDP联机状态
netstat -atunp

# 显示路由信息
netstat -r
netstat -rn

# 持续更新联机状态
netstat -c 5

# 列出指定端口的联机状态
netstat -an | grep -e ':8080\>'