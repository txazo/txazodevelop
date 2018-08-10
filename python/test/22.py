# -*- coding: utf-8 -*-

# 文件读写
f = open('D:/a.txt', 'r')
line = f.readline()
print line, type(line)
f.close()

print '----------'

f = open('D:/a.txt', 'r')
lines = f.readlines()
print lines, type(lines)
f.close()

print '----------'

f = open('D:a.txt', 'w')
f.write('Haha')
f.close()
