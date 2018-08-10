#!/bin/sh
# Author: txazo
# Date: 2014-03-21
# Description: MySQL Sub Table

function executeSql() {
  sql="$1"
  
  if [ "$sql" = "" ]
  then
    cat | mysql -uroot -ptxazo1218 -N -s --local-infile
  else
    echo "$sql" | mysql -uroot -ptxazo1218 -N -s --local-infile
  fi
}

function createSubTable() {
  num=$1
  echo "CREATE TABLE IF NOT EXISTS test.SubTest_$num (
    id int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    name varchar(20) NOT NULL default '' COMMENT 'Name',
    createTime datetime NOT NULL default '1970-01-01 00:00:00' COMMENT 'CreateTime',
    PRIMARY KEY (id)
  ) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='SubTest_$num';" | executeSql
}

function createTable() {
  subTableStr=$1
  echo "USE test; CREATE TABLE IF NOT EXISTS SubTest (
    id int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    name varchar(20) NOT NULL default '' COMMENT 'Name',
    createTime datetime NOT NULL default '1970-01-01 00:00:00' COMMENT 'CreateTime',
    PRIMARY KEY (id)
  ) ENGINE=MRG_MyISAM DEFAULT CHARSET=utf8 UNION=(${subTableStr}) COMMENT='SubTest';" | executeSql
}

function main() {
  subTableStr=''
  for i in $(seq 0 9)
  do
    createSubTable $i
    if [ $i = "0" ]; then
      subTableStr="SubTest_$i"
    else
      subTableStr="${subTableStr},SubTest_$i"
    fi
  done
  
  createTable $subTableStr
}

main