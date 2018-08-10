# -*- encoding: utf-8 -*-

# 值传递
def square(a, b) :
    c = a**2 + b**2
    return c

a = 3;
b = 4;
print square(a, b)

# 指针传递
list = [1, 2, 3, 4]
def changeList(list) :
    list[0] += 1

changeList(list)
print list
