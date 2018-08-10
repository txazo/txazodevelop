# -*- coding: utf-8 -*-

# list是一个类
a = [1, 3, 2, 4, 5] # a是list的一个对象
print type(a)
print a.count(5)
print a.index(2)
a.append(6)
a.sort()
print a
a.pop()
print a
a.remove(5)
print a
a.insert(0, 9)
print a
