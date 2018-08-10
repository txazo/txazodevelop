#!/bin/bash
#
# chkconfig: 2345 105 45
# description: Service txazo

### BEGIN INIT INFO
# Provides:          txazo
# Default-Start:     2 3 4 5
# Default-Stop:      0 1 6
# Short-Description: The Service txazo
### END INIT INFO

usage() {
  echo "Usage: /etc/init.d/txazo [start | stop | status | restart]"
}

start() {
  echo "txazo start/running"
}

stop() {
  echo "txazo stop/waiting"
}

restart() {
  stop
  start
}

status() {
  echo "txazo start/running"
}

case "$1" in
  start)
        start
        ;;
  stop)
        stop
        ;;
  restart)
        restart
        ;;
  status)
        status
        ;;
  *)
        usage
        ;;
esac

exit 0