# ********************< echo >********************
# 输出文本到标准输出

echo hello 'hello' "hello"

name=txazo
echo $name ${name} "$name" "${name}"

# 输出后不换行
echo -n 'hello'

# 支持处理转义字符
echo -e "1\t2\n3\t4"
# 1       2
# 3       4

# 不支持处理转义字符
echo -E "1\t2\n3\t4"
# 1\t2\n3\t4