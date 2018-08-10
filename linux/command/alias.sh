# ********************< alias >********************
# 设置指令的别名
# 可编辑/etc/profile或~/.bashrc设置指令的别名

# 显示所有指令的别名
alias grep

# 显示指定指令的别名
alias ls
alias ls grep

# 设置指令的别名
alias grep='grep --color=auto'

# 取消指令的别名设置
unalias grep

# 自定义alias文件
cd ~
vi .alias
alias grep='grep --color=auto'
vi .bashrc
if [ -f ~/.alias ]; then
    . ~/.alias
fi
