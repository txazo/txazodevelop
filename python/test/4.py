# -*- coding: utf-8 -*-
# tuple：不可变更
s1 = (12, 12.5, True, "hello")
print s1, type(s1)
print s1[0]
print s1[-1] # 最后一个
print s1[-2] # 倒数第二个
print s1[:3] #(0, 1, 2)
print s1[1:] #1到最后
print s1[1:3] #(1, 2)
print s1[0:4:2] #(0, 2)
print s1[3:1:-1] #(3, 2)

# list：可以变更
s2 = [12, 12.5, True, "hello"]
print s2, type(s2)
print s2[0]
s2[1] = False
print s2[1]


# 字符串(tuple)
str = "abcdefg"
print str[2]
