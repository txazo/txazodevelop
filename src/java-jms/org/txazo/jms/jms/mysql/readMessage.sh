#!/bin/sh
# Author: txazo
# Date: 2013-12-13
# Description: Read Message From MySQL

function executeSql() {
  sql="$1"

  if [ "$sql" = "" ]
  then
    cat | mysql -uroot -ptxazo1218 -N -s
  else
    echo "$sql" | mysql -uroot -ptxazo1218 -N -s
  fi
}

function readMessage() {
  echo "`date +"%Y-%m-%d %H:%M:%S"` ----- readMessage begin"
  
  id=`echo "select max(id) from jms.Message limit 1" | executeSql ""`
  if [ $id != "NULL" ]; then
    sql="select id, memberId, ip, subject, text, createTime from jms.Message where id <= $id"
	executeSql "$sql" > message.tmp
	sql="delete from jms.Message where id <= $id"
	executeSql "$sql"
	cat message.tmp
  fi
  
  echo "`date +"%Y-%m-%d %H:%M:%S"` ----- readMessage end"
}

function main() {
  readMessage
}

main