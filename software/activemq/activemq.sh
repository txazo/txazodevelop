#!/bin/bash
# 
# Author: txazo
# Description: Install ActiveMQ
# Directory: /usr/local/activemq
# 
# ActiveMQ Start: activemq
#                 activemq > /tmp/smlog  2>&1 &
#                 nohup bin/activemq > /tmp/smlog 2>&1 &
# ActiveMQ Process: ps -ef | grep activemq
# ActiveMQ Web Console: http://localhost:8161/admin

#chmod 600 /etc/default/activemq
#bin/activemq setup /etc/default/activemq

function init() {
  pkgPath="/soft/pkgs"
  activemqHome="/usr/local/activemq"
  activemqVersion="5.10.0"
}

function downloadActiveMQ() {
  cd $pkgPath
  wget http://apache.dataguru.cn/activemq/$activemqVersion/apache-activemq-$activemqVersion-bin.tar.gz
}

function installActiveMQ() {
  tar -zxvf apache-activemq-$activemqVersion-bin.tar.gz
  mv apache-activemq-$activemqVersion $activemqHome
}

function main() {
  init
  downloadActiveMQ
  installActiveMQ
}

main