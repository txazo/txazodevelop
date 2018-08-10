# ********************< cat >********************
# 连接文件或标准输入到标准输出
# EOF，把内容当作标准输入传给程序

# 清除文件内容
cat /dev/null > data.tmp

# 显示文件内容
cat data.tmp

# 创建新文件或重写文件
cat > data.tmp

cat > data.tmp << EOF
HelloWorld
Welcome to Beijing
EOF

# 创建新文件或追加文件
cat >> data.tmp

cat >> data.tmp << EOF
HelloWorld
Welcome to Beijing
EOF

# 合并文件
cat 1.tmp 2.tmp > 3.tmp