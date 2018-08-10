# -*- coding: utf-8 -*-
#!/usr/bin/python 

# if-elif-else
a = 0
if a > 1:
    print('a > 1')
elif a == 1:
    print('a == 1')
else:
    print('a < 1')

# while
a = 0
while a < 5:
    a += 1
    print(a)
else:
    print('while end')

# for
for i in range(1, 5):
    print(i)
else:
    print('for end')

# break
for i in range(1, 5):
    print(i)
    if i > 3:
        break;
else:
    print('break')

# continue
for i in range(1, 5):
    if i > 3:
        break;
    print(i)
else:
    print('continue')
