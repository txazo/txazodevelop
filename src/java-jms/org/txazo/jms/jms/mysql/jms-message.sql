CREATE TABLE IF NOT EXISTS jms.Message (
	id int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
	memberId int(10) NOT NULL COMMENT 'memberId',
	ip varchar(15) NOT NULL default '' COMMENT 'ip',
	subject varchar(50) NOT NULL default '' COMMENT 'subject',
	text varchar(300) NOT NULL default '' COMMENT 'text',
	createTime datetime NOT NULL default '1970-01-01 00:00:00' COMMENT 'createTime',
	PRIMARY KEY (id),
	KEY (memberId)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='Message';