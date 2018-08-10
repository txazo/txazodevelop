# ********************< more >********************
# 分页显示文件

more +n           # 从第n行开始显示
more -n           # 定义屏幕大小为n行
more +/pattern    # 从第一个匹配的行的前两行开始显示
more -c           # 顶部清屏显示
more -p           # 顶部清屏显示
more -d           # 显示提示信息

more +10 -20 +/txazo -cd /etc/passwd

# 操作命令
Enter             # 向下一行
Space             # 向下滚一屏
=                 # 输出当前行号
v                 # 调用Vi编辑器编辑
q                 # 退出