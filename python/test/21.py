# -*- coding: utf-8 -*-

# 词典dict：元素没有顺序

dic = {'tom' : 12, 'sam' : 32}
print dic, type(dic)
print dic['tom']
dic['sam'] = 30
print dic
print len(dic)

print '----------'

dic = {}
print type(dic)
dic['lili'] = 90
print dic

print '----------'

dic = {'tom' : 12, 'sam' : 32, 'lili' : 50}
for key in dic :
    print key, dic[key]

print dic.keys(), type(dic.keys())
print dic.values(), type(dic.values())
print dic.items(), type(dic.items())
dic.clear()
print dic

print '----------'

dic = {'tom' : 12, 'sam' : 32}
del dic['tom']
print dic
