#!/bin/bash
#
# Author: txazo
# Description: Install MongoDB
# Directory: /usr/local/mongodb
# MongoDB Start: /usr/local/mongodb/bin/mongod --dbpath=/data/mongodb/db --logpath=/data/mongodb/logs/mongodb.log --logappend --fork
# MongoDB Client: /usr/local/mongodb/bin/mongo
# OK

function init {
  pkgPath="/soft/pkgs"
  mongodbHome="/usr/local/mongodb"
}

function downloadMongodb() {
  cd $pkgPath
  wget http://downloads.10gen.com/linux/mongodb-linux-x86_64-enterprise-rhel62-2.6.4.tgz
}

function installMongodb() {
  tar -zxvf mongodb-linux-x86_64-enterprise-rhel62-2.6.4.tgz
  mv mongodb-linux-x86_64-enterprise-rhel62-2.6.4 $mongodbHome
  cd $mongodbHome
  
  rm -rf /data/mongodb
  mkdir -p /data/mongodb/db
  mkdir -p /data/mongodb/logs
}

function main() {
  init
  downloadMongodb
  installMongodb
}

main