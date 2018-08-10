#!/bin/sh
# 
# chkconfig: - 55 45
# description:  Memcached

IP="112.124.6.220"
PORT=9998
USER=root
MAXCONN=1024
CACHESIZE=64
PIDFILE=/var/run/memcached.pid

prog="Memcached"
EXEC=/usr/local/memcached/bin/memcached

start() {
    if [ -f $PIDFILE ]; then
	    echo "$prog is already running"
	else
	    echo "Starting $prog..."
		$EXEC -d -m $CACHESIZE -u $USER -l $IP -p $PORT -c $MAXCONN -P $PIDFILE
	fi
}

stop() {
    if [ ! -f $PIDFILE ]; then
	    echo "prog is not running"
	else
	    PID=$(cat $PIDFILE)
		echo "Stopping $prog ..."
		kill -9 $PID
		while [ -x /proc/${PID} ]; do
		    echo "Waiting for $prog to shutdown ..."
			sleep 1
		done
		rm -f $PIDFILE
		echo "$prog is stopped"
	fi
}

status() {
  if [ -f $PIDFILE ]; then
      echo "$prog is running"
  else
      echo "$prog is stopped"
  fi
}

restart() {
    stop
    start
}

case "$1" in
    start)
        start
        ;;
    stop)
        stop
        ;;
    status)
        status
        ;;
    restart)
        restart
        ;;
    *)
        echo $"Usage: $0 {start | stop | status | restart}"
		;;
esac