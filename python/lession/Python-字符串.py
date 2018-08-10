# ********************< 单引号 >********************
print 'Let\'s go!'
print 'She said, "Hello World!"'

# ********************< 双引号 >********************
print "Let's go!"
print "She said, \"Hello World!\""

# ********************< 三引号 >********************
print '''Hello!
Welcome to Beijing.
Thank you!'''

# ********************< 字符串跨行 >********************
print 'Hello!\
Welcome to Beijing.\
Thank you!'

# ********************< 拼接字符串 >********************
print "Let's go. " 'She said, "Hello World!"'
a = "Hello, "
b = "World!"
c = a + b
print c

# ********************< str >********************
print str(10000L)  # 10000
print str("Hello Woorld!")  # Hello Woorld!

# ********************< repr >********************
print repr(10000L)  # 10000L
print repr("Hello Woorld!")  # 'Hello Woorld!'