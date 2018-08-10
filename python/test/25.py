# -*- coding: utf-8 -*-

# 循环设计

str = 'abcdefg'

for i in range(0, len(str), 1) :
    print str[i]

print '----------'

for i in range(0, len(str), 2) :
    print str[i]

print '----------'

for (index, char) in enumerate(str) :
    print index, char

print '----------'

ta = [1, 2, 3]
tb = [4, 5, 6]
tc = ['a', 'b' ,'c']
for (a, b, c) in zip(ta, tb, tc) :
    print a, b, c

