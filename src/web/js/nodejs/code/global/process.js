// process.js

console.log(process.argv);

// �ָ���׼������
process.stdin.resume();

// ��׼�������¼���Ӧ����
process.stdin.on('data', function(data) {
    // �������׼���
    process.stdout.write('read from console: ' + data.toString());
});

// process.nextTick(callback);