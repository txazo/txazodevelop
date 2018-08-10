#!/bin/sh
# Description: Common

function init() {
  theDay="$1"
  if [ "$theDay" = "" ]
  then
    theDay=`date +%Y%m%d`
  fi
  theDay=`date +%Y%m%d -d "$theDay"`
  theNextDay=`date +%Y%m%d -d "$theDay 1 days ago"`
  
  tabChar=`echo -e "\t"`
}

function executeSql() {
  sql="$1"
  
  if [ "$sql" = "" ]
  then
    cat | mysql -uroot -N -s --local-infile
  else
    echo "$sql" | mysql -uroot -N -s --local-infile
  fi
}

function executeRemoteSql() {
  sql="$1"
  ip="$2"
  
  if [ "$sql" = "" ]
  then
    cat | mysql -h$ip -uroot -proot -N -s --local-infile
  else
    echo "$sql" | mysql -h$ip -uroot -proot -N -s --local-infile
  fi
}

function executeSqlFrom208() {
  sql="$1"
  
  if [ "$sql" = "" ]
  then
    cat | mysql-ib -uroot -proot -h127.0.0.1 test -N -s --local-infile
  else
    echo "$sql" | mysql-ib -uroot -proot -h127.0.0.1 test -N -s --local-infile
  fi
}

function range() {
  num1="$1"
  num2="$2"
  seq -w $num1 $num2
}

function  sql() {
  echo "select * from test" | executeSql > data.tmp
  
  sql="select * from test where createTime >= \""$theDay"\""
  executeRemoteSql "$sql" "127.0.0.1" > data.tmp
  
  rm -f sql.tmp
  range 0 99 | while read num
  do
    echo "select memberId from test$num" >> sql.tmp
  done
  cat sql.tmp | executeRemoteSql "" "127.0.0.1" > memberId.tmp
  
  cat memberId.tmp | awk '{
      printf("select * from test_%02d where memberId = %s limit 1;\n", $1 % 100, $1)
  }' > sql.tmp
}

function cat() {
  cat data.tmp | awk '{print $0}'
  cat d1.tmp d2.tmp > data.tmp
}

function uniq() {
  cat data.tmp | sort | uniq -c
}

function sort() {
  
}

function join() {
  join -t "$tabChar" d1.tmp d2.tmp
}

function sed() {
  
}

function array() {
  
}

function dateRange() {
  theDay="20140101"
  while [ "$theDay" != "20140110" ]
  do
    echo "$theDay"
	theDay=`date +%Y%m%d -d "$theDay 1 days"`
  done
  
  theDay="20140101"
  count=0
  while [ "$count" != "20" ]
  do
    echo "$theDay"
    let count++
	theDay=`date +%Y%m%d -d "$theDay 1 days"`
  done
}