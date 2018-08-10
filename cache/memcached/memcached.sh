#!/bin/bash
# 
# Author: txazo
# Description: Install Memcached
# Directory: /usr/local/memcached
# 
# Memcached Start: /usr/local/memcached/bin/memcached -d -m 64 -u root -l 112.124.6.220 -p 9998 -c 128 -P /tmp/memcached.pid
# Memcached Stop: kill `cat /tmp/memcached.pid`
# Memcached Process: ps -ef | grep memcached
#                    ps aux | grep memcached
# 
# /usr/local/memcached/bin/memcached -vv

function init() {
  pkgPath="/soft/pkgs"
  libeventHome="/usr/local/libevent"
  memcachedHome="/usr/local/memcached"
  libeventVersion="2.0.22"
  memcachedVersion="1.4.25"
}

function downloadLibevent() {
  cd $pkgPath
  wget http://downloads.sourceforge.net/project/levent/release-2.0.22-stable/libevent-2.0.22-stable.tar.gz?r=&ts=1452660295&use_mirror=ncu
}

function installLibevent() {
  tar -zxvf libevent-$libeventVersion-stable.tar.gz
  cd libevent-$libeventVersion-stable

  ./configure --prefix=$libeventHome
  make
  make install
}

function downloadMemcached() {
  cd $pkgPath
  wget http://www.memcached.org/files/memcached-$memcachedVersion.tar.gz
}

function installMemcached() {
  tar -zxvf memcached-$memcachedVersion.tar.gz
  cd memcached-$memcachedVersion

  ./configure --prefix=$memcachedHome --with-libevent=$libeventHome
  make
  make install
}

function main() {
  init
  downloadLibevent
  installLibevent
  downloadMemcached
  installMemcached
}

main