<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" import="java.util.List"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>查询商品信息</title>
<style type="text/css">
body {
	font-size: 12px;
}

.pages {
	margin: 0 auto;
	width: 330px;
	height: 30px;
}

.a {
	float: left;
	position: relative;
	padding: 12px 10px 5px 10px;
}

.pageselection {
	float: left;
	position: relative;
	padding: 12px 10px 5px 10px;
}
</style>
</head>
<body>
	<%
		List pagelist = (List) request.getAttribute("pagelists");
	%>
	<table width="600" height="47" border="0px" align="center"
		cellpadding="0" cellspacing="1" bgcolor="#333333">
		<tr>
			<td height="30" colspan="7" bgcolor="#EFEFEF"><div
				style="float: left; position: relative; padding-top: 5px; padding-bottom: 5px;margin-left: 4px;">数据信息添加图表</div>
			<div bgcolor="darkgray" style="float: right;">
				<a href="index.jsp"><button
						style="cursor: pointer; margin-top: 3px;float: right; margin-right: 4px;">返回</button></a>
			</div></td>
		</tr>
		<tr>
			<td width="36" height="27" align="center" bgcolor="#FFFFFF">编号</td>
			<td width="145" align="center" bgcolor="#FFFFFF">图书名称</td>
			<td width="85" align="center" bgcolor="#FFFFFF">单价</td>
			<td width="38" align="center" bgcolor="#FFFFFF">数量</td>
			<td width="148" align="center" bgcolor="#FFFFFF">作者</td>
			<td width="50" align="center" bgcolor="#FFFFFF">操作</td>
		</tr>
		<c:forEach var="book" items="${requestScope.selectbook}">
			<tr>
				<td height="27" bgcolor="#FFFFFF">&nbsp; <c:out
						value="${book.id}" /></td>
				<td bgcolor="#FFFFFF">&nbsp; <c:out value="${book.name}" /></td>
				<td bgcolor="#FFFFFF">&nbsp; <c:out value="${book.price}" />（元）
				</td>
				<td bgcolor="#FFFFFF">&nbsp; <c:out value="${book.bookCount}" /></td>
				<td bgcolor="#FFFFFF">&nbsp; <c:out value="${book.author}" /></td>
				<td bgcolor="#FFFFFF">&nbsp;
					<form action="SelectBookByIDServlet" method="get"
						style="float: left; margin-top: 0;" class="xg">
						<input type="hidden" name="id" value="${book.id }">
						<%--       		<input type="hidden" name="page" value="<%=pagelist.get(0) %>"> --%>
						<!-- <input type="submit" value="修改"
							style="background-color: #C9C9C9s; cursor: pointer; margin-left: 2px;"> -->
							<img alt="修改" src="images/modify.gif" style=" cursor: pointer; margin-left: 2px;" onclick="xg();">
					</form>
					<form action="DeleteServlet" method="get"
						style="float: left; margin-top: 0;" class="sc">
						<input type="hidden" name="id" value="${book.id }"> <input
							type="hidden" name="page" value="<%=pagelist.get(0)%>"> <!-- <input
							type="submit" value="刪除"
							style="background-color: #C9C9C9s; cursor: pointer; margin-left: 2px;"> -->
							<img  alt="删除" src="images/del.gif" style=" cursor: pointer; margin-left: 2px;" onclick="sc();">
					</form>
				</td>
			</tr>
		</c:forEach>
	
	<!-- 用operation属性值Frist，sub，add，max来代表首页，下一页，上一页，尾页 -->
	</table>
	<div class="pages">
		<a class="a" href="SelectByBookNameAndAuthor?action=query&operation=first">首页</a> <a
			class="a" href="SelectByBookNameAndAuthor?action=query&operation=sub">上一页</a>
		
		<!-- 设置页面选择条，如：1 2 3 4 -->
		<%
			for (int i = 0; i < pagelist.size(); i++) {
		%>
		<a class="a" href="SelectByBookNameAndAuthor?action=query&page=<%=pagelist.get(i)%>"><%=pagelist.get(i)%></a>
		<%
			}
		%>
		<a class="a" href="SelectByBookNameAndAuthor?action=query&operation=add">下一页</a> <a
			class="a" href="SelectByBookNameAndAuthor?action=query&operation=max">尾页</a>
	</div>
	<script type="text/javascript">
		function sc() {
			document.getElementsByClassName("sc")[0].submit();
		};
		function xg() {
			document.getElementsByClassName("xg")[0].submit();
		};
	</script>
</body>
</html>
