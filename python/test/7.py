# -*- coding: utf-8 -*-

list = [1, 2, 3, 4, 5]
for i in list :
    print i

print "----------"

# range(n)-list类型
list = range(5)
print list

print "----------"

for i in range(10) :
    print i**2

print "----------"

i = 0;
while i < 10 :
    if i == 5 :
        i += 1
        continue
    elif i == 7 :
        break
    print i;
    i += 1;
