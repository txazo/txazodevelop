#!/bin/sh
# Author: tuxiaozhou
# Description: IndexTest

function executeSql() {
  sql="$1"
  
  if [ "$sql" = "" ]
  then
    cat | mysql -utxazo -ptxazo -N --local-infile
  else
    echo "$sql" | mysql -utxazo -ptxazo -N --local-infile
  fi
}

function execSql() {
  echo "CREATE TABLE IF NOT EXISTS test.IndexTest (
    id int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
	avatar varchar(50) NOT NULL default '' COMMENT 'avatar',
    PRIMARY KEY (id)
  ) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='IndexTest';" | executeSql
  
  echo "insert into test.IndexTest(avatar) values('root')" | executeSql
  echo "insert into test.IndexTest(avatar) values('test')" | executeSql
  echo "insert into test.IndexTest(avatar) values('admin')" | executeSql
  echo "insert into test.IndexTest(avatar) values('txazo')" | executeSql
  echo "insert into test.IndexTest(avatar) values('manager')" | executeSql
}

function main() {
  execSql
}

main