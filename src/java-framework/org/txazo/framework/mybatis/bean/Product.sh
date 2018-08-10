#!/bin/sh
# Author: txazo
# Description: Create Table

function executeSql() {
  sql="$1"
  
  if [ "$sql" = "" ]
  then
    cat | mysql -uroot -proot -N -s --local-infile
  else
    echo "$sql" | mysql -uroot -proot -N -s --local-infile
  fi
}

function createTable() {
  echo "CREATE TABLE IF NOT EXISTS test.Product (
    id int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
	name varchar(30) NOT NULL DEFAULT '' COMMENT 'name',
	price double(8,2) NOT NULL DEFAULT 0.00 COMMENT 'price',
	createTime datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT 'createTime',
    PRIMARY KEY (id),
    UNIQUE (name)
  ) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='Product';" | executeSql
}

function main() {
  createTable
}

main