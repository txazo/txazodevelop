# ********************< while >********************
i=0
while [ ${i} -lt 7 ]
do
  echo ${i}
  i=$[$i + 1]
done

# ********************< while read >********************
while read line
do
  echo ${line}
done < /etc/passwd

cat /etc/passwd | while read line
do
  echo ${line}
done

# ********************< awk while >********************
echo -e "12 23\n34 45" | awk 'BEGIN {ORS=""} {
	count = 1;
	while (count <= NF) {
	    print $count, "\t";
		count++;
	}
	print "\n";
}'