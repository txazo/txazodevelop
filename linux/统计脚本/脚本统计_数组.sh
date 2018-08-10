# ********************< 数组定义 >********************
array=(1 2 3 4)
array=([0]=1 [1]=2 [2]=3 [3]=4)
str="1 2 3 4" array=($str)
array[0]=1;array[1]=2;array[2]=3;array[3]=4

# ********************< 数组访问 >********************
${array[0]} ${array[1]}
${array[*]} ${array[@]}

# ********************< 数组长度 >********************
${#array[*]} | ${#array[@]}

# ********************< 数组元素长度 >********************
${#array[0]}

# ********************< 删除数组 >********************
unset array
unset array[0]

# ********************< 子数组 >********************
${array[*]:2}  # (array[2] ...)
${array[*]::2}  # (array[0] array[1])
${array[*]:2:2}  # (array[2] array[3])

# ********************< 数组匹配 >********************
array=(one two three four)
${array[*] /o/}  # (ne tw three fur)
${array[*] /o/p}  # (pne twp three fpur)
${array[*] /#o/}  # (ne two three four)头匹配
${array[*] /#o/p}  # (pne two three four)头匹配
${array[*] /%o/}  # (one tw three four)尾匹配
${array[*] /%o/p}  # (one twp three four)尾匹配

# ********************< 字符串数组 >********************
str="1 2 3 4" array=($str)

# ********************< 数组循环 >********************
for i in ${array[*]}
do
  echo $i
done

for ((i=0; i < ${#array[*]}; i++))
do
  echo ${array[$i]}
done

# ********************< awk数组 >********************
echo ${array[*]} | awk 'BEGIN {FS=" "} {for (i = 1; i <= NF; i++) print $i}'