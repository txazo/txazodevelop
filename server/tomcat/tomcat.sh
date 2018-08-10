#!/bin/bash
#
# Author: txazo
# Description: Install Tomcat
# Directory: /usr/local/tomcat
# Tomcat Start: /usr/local/tomcat/bin/startup.sh
# Tomcat Stop: /usr/local/tomcat/bin/shutdown.sh

function init() {
  pkgPath="/soft/pkgs"
  tomcatHome="/usr/local/tomcat"
  tomcatVersion="8.0.30"
}

function downloadTomcat() {
  cd $pkgPath
  wget http://mirrors.hust.edu.cn/apache/tomcat/tomcat-8/v$tomcatVersion/bin/apache-tomcat-$tomcatVersion.tar.gz
}

function installTomcat() {
  tar -zxvf apache-tomcat-$tomcatVersion.tar.gz
  rm -rf $tomcatHome
  mv apache-tomcat-$tomcatVersion $tomcatHome
}

function main() {
  init
  downloadTomcat
  installTomcat
}

main