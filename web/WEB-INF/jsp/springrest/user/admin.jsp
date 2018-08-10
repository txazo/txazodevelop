<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html lang="zh">
<head>
<meta charset="utf-8" />
<title>Spring MVC Restful</title>
<script type="text/javascript" src="/js/jquery/jquery/jquery-1.11.1.min.js"></script>
</head>
<body>
	<h3>Spring MVC Restful</h3>
	<input type="text" id="id1" /><input type="text" id="user1" /><input type="button" value="添加" onclick="restPost(); return false;" /><br /><br />
	<input type="text" id="id2" /><input type="text" id="user2" /><input type="button" value="更新" onclick="restPut(); return false;" /><br /><br />
	<input type="text" id="id3" /><input type="button" value="删除" onclick="restDelete(); return false;" />
	<script type="text/javascript">
		function restPost() {
			var id = $('#id1').val();
			var user = $('#user1').val();
			if (id == '' || user== '') {
				alert('请输入');
				return;
			}
			$.ajax({
				url: '/springrest/user/' + id,
				type: 'post',
				data: {
					'user': user
				},
				dataType: 'json',
				success: function(data) {
					if (!!data && data.status == 1) {
						alert('添加成功');
					} else {
						alert('添加失败');
					}
				}
			});
		}
		
		function restPut() {
			var id = $('#id2').val();
			var user = $('#user2').val();
			if (id == '' || user== '') {
				alert('请输入');
				return;
			}
			$.ajax({
				url: '/springrest/user/' + id,
				type: 'put',
				data: {
					'user': user
				},
				dataType: 'json',
				success: function(data) {
					if (!!data && data.status == 1) {
						alert('更新成功');
					} else {
						alert('更新失败');
					}
				}
			});
		}
		
		function restDelete() {
			var id = $('#id3').val();
			if (id == '') {
				alert('请输入');
				return;
			}
			$.ajax({
				url: '/springrest/user/' + id,
				type: 'delete',
				dataType: 'json',
				success: function(data) {
					if (!!data && data.status == 1) {
						alert('删除成功');
					} else {
						alert('删除失败');
					}
				}
			});
		}
	</script>
</body>
</html>