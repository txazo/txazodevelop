import MySQLdb

def insert() :
    try :
        conn = MySQLdb.connect(host = 'localhost', user = 'root', passwd = 'root', db = 'txazo', port = 3306)
        cur = conn.cursor()
        cur.execute('drop database if exists txazo')
        print 'sql: drop database if exists txazo'
        cur.execute('create database txazo default character set utf8')
        print 'sql: create database txazo default character set utf8'
        cur.execute('create table txazo.user(id int(10) not null auto_increment, nickname varchar(30) not null, primary key(id))')
        print 'sql: create table txazo.user(id int(10) not null auto_increment, nickname varchar(30) not null, primary key(id))'
        
        values = [[1, 'tom'], [2, 'tom'], [3, 'tom'], [4, 'tom'], [5, 'tom']]
        for i, value in enumerate(values):
            cur.execute('insert into txazo.user values(%s, %s)', value)

        cur.execute('update txazo.user set nickname = "txazo"')
        print 'sql: update txazo.user set nickname = "txazo"'

        count = cur.execute('select * from txazo.user')
        print 'sql: select * from txazo.user'
        print 'Results: %s records' % count

        result = cur.fetchone()
        print 'id: %s, nickname: %s' % result
        
        results = cur.fetchmany(5)
        for r in results :
            print 'id: %s, nickname: %s' % r

        conn.commit()
        cur.close()
        conn.close()
    except MySQLdb.Error,e:
        conn.rollback()
        conn.close()
        print "Mysql Error %d: %s" % (e.args[0], e.args[1])

insert()
