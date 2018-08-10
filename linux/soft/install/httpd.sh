#!/bin/bash
#
# Author: txazo
# Description: Install Apache Httpd
# Directory: /usr/local/httpd
# Install Dependency: yum install pcre pcre-devel
# Apache Start: /usr/local/httpd/bin/apachectl start
#               service httpd start
# Apache Stop:  /usr/local/httpd/bin/apachectl stop
#               service httpd stop
# Apache Service: cp /usr/local/httpd/bin/apachectl /etc/ec.d/init.d/httpd
#                 vi httpd
#                 # chkconfig: 345 85 30
#                 # description: Apache
#                 chkconfig --add httpd
#                 chkconfig httpd on

function init() {
  pkgPath="/soft/pkgs"
  aprHome="/usr/local/apr-httpd"
  aprUtilHome="/usr/local/apr-util-httpd"
  httpdHome="/usr/local/httpd"
  aprVersion="1.5.0"
  aprUtilVersion="1.5.3"
  httpdVersion="2.4.7"
}

function downloadHttpd() {
  cd $pkgPath
  wget http://apache.dataguru.cn/apr/apr-$aprVersion.tar.gz
  wget http://apache.dataguru.cn/apr/apr-util-$aprUtilVersion.tar.gz
  wget http://mirrors.cnnic.cn/apache/httpd/httpd-$httpdVersion.tar.gz
}

function installHttpd() {
  tar -zxvf apr-$aprVersion.tar.gz
  cd apr-$aprVersion
  ./configure --prefix=$aprHome
  make
  make install
  
  cd ..
  tar -zxvf apr-util-$aprUtilVersion.tar.gz
  cd apr-util-$aprUtilVersion
  ./configure --prefix=$aprUtilHome --with-apr=$aprHome
  make
  make install
  
  cd ..
  tar -zxvf httpd-$httpdVersion.tar.gz
  cd httpd-$httpdVersion
  ./configure --prefix=$httpdHome --with-apr=$aprHome --with-apr-util=$aprUtilHome --enable-rewirte --enable-modules=most --enable-modules-shared=most --enable-mpms-shared=all
  make
  make install
}

function main() {
  init
  downloadHttpd
  installHttpd
}

main