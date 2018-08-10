# ********************< tar >********************
# 打包解包压缩解压缩

# 打包(tar包)
tar -cvf data.tar data

# 解包(tar包)
tar -xvf data.tar

# 查看包(tar包)
tar -tvf data.tar

# 压缩(tar.gz包)
tar -zcvf data.tar.gz data

# 解压(tar.gz包)
tar -zxvf data.tar.gz

# 查看压缩包(tar.gz包)
tar -ztvf data.tar.gz

# 解压部分文件
tar -zxvf data.tar.gz data/log

# 解压到指定目录下
tar -zxvf data.tar.gz -C /home/txazo/www

# 压缩时排除部分文件
tar --exclude=data/www -zcvf data.tar.gz data

# 保留原文件的文件权限和属性
tar -zcvpf data.tar.gz data