#!/bin/bash
#
# Author: txazo
# Description: Install Nginx
# Directory: /usr/java/nginx
# Install Dependency: yum install pcre pcre-devel zlib zlib-devel openssl openssl-devel
# Start: cd /usr/local/nginx/sbin ./nginx
#        /usr/local/nginx/sbin/nginx -c /usr/local/nginx/conf/nginx.conf
# OK

function init {
  pkgPath="/soft/pkgs"
  nginxHome="/usr/local/nginx"
  nginxVersion="1.6.2"
}

function downloadNginx() {
  cd $pkgPath
  wget http://nginx.org/download/nginx-$nginxVersion.tar.gz
}

function installNginx() {
  tar -zxvf nginx-$nginxVersion.tar.gz
  cd nginx-$nginxVersion

  ./configure --prefix=$nginxHome
  make
  make install
}

function main() {
  init
  downloadNginx
  installNginx
}

main