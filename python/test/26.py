# -*- coding: utf-8 -*-

# 循环对象

for line in open('D:a.txt', 'r') :
    print line

print '----------'

# 生成器(generator)
def gen() :
    a = 100
    yield a
    a += 100
    yield a
    yield 500

print type(gen())
for i in gen() :
    print i

print '----------'

def gen() :
    for i in range(4) :
        yield i

print type(gen())
for i in gen() :
    print i

print '----------'

a = (x for x in range(4))
print type(a)
for i in a :
    print i

# 表推导
print '----------'
L = [x**2 for x in range(4)]
print type(L), L
