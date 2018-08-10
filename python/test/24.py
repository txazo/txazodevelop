# -*- coding: utf-8 -*-

# 函数参数传递
# 先位置，在关键字，再包裹位置，在包裹关键字


# 位置传递
def f(a, b, c) :
    print a * b + c
print f(1, 2, 3)

print '----------'

# 关键字参数传递
def f(a, b, c) :
    print a * b + c
print f(c = 1, a = 2, b = 3)

print '----------'

# 位置传递和关键字参数传递混用
def f(a, b, c) :
    print a * b + c
print f(2, b = 3, c = 4)

print '----------'

# 参数默认值
def f(a, b, c = 10) :
    print a * b + c
print f(2, 3)
print f(2, 3, 4)

print '----------'

# 包裹位置传递
def func(*name) :
    print type(name)
    for i in name :
        print i
    print name # tuple
func(1, 4, 6)

print '----------'

# 包裹关键字传递
def func(**dict) :
    print type(dict)
    for key in dict :
        print key, dict[key]
    print dict # dict
func(a = 1, b = 4)

print '----------'

# 解包裹
def func(a, b, c) :
    print a, b, c
args = (1, 2, 3)
func(*args)

def func(a, b, c) :
    print a, b, c
dic = {'a' : 12, 'b' : 23, 'c' : 34}
func(**dic)
