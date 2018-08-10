# ********************< paste >********************
# �ϲ��ļ�����
# pasteĬ�����Ʊ���Ϊ�ָ���

cat data.tmp
# 1
# 2
# 3

# �ļ�������ʾ��һ��
paste -s data.tmp
# 1       2       3

# ָ���ָ���
paste -s -d ',' data.tmp
# 1,2,3

cat 1
# 11 1
# 12 2

cat 2
# 21 1
# 22 2

# ���кϲ�
paste -d ' ' 1 2
# 11 1 21 1
# 12 2 22 2

# ���кϲ�
paste -s -d '\n' 1 2
# 11 1
# 12 2
# 21 1
# 22 2

# ÿ���ļ�������ʾ��һ��
paste -s -d ' ' 1 2
# 11 1 12 2
# 21 1 22 2

# ָ��һ����ʾ����
seq 1 10 | paste -d ' ' - - - -
# 1 2 3 4
# 5 6 7 8
# 9 10 