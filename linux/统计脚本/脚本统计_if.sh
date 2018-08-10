# if-elif-else-fi

# 单行if
if [ 2 -gt 1 ]; then echo "2 > 1"; fi

# 多行if
if [ 2 -gt 1]
then
  echo "2 > 1"
fi

# if-elif-else-fi
count=1
if [ $count -gt 0 ]; then
  echo "$count > 0"
elif [ $count -lt 0 ]; then
  echo "$count < 0"
else
  echo "$count = 0"
fi

# if-elif-else-fi
count=1
if [ $count -gt 0 ]
then
  echo "$count > 0"
elif [ $count -lt 0 ]
then
  echo "$count < 0"
else
  echo "$count = 0"
fi

# awk中if-else if-else(类似Java)
echo | awk 'BEGIN {count = 1} {
if (count > 0) {
  printf "%d > 0\n", count
} else if (count < 0) {
  printf "%d < 0\n", count
} else {
  printf "%d = 0\n", count
}
}'