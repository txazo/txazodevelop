try :
    re = iter(range(5))

    #raise StopIteration

    for i in range(100):
        print re.next()

    print 'HaHaHaHa'
except StopIteration :
    print 'here id end', i


#try :
#
#except error1 :
#   
#except error2 :
#
#else :
#
#finally :
#
