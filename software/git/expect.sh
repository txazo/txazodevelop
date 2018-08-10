#!/usr/bin/expect

spawn ./svn-to-git-init.sh "test"
expect "Password: "
send "txazo\n"
expect eof
exit