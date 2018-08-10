# ********************< 元祖 >********************
tuple = (1, 2, 3, 4)
print type(tuple), tuple  # <type 'tuple'> (1, 2, 3, 4)

# ********************< 空元祖 >********************
tuple = ()
print type(tuple), tuple  # <type 'tuple'> ()

# ********************< 单值元祖 >********************
tuple = (1,)
print type(tuple), tuple  # <type 'tuple'> (1,)

tuple = (1)
print type(tuple), tuple  # <type 'int'> 1

# ********************< tuple函数 >********************
tuple = tuple([1, 2, 3, 4])
print type(tuple), tuple  # <type 'tuple'> (1, 2, 3, 4)

tuple = tuple("1234")
print type(tuple), tuple  # <type 'tuple'> (1, 2, 3, 4)

tuple = tuple((1, 2, 3, 4))
print type(tuple), tuple  # <type 'tuple'> (1, 2, 3, 4)

# ********************< 元祖访问 >********************
tuple = (1, 2, 3, 4)
print tuple[0], tuple[1], tuple[2], tuple[3]  # 1 2 3 4
print tuple[-1], tuple[-2], tuple[-3], tuple[-4]  # 4 3 2 1