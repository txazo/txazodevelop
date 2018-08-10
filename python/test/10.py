# -*- coding: utf-8 -*-

# 面向对象

class Human(object) :
    laugh = 'haha'
    def show_laugh(self) :
        print self.laugh
    def laugh_100th(self) :
        for i in range(100) :
            self.show_laugh()

li_lei = Human()
li_lei.laugh_100th()

print '----------'

class HappyBird(object) :
    def __init__(self, more_words) :
        print 'We are happy birds.', more_words


summer = HappyBird('HappyHappy!')

print '----------'

class Man(object) :
    def __init__(self, input_gender) :
        self.gender = input_gender
    def printGender(self) :
        print self.gender

my = Man('male')
print my.gender
my.printGender()
