#!/bin/bash
#
# Author: txazo
# Description: Install Jetty
# Directory: /usr/local/jetty

function init() {
  pkgPath="/soft/pkgs"
  jettyHome="/usr/local/jetty"
}

function downloadJetty() {
  cd $pkgPath
  wget http://mirrors.ustc.edu.cn/eclipse/jetty/stable-9/dist/jetty-distribution-9.1.3.v20140225.tar.gz
}

function installJetty() {
  if [ -d "$jettyHome" ]; then
    rm -rf "$jettyHome"
  fi
  tar -zxvf jetty-distribution-9.1.3.v20140225.tar.gz
  mv jetty-distribution-9.1.3.v20140225 $jettyHome
}

function main() {
  init
  downloadJetty
  installJetty
}

main