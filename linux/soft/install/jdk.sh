#!/bin/bash
#
# Author: txazo
# Description: Install JDK
# Directory: /usr/java/jdk1.8.0_65
# OK

function init() {
  pkgPath="/soft/pkgs"
  profile="/etc/profile"
}

function downloadJdk() {
  cd $pkgPath
  wget --no-cookie --no-check-certificate --header "Cookie: s_cc=true;oraclelicense=accept-securebackup-cookie;gpw_e24=http%3A%2F%2Fwww.oracle.com%2F" http://download.oracle.com/otn-pub/java/jdk/8u65-b17/jdk-8u65-linux-x64.rpm
}

function installJdk() {
  rpm -ivh jdk-8u65-linux-x64.rpm
  
  sed -i '/^export PATH/a export JAVA_HOME="/usr/java/jdk1.8.0_65"\nexport PATH="$PATH:$JAVA_HOME/bin:$JAVA_HOME/jre/bin"\nexport CLASSPATH=".:$JAVA_HOME/lib:$JAVA_HOME/jre/lib"' $profile
  
  source $profile
}

function main() {
  init
  downloadJdk
  installJdk
}

main