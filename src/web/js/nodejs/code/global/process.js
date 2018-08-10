// process.js

console.log(process.argv);

// 恢复标准输入流
process.stdin.resume();

// 标准输入流事件响应函数
process.stdin.on('data', function(data) {
    // 输出到标准输出
    process.stdout.write('read from console: ' + data.toString());
});

// process.nextTick(callback);