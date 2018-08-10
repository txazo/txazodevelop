import MySQLdb

def insert() :
    try :
        conn = MySQLdb.connect(host = 'localhost', user = 'root', passwd = 'root', db = 'txazo', port = 3306)
        cur = conn.cursor()
        cur.execute('drop database if exists txazo')
		cur.close()
        conn.commit()
        conn.close()
    except MySQLdb.Error,e:
        conn.rollback()
        conn.close()
        print "Mysql Error %d: %s" % (e.args[0], e.args[1])

query()
