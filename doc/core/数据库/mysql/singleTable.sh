#!/bin/sh
# Author: txazo
# Description: MySQL Create Single Table

function executeSql() {
  sql="$1"
  
  if [ "$sql" = "" ]
  then
    cat | mysql -uroot -ptxazo1218 -N -s --local-infile
  else
    echo "$sql" | mysql -uroot -ptxazo1218 -N -s --local-infile
  fi
}

function executeRemoteSql() {
  sql="$1"
  ip="$2"

  if [ "$sql" = "" ]
  then
    cat | mysql -h$ip -uroot -ptxazo1218 -N -s --local-infile
  else
    echo "$sql" | mysql -h$ip -uroot -ptxazo1218 -N -s --local-infile
  fi
}

function createTable() {
  echo "CREATE DATABASE IF NOT EXISTS email;" | executeSql
  echo "CREATE TABLE IF NOT EXISTS email.Email (
    id int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
	sender varchar(30) NOT NULL DEFAULT '' COMMENT '发送方',
	receiver varchar(30) NOT NULL DEFAULT '' COMMENT '接收方',
	title varchar(100) NOT NULL DEFAULT '' COMMENT '邮件标题',
	content text NOT NULL COMMENT '邮件内容',
    createTime datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '发送时间',
    PRIMARY KEY (id),
	key (createTime)
  ) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='邮件';" | executeSql
}

function main() {
  createTable
}

main