# ********************< chmod >********************
# 改变文件权限

chmod 777 file
chmod -R 777 file  # 递归改变文件权限

chmod [u | g | o | a] [+ | - | =] [r | w | x] file

chmod a-x,g-r file
chmod a+x,g+r file
chmod a=x,g=r file