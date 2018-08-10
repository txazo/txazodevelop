CREATE DATABASE IF NOT EXISTS db;

CREATE TABLE IF NOT EXISTS db.table (
    id int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
    name varchar(20) NOT NULL DEFAULT '' COMMENT 'name',
    createTime datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT 'createTime',
    PRIMARY KEY (id),
    UNIQUE (name),
    KEY (createTime)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='table';