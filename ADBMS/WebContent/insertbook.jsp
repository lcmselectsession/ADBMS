<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>数据信息添加</title>
<style type="text/css">

.submit {
	cursor: pointer;
}
</style>
</head>
<body>
	<table width="300" height="47" border="0px" align="center"
		cellpadding="0" cellspacing="1" >
		<td height="30" colspan="5" bgcolor="#EFEFEF"><div
				style="float: left; position: relative; padding-top: 5px; padding-bottom: 5px;margin-left: 4px;">数据信息添加图表</div>
			<div bgcolor="darkgray" style="float: right;">
				<a href="index.jsp"><button
						style="cursor: pointer; margin-top: 3px;float: right; margin-right: 4px">返回</button></a>
			</div></td>
				<form action="InsertServlet" method="get">
			<tr style="height: 25px">
				<td colspan="7">
					书名:<input
					type="text" name="name"
					style="width: 257px; margin: 3px 0;"><br> 
					价格:<input
					type="text"  name="price"
					style="width: 257px; margin: 3px 0;"><br> 
					数量:<input
					type="text" name="bookCount"  style="width: 257px; margin: 3px 0;"><br>
					作者:<input type="text" name="author" 
					style="width: 257px; margin: 3px 0;"><br> <input
					type="submit" value="添加" style="float: right; margin: 3px 3px;">
					<input type="reset" value="重置" style="float: right; margin: 3px 0;">
				</td>
			</tr>
		</form>
		
	</table>
	<script type="text/javascript">
		var sub = document.getElementsByClassName("sub");
		function bon() {
			var length = document.getElementsByClassName("form").length;
			for (var i = 0; i < length; i++) {
				if (document.getElementsByClassName("check")[i].checked) {
					document.getElementsByClassName("form")[i].submit();
				}
				//alert(document.getElementsByClassName("check")[i].checked);
			}
			;
			//alert("提交成功！");
		}
	</script>
</body>
</html>