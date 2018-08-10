# ********************< 列表 >********************
list = [1, 2, 3, 4]
print type(list), list  # <type 'list'> [1, 2, 3, 4]

# ********************< list函数 >********************
list = list("1234")
print type(list), list  # <type 'list'> [1, 2, 3, 4]

# ********************< 列表元素赋值 >********************
list[0] = 1

# ********************< 删除列表元素 >********************
del list[0]
del list

# ********************< class list(object) >********************
list()
list(iterable)
append(object)  # 列表末尾追加新的对象
count(value)  # value在列表中出现的次数
extend(iterable)  # 列表末尾追加新的列表
index(value, [start, [stop]])  # value在列表中第一次出现的位置
insert(index, object)  # 在列表的index位置插入新的对象
pop([index])  # 移除列表中index位置的元素，并返回该元素
remove(value)  # 移除列表中value的第一个匹配项
reverse()  # 列表倒序
sort(cmp=None, key=None, reverse=False)  # 排序列表
