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

main "message" "Message" "id int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
      memberId int(11) NOT NULL DEFAULT 0 COMMENT '用户ID',
	  memberName varchar(20) NOT NULL DEFAULT '' COMMENT '用户昵称',
	  objectId int(11) NOT NULL DEFAULT 0 COMMENT '对方ID',
	  objectName varchar(20) NOT NULL DEFAULT '' COMMENT '对方昵称',
      ip varchar(15) NOT NULL DEFAULT '' COMMENT '发送者IP',
	  type tinyint(2) NOT NULL default 0 COMMENT '类别(0-普通站内信，1-系统站内信)',
	  contentId bigint(20) NOT NULL DEFAULT 0 COMMENT '内容ID',
	  isRead tinyint(2) NOT NULL default 0 COMMENT '是否已读(0-未读，1-已读)',
	  outBox tinyint(2) NOT NULL default 0 COMMENT '发件箱(1-发件箱)',
	  inBox tinyint(2) NOT NULL default 0 COMMENT '收件箱(1-收件箱)',
	  collectBox tinyint(2) NOT NULL default 0 COMMENT '收藏箱(1-收藏箱)',
	  removeBox tinyint(2) NOT NULL default 0 COMMENT '垃圾箱(1-垃圾箱)',
      createTime datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '发送时间',
	  updateTime datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '更新时间',
      PRIMARY KEY (id),
	  KEY (memberId),
	  KEY (createTime)" "10" "站内信"