#!/bin/sh
# Author: txazo
# Description: Create Table

function executeSql() {
  sql="$1"
  
  if [ "$sql" = "" ]
  then
    cat | mysql -utxazo -ptxazo -N -s --local-infile
  else
    echo "$sql" | mysql -utxazo -ptxazo -N -s --local-infile
  fi
}

function createTable() {
  echo "CREATE TABLE IF NOT EXISTS test.Test (
    id int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
    memberId int(11) NOT NULL DEFAULT 0 COMMENT 'memberId',
    number tinyint(1) NOT NULL DEFAULT 0 COMMENT 'number',
	float float(6,2) NOT NULL DEFAULT 0.00 COMMENT 'float',
    string varchar(15) NOT NULL DEFAULT '' COMMENT 'string',
    createTime datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT 'createTime',
    PRIMARY KEY (id),
    KEY (memberId)
  ) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='Test';" | executeSql
}

function main() {
  createTable
}

main