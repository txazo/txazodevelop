# ********************< ping >********************
# 测试网络连通性

# 测试本机和目标主机的连通性
ping mail.txazo.com

# 控制ping的次数
ping -c 3 mail.txazo.com
# PING mail.txazo.com (192.168.100.1) 56(84) bytes of data.
# 64 bytes from mail.txazo.com (192.168.100.1): icmp_seq=1 ttl=64 time=0.018 ms
# 64 bytes from mail.txazo.com (192.168.100.1): icmp_seq=2 ttl=64 time=0.034 ms
# 64 bytes from mail.txazo.com (192.168.100.1): icmp_seq=3 ttl=64 time=0.038 ms
# 
# --- mail.txazo.com ping statistics ---
# 3 packets transmitted, 3 received, 0% packet loss, time 1999ms
# rtt min/avg/max/mdev = 0.018/0.030/0.038/0.008 ms

# 等待响应对方主机的秒数
ping -c 1 -W 1 mail.txazo.com