# Shell中执行数据库操作
echo "show databases" | mysql -uroot -N -s
echo "show tables" | mysql -utest -ptest -h127.0.0.1 test -N -s
# -N              输出中去掉列名
# -s              使用制表符作为分隔符
# --local-infile  是否允许导入本地SQL文件

# 查询
echo "select payDate from test.DailyPay;" | executeSql > pay.tmp

# 清空表数据
echo "truncate table root.DailyPay;" | executeSql

# 批量插入
echo "load data local infile 'data.tmp' into table root.DailyPay
      fields terminated by '\t' lines terminated by '\n'
	  (id, payDate, channelId, channelName, platform);" | executeSql
	  
# 批量更新
echo "load data local infile 'data.tmp' replace into table root.DailyPay
      fields terminated by '\t' lines terminated by '\n'
	  (id, payDate, channelId, channelName, platform);" | executeSql
	  
mysql -utest -ptest -h127.0.0.1 core_user -N --local-infile
# 加上参数 --local-infile
	  
# 少量sql语句
echo "select * from DailyPay;" | executeSql

# 较多sql语句，先写入到文件
cat sql.tmp | executeSql

function executeSql() {
  sql="$1"
  
  if [ "$sql" = "" ]
  then
    cat | mysql -uroot -N --local-infile
  else
    echo "$sql" | mysql -uroot -N --local-infile
  fi
}

cat sql.tmp | executeRemoteSql > result.tmp
executeRemoteSql "$sql" > result.tmp

function executeRemoteSql() {
  sql="$1"
  ip="$2"

  if [ "$sql" = "" ]
  then
    cat | mysql -utest -ptest -h$ip -N
  else
    echo "$sql" | mysql -utest -ptest -h$ip -N
  fi
}

cat sql.tmp | executeRemoteSql "" "127.0.0.1" > result.tmp
executeRemoteSql "$sql" "127.0.0.1" > result.tmp

function executeRemoteSqlUserPwd() {
  sql="$1"
  ip="$2"
  user="$3"
  password="$4"

  if [ "$sql" = "" ]
  then
    cat | mysql -u$user -p$password -h$ip -N
  else
    echo "$sql" | mysql -u$user -p$password -h$ip -N
  fi
}

# 建表语句模板
CREATE TABLE IF NOT EXISTS root.RegisterProcessV2(
	id int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
	regDate date NOT NULL COMMENT '注册日期',
	channelId varchar(8) NOT NULL COMMENT '渠道号',
	channelName varchar(30) NOT NULL COMMENT '渠道名称',
	platform varchar(15) NOT NULL COMMENT '平台类型: web | light',
	firstStep smallint(6) default 0 COMMENT '第一步用户数',
	secondStep smallint(6) default 0 COMMENT '第二步用户数',
	thirdStep smallint(6) default 0 COMMENT '第三步用户数',
	fourthStep smallint(6) default 0 COMMENT '第四步用户数',
	wastageRate varchar(8) NOT NULL COMMENT '用户流失率',
	validateRate varchar(8) NOT NULL COMMENT '号码验证率',
	avatarUpload smallint(6) default 0 COMMENT '头像上传数',
	regVersion varchar(6) NOT NULL COMMENT '注册版本',
	reserveField1 varchar(10) NOT NULL default '0' COMMENT '预留字段1',
	reserveField2 varchar(10) NOT NULL default '0' COMMENT '预留字段2',
	reserveField3 varchar(10) NOT NULL default '0' COMMENT '预留字段3',
	reserveField4 varchar(10) NOT NULL default '0' COMMENT '预留字段4',
	PRIMARY KEY (id),
	KEY (regDate),
	KEY (channelId),
	KEY (platform),
	KEY (regVersion)
) ENGINE=MyISAM DEFAULT CHARSET=gbk COMMENT='注册流程表';

cat sql.tmp | executeRemoteSqlUserPwd "" "127.0.0.1" "test" "test" > result.tmp
executeRemoteSqlUserPwd "$sql" "127.0.0.1" "test" "test" > result.tmp

select ifnull(MemberId, -1) from LightMember order by CreateTime limit 20;
select if(MemberId is not null, 1, -1) from LightMember order by CreateTime limit 20;
select 1 + 2 from LightMember limit 1;