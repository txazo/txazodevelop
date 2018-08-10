# ********************< for >********************
for ((i=1; i < 10; i++))
do
  echo $i
done

# ********************< for 数组 >********************
array=(1 2 3 4 5)
for ((i=0; i < ${#array[*]}; i++))
do
  echo ${array[$i]}
done

# ********************< for 数组 >********************
array=(1 2 3 4 5)
for i in ${array[*]}
do
  echo $i
done

# ********************< for in >********************
for i in $(seq 10)
do
  echo $i
done

# ********************< for in >********************
for i in $(seq 10 20)
do
  echo $i
done

# ********************< for in >********************
for i in $(ls)
do
  echo $i
done

# ********************< awk for >********************
array=(1 2 3 4 5)
echo | awk '{
  for (i = 0; i < 5; i++) {
    print i, 'array[i]'
  }
}'

echo | awk '{
  for (i = 1; i <= 5; i++) {
    array[i] = i;
  }
  for (i in array) {
    print i, array[i]
  }
}'