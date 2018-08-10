CREATE DATABASE IF NOT EXISTS develop;

CREATE TABLE IF NOT EXISTS develop.Mail (
	id int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
	to varchar(50) NOT NULL DEFAULT '' COMMENT '接受邮件地址',
	title varchar(50) NOT NULL DEFAULT '' COMMENT '邮件标题',
	content mediumtext NOT NULL DEFAULT '' COMMENT '邮件内容',
	sendTime datetime NOT NULL DEFAULT '' COMMENT '发送时间',
	PRIMARY KEY (id),
	KEY (to),
	KEY (sendTime)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='Mail';