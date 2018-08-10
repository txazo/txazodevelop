#!/bin/bash
#
# Author: txazo
# Description: Install Python
# Directory: /usr/local/python3.3.3

function init {
  pkgPath="/soft/pkgs"
  pythonHome="/usr/local/python3.3.3"
}

function downloadPython() {
  cd $pkgPath
  wget http://www.python.org/ftp/python/3.3.3/Python-3.3.3.tgz
}

function installPython() {
  tar -zxvf Python-3.3.3.tgz
  cd Python-3.3.3

  ./configure --prefix=$pythonHome
  make
  make install
  
  ln -s $pythonHome/bin/python3 /usr/bin/python
}

function main() {
  init
  downloadPython
  installPython
}

main