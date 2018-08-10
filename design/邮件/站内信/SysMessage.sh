#!/bin/sh
# Author: txazo
# Date: 2014-03-21
# Description: MySQL Create Sub Table

function init() {
  database="$1"
  tableName="$2"
  sql="$3"
  subTableAmount="$4"
  tableComment="$5"
  
  paramFlag=1
  
  if [ "$database" = "" ]; then paramFlag=0; fi
  if [ "$tableName" = "" ]; then paramFlag=0; fi
  if [ "$sql" = "" ]; then paramFlag=0; fi
  if [ "$subTableAmount" = "" ]; then subTableAmount=10; fi
  if [ "$tableComment" = "" ]; then paramFlag=0; fi
  
  createSubSql="$sql) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='$tableComment'";
  createMRGSql="$sql) ENGINE=MRG_MyISAM DEFAULT CHARSET=utf8 UNION=("
  
  let subTableAmount--
  
  unionSql=""
  for num in `seq 0 $subTableAmount`
  do
    unionSql="$unionSql${tableName}_$num,"
  done

  unionSql=`echo $unionSql | awk '{print substr($0, 0, length($0) - 1)}'`
  createMRGSql="$createMRGSql$unionSql) COMMENT='$tableComment'"
}

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
  seq 0 $subTableAmount | while read num
  do 
    sql="CREATE TABLE IF NOT EXISTS $database.${tableName}_$num($createSubSql;"
	executeSql "$sql"
    echo "create table $database.${tableName}_$num ok..."
  done
}

function createMRGTable() {
  sql="USE $database;CREATE TABLE IF NOT EXISTS $database.$tableName($createMRGSql;"
  executeSql "$sql"
  echo "create table $database.$tableName ok..."
}

function main() {
  init "$1" "$2" "$3" "$4" "$5"
  
  if [ "$paramFlag" = "1" ]; then
    echo "CREATE DATABASE IF NOT EXISTS $database;" | executeSql
    createSubTable
	createMRGTable
  else
    echo "parameters not right"
  fi
}

main "message" "SysMessage" "id int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
	  groupId tinyint(4) NOT NULL DEFAULT 0 COMMENT '用户组ID(0-全部用户组)',
	  contentId bigint(20) NOT NULL DEFAULT 0 COMMENT '内容ID',
      beginTime date NOT NULL DEFAULT '1970-01-01' COMMENT '开始时间',
      endTime date NOT NULL DEFAULT '1970-01-01' COMMENT '结束时间',
	  createTime datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
      PRIMARY KEY (id),
	  KEY (beginTime),
	  KEY (endTime)" "10" "系统站内信"