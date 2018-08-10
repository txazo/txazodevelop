#!/bin/sh
# Author: txazo
# Description: Common Script

# ��ʼ��
function init() {
  theDay="$1"
  if [ "$theDay" = "" ]
  then
    theDay=`date +%Y%m%d -d "1 days ago"`
  fi
  theDay=`date +%Y%m%d -d "$theDay"`
  theNextDay=`date +%Y%m%d -d "$theDay 1 days"`
  
  tabChar=`echo -e "\t"`
}

# ����ִ��SQL
function executeSql() {
  sql="$1"

  if [ "$sql" = "" ]
  then
    cat | mysql -uroot -N -s --local-infile
  else
    echo "$sql" | mysql -uroot -N -s --local-infile
  fi
}

# Զ��ִ��SQL
function executeRemoteSql() {
  sql="$1"
  ip="$2"

  if [ "$sql" = "" ]
  then
    cat | mysql -uroot -proot -h$ip -N -s --local-infile
  else
    echo "$sql" | mysql -uroot -proot -h$ip -N -s --local-infile
  fi
}

# 208ִ��SQL
function executeSqlFrom208() {
  sql="$1"

  if [ "$sql" = "" ]
  then
    cat | mysql-ib -uroot -proot -h127.0.0.1 test -N -s --local-infile
  else
    echo "$sql" | mysql-ib -uroot -proot -h127.0.0.1 test -N -s --local-infile
  fi
}

# range 99 (00 - 99)
function range() {
  num=$1
  seq -w $num
}

# rangeDate "20131001" "20131130"
function rangeDate() {
  beginDate="$1"
  endDate="$2"
  
  while [ true ]
  do
    echo $beginDate
    if [ "$beginDate" == "$endDate" ]; then
      break;
    fi
    beginDate=`date +%Y%m%d -d "$beginDate 1 days"`
  done
}

# ����
function createTable() {
  echo "CREATE TABLE IF NOT EXISTS test.Common (
    id int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
    memberId int(11) NOT NULL COMMENT 'memberId',
    number tinyint(1) NOT NULL DEFAULT '0' COMMENT 'number',
    string varchar(15) NOT NULL DEFAULT '' COMMENT 'string',
    createTime datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT 'createTime',
    PRIMARY KEY (id),
    KEY (memberId)
  ) ENGINE=MyISAM DEFAULT CHARSET=gbk COMMENT='Common';" | executeSql
}

# ������������
function loadData() {
  echo "load data local infile 'data.tmp' [replace] into table test.Common
        fields terminated by '\t' lines terminated by '\n'
        (memberId, number, string, createTime);" | executeSql
}

function main() {
  # �ٷֱ�
  rm -f sql.tmp
  range 99 | while read num
  do
    echo "select * from test.Common_$num where createTime >= \""$theDay"\" and createTime < \""$theNextDay"\";" >> sql.tmp
  done
  cat sql.tmp | executeRemoteSql "" "127.0.0.1" > data.tmp
  
  # PV��
  rangeDate "20131001" "20131130" | while read date
  do
    sql="select memberid from test.t_pv_$date where pv_url = \"/test.jsps\";"
    executeSqlFrom208 "$sql" > data.tmp
  done
  
  # ����
  workCity=(10101002 10101007 10116001 10131003 10131009)
  for ((i = 0; i < ${#workCity[*]}; i++))
  do
    echo ${workCity[$i]}
  done
  
  # sed
  echo "memberId=1000" | sed 's/memberId=\([0-9]\+\)/\1/' # 1000
  
  # awk
  cat data.tmp | awk 'BEGIN {FS = "\t"}
  function add(num1, num2) {
      return num1 + num2
  } {
      if (!data[$1, $2, $3]) {
        data[$1, $2, $3] = 1
      }
  } END {
      for (i in data) {
        split(i, p, "\034");
        printf("%s\t%s\t%s\n", p[1], p[2], p[3], data[i]);
      }
  }'
  
  # find
  find /data/log_backup/access_log -name 'MemberAction*'
}

main