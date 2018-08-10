# -*- coding: utf-8 -*-

# 函数对象

def func(x, y) :
    return x + y

# 函数作为对象传参
def test(f, a, b) :
    print f(a, b)

test(func, 1, 2)

# map函数
def quart(x) :
    return x**2
list = map(quart, [1, 2, 3])
print list

list = map(func, [1, 2, 3], [101, 102, 103])
print list

print type(list)

# filter函数
def test(a) :
    if (a % 2 == 0) :
        return True
    else :
        return False

print filter(test, [1, 2, 3, 4, 5, 6])

# reduce函数
print reduce(func, [1, 2, 3, 4])
