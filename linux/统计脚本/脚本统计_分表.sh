# ********************< 百分表 >********************
function range100() {
  echo "" | awk '{
    for (i = 0; i < 100; i++) {
      printf("%02d\n", i);
    }
  }'
}

range100 | while read num
do
  echo MemberInfo_${num}
done

# ********************< 百分表 >********************
seq -f %02g 0 99 | while read num
do
  echo MemberInfo_${num}
done

# ********************< 百分表 >********************
seq -w 0 99 | while read num
do
  echo MemberInfo_${num}
done

# ********************< 千分表 >********************
function range1000() {
  echo "" | awk '{
    for (i = 0; i < 1000; i++) {
      printf("%03d\n", i);
    }
  }'
}

range1000 | while read num
do
  echo MemberInfo_${num}
done

# ********************< 千分表 >********************
seq -f %03g 0 999 | while read num
do
  echo MemberInfo_${num}
done

# ********************< 千分表 >********************
seq -w 0 999 | while read num
do
  echo MemberInfo_${num}
done