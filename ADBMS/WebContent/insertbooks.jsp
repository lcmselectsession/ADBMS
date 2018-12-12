<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>数据信息批量添加</title>
<style type="text/css">
input {
	border: none;
}

.submit {
	cursor: pointer;
}
</style>
</head>
<body>
	<table width="555" height="47" border="0px" align="center"
		cellpadding="0" cellspacing="1" bgcolor="#333333">
		<td height="30" colspan="5" bgcolor="#EFEFEF"><div
				style="float: left; position: relative; padding-top: 5px; padding-bottom: 5px;">数据信息批量添加图表</div>
			<div bgcolor="darkgray" style="float: right;">
				<a href="index.jsp"><button
						style="cursor: pointer; margin-top: 3px">返回</button></a>
			</div></td>
		<tr>
			<td width="145" align="center" bgcolor="#FFFFFF">图书名称</td>
			<td width="85" align="center" bgcolor="#FFFFFF">单价</td>
			<td width="38" align="center" bgcolor="#FFFFFF">数量</td>
			<td width="148" align="center" bgcolor="#FFFFFF">作者</td>
			<td width="40" align="center" bgcolor="#FFFFFF">操作</td>
		</tr>
		<form action="InsertServlet" method="get" class="form">
			<tr style="height: 25px">
				<td bgcolor="#FFFFFF"><input type="text" name="name"
					style="width: 145px;"></td>
				<td bgcolor="#FFFFFF"><input type="text" name="price"
					style="width: 85px;"></td>
				<td bgcolor="#FFFFFF"><input type="text" name="bookCount"
					style="width: 38"></td>
				<td bgcolor="#FFFFFF"><input type="text" name="author"
					style="width: 148"></td>
				<td bgcolor="#FFFFFF">&nbsp;&nbsp;&nbsp;<input type="checkbox"
					value="123" class="check"> <!-- <input type="submit" value="添加" class="submit" style="width: 38"> --></td>
			</tr>
		</form>
		<form action="InsertServlet" method="get" class="form">
			<tr style="height: 25px">
				<td bgcolor="#FFFFFF"><input type="text" name="name"
					style="width: 145px;"></td>
				<td bgcolor="#FFFFFF"><input type="text" name="price"
					style="width: 85px;"></td>
				<td bgcolor="#FFFFFF"><input type="text" name="bookCount"
					style="width: 38"></td>
				<td bgcolor="#FFFFFF"><input type="text" name="author"
					style="width: 148"></td>
				<td bgcolor="#FFFFFF">&nbsp;&nbsp;&nbsp;<input type="checkbox"
					value="123" class="check"> <!-- <input type="submit" value="添加" class="submit" style="width: 38"> --></td>
			</tr>
		</form>
		<form action="InsertServlet" method="get" class="form">
			<tr style="height: 25px">
				<td bgcolor="#FFFFFF"><input type="text" name="name"
					style="width: 145px;"></td>
				<td bgcolor="#FFFFFF"><input type="text" name="price"
					style="width: 85px;"></td>
				<td bgcolor="#FFFFFF"><input type="text" name="bookCount"
					style="width: 38"></td>
				<td bgcolor="#FFFFFF"><input type="text" name="author"
					style="width: 148"></td>
				<td bgcolor="#FFFFFF">&nbsp;&nbsp;&nbsp;<input type="checkbox"
					value="123" class="check"> <!-- <input type="submit" value="添加" class="submit" style="width: 38"> --></td>
			</tr>
		</form>
		<form action="InsertServlet" method="get" class="form">
			<tr style="height: 25px">
				<td bgcolor="#FFFFFF"><input type="text" name="name"
					style="width: 145px;"></td>
				<td bgcolor="#FFFFFF"><input type="text" name="price"
					style="width: 85px;"></td>
				<td bgcolor="#FFFFFF"><input type="text" name="bookCount"
					style="width: 38"></td>
				<td bgcolor="#FFFFFF"><input type="text" name="author"
					style="width: 148"></td>
				<td bgcolor="#FFFFFF">&nbsp;&nbsp;&nbsp;<input type="checkbox"
					value="123" class="check"> <!-- <input type="submit" value="添加" class="submit" style="width: 38"> -->
				</td>
			</tr>
		</form>
		<tr style="height: 20px" bgcolor="#B3B3B3">
			<td bgcolor="#B3B3B3"></td>
			<td bgcolor="#B3B3B3"></td>
			<td bgcolor="#B3B3B3"></td>
			<td bgcolor="#B3B3B3"></td>
			<td bgcolor="#B3B3B3"><button class="sub" style="width: 50px"
					onclick="bon();">添加</button></td>
		</tr>
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