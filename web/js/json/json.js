var text = '{"name": "root"}';

//JSON字符串转为JSON对象
var json = eval('(' + text + ')');
console.log(json.name);
json = text.parseJSON(); // json.js
console.log(json.name);
json = JSON.parse(text);
console.log(json.name);

// JSON对象转为JSON字符串
text = JSON.stringify(json);
console.log(text);
text = json.toJSONString(); // json.js
console.log(text);