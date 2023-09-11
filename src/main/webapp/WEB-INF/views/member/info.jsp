<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	info.jsp
	<%@ include file="../default/header.jsp" %>
	
	<h3>개인 정보</h3>
	id : ${mem.dto.id }<br>
	pw : ${mem.dto.pw }<br>
	addr1 : ${mem.addr1 }<br>
	addr2 : ${mem.addr2 }<br>
	addr3 : ${mem.addr3 }<br>
	
	<a href="${contextPath }/member/modify?id=${mem.dto.id}">수정</a>

</body>
</html>