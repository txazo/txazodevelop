// AJAX跨域问题解决
$.getJSON('http://doc.txazo.com/txazo.do?callback=?', {'param': param}, function(data) {
});

// 后台
String json = JSON.toJSONString(map);
response.getWriter().write(callback + "(" + json + ");");