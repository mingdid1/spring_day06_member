<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	list.jsp
	<%@include file="../default/header.jsp" %>
	<h3>리스트 페이지</h3>
	size : ${list.size() }
	
	<table border="1">
		<tr>
			<th>id</th> <th>pw</th> <th>addr</th>
		</tr>
		<c:forEach var="dto" items="${list }">
			<tr>
				<td>
					<a href="info?id=${dto.id}"> 
						${dto.id }
					</a>
				</td>
				<td>${dto.pw }</td>
				<td>${dto.addr }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>