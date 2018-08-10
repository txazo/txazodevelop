#!/bin/bash
# 
# Author: txazo
# Description: Install Redis
# Directory: /usr/local/redis
# Redis Start: /usr/local/redis/redis-server /usr/local/redis/etc/redis.conf
#              service redis start
# Redis Stop: /usr/local/redis/redis-cli -h 112.124.6.220 -p 8998 -a txazo shutdown
#             service redis stop
# Redis Stats: ps aux | grep redis
#              /usr/local/redis/redis-cli -h 112.124.6.220 -p 8998 -a txazo ping
# Redis Client: /usr/local/bin/redis-cli -p 8998 -a txazo
# Redsi Connection: telnet 112.124.6.220 8998
# 
# Redis Configuration: /usr/local/redis/etc/redis.conf
#     daemonize yes
#     pidfile /var/run/redis.pid
#     port 8998
#     bind 112.124.6.220
#     loglevel verbose
#     logfile /var/log/redis.log
#     dir /var/redis
#     requirepass txazo
# 
# cp /usr/local/redis/utils/redis_init_script /etc/init.d/redis
# vi /etc/init.d/redis
# # chkconfig: 2345 55 25
# # description: Redis
# chkconfig --add redis
# chkconfig redis on

function init {
  pkgPath="/soft/pkgs"
  redisHome="/usr/local/redis"
  redisConfig="/usr/local/redis/etc"
  redisVersion="2.8.9"
  
  if [ -d $redisHome ]; then
    rm -rf $redisHome
  fi
  
  mkdir -p $redisConfig
}

function downloadRedis() {
  cd $pkgPath
  wget http://download.redis.io/releases/redis-$redisVersion.tar.gz
}

function installRedis() {
  tar -zxvf redis-$redisVersion.tar.gz
  cd redis-$redisVersion
  make
  
  cp redis.conf $redisConfig
  cd src
  cp redis-server redis-cli redis-benchmark $redisHome
}

function main() {
  init
  downloadRedis
  installRedis
}

main