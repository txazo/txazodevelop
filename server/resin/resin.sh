#!/bin/bash
#
# Author: txazo
# Description: Install Resin
# Directory: /usr/local/resin

function init {
  pkgPath="/soft/pkgs"
  resinHome="/usr/local/resin"
  resinVersion="4.0.40"
}

function downloadResin() {
  cd $pkgPath
  wget http://www.caucho.com/download/resin-$resinVersion.tar.gz
}

function installResin() {
  tar -zxvf resin-$resinVersion.tar.gz
  cd resin-$resinVersion

  rm -rf $resinHome
  ./configure --prefix=$resinHome
  make
  make install

  cd ..
  rm -rf resin-$resinVersion.tar.gz resin-$resinVersion
}

function main() {
  init
  downloadResin
  installResin
}

main