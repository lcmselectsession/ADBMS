<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" import="com.wgh.model.BookForm" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改数据</title>
</head>
<body>
	<table width="300" height="47" border="0px" align="center"
		cellpadding="0" cellspacing="1" >
		<tr>
			<td height="30" colspan="1" bgcolor="#EFEFEF"><div
					style="float: left; position: relative; padding-top: 5px; padding-bottom: 5px;">图书信息修改列表</div>
				<div bgcolor="darkgray" style="float: right;">
					<a href="index.jsp"><button
							style="cursor: pointer; margin-top: 3px">返回</button></a>
				</div></td>
		</tr>

		<c:forEach var="book" items="${sessionScope.book}">	
		<form action="UpdateServlet" method="get">
			<tr style="height: 25px">
<%-- 			<%=((BookForm)request.getAttribute("book")).getId()%> --%>
				<td colspan="7"><input type="hidden" name="id" value="${book.id }"> 
					书名:<input
					type="text" value="${book.name }" name="name"
					style="width: 257px; margin: 3px 0;"><br> 
					价格:<input
					type="text" value="${book.price}" name="price"
					style="width: 257px; margin: 3px 0;"><br> 
					数量:<input
					type="text" name="bookCount" value="${book.bookCount }" style="width: 257px; margin: 3px 0;"><br>
					作者:<input type="text" name="author" value="${book.author }"
					style="width: 257px; margin: 3px 0;"><br> <input
					type="submit" value="修改" style="float: right; margin: 3px 3px;">
					<input type="reset" value="重置" style="float: right; margin: 3px 0;">
				</td>
			</tr>
		</form>
		</c:forEach>
	</table>
</body>
</html>