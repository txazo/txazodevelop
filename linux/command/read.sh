# ********************< read >********************
# 读取标准输入

# 读取标准输入并赋值给变量
read name

# 用户输入时给出提示
read -p "Please input your name: " name

# 不显示用户输入
read -s -p "Please input your name: " name

# 一次读取并赋值给多个变量(空格隔开)
read -p "Please input your name and password: " name password

# 读取时不指定变量，数据赋值给环境变量REPLY
read -p "Please input your name: "

# 计时输入，计时器满未输入时返回0
if read -t 5 -p "Please input your name: " name; then
  echo "Hello, you name is $name"
else
  echo -e "\nSorry, time is out"
fi

# 计数输入，当输入的字符数达到指定数时，自动退出
read -n 1 -p "Do you want to continue [y/n]? " answer
case $answer in
  Y|y)
    echo -e "\nContinue"
	;;
  N|n)
    echo -e "\nGood Bye"
	;;
  *)
    echo -e "\nError Choice"
	;;
esac

# 逐行读取文件
while read line; do
  echo $line
done < /etc/passwd

# 程序自动输入
# yum install expect
# read.sh
#!/bin/bash

read -p "Please input your name: " name
echo "$name"
read -s -p "Please input the password: " password
echo -e "\n$password"

# expect.sh
#!/usr/bin/expect

spawn ./read.sh
expect "name: "
send "txazo\n"
expect "password: "
send "txazo1218\n"
expect eof
exit