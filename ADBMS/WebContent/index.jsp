<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>应用JSTL显示数据库中的商品信息</title>
</head>
<body>
<!-- 判断session中是否已经存在之前访问的页，如过有从当前页开始，如果没有从第一页开始 -->
	<c:choose>
		
		<c:when test="${sessionScope.start>1 }">
			<c:redirect url="BookServlet">
				<c:param name="action" value="query" />
				<c:param name="page" value="${sessionScope.start }"></c:param>
			</c:redirect>			
		</c:when>
		<c:when test="${srequestScope.page!=null }">
			<c:redirect url="BookServlet">
				<c:param name="action" value="query" />
				<c:param name="page" value="${srequestScope.page }"></c:param>
			</c:redirect>			
		</c:when>
		<c:otherwise>
				<c:redirect url="BookServlet">
					<c:param name="action" value="query" />
					<c:param name="page" value="1"></c:param>
				</c:redirect>
			</c:otherwise>
	</c:choose>
</body>
</html>
