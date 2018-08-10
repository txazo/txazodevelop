# -*- coding: utf-8 -*-

# 面向对象

class Bird(object) :
    have_feather = True
    way_of_reproduction = 'egg'
    def move(self, dx, dy) :
        position = [0, 0]
        position[0] += dx
        position[1] += dy
        return position

class Chicken(Bird) :
    way_of_move = 'walk'


summer = Bird()
print summer.way_of_reproduction
print 'after move', summer.move(5, 8)

summer = Chicken()
print summer.way_of_move
