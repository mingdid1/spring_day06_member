<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<c:set var="contextPath" value="${pageContext.request.contextPath }"/>

	<!-- 우편번호 찾기 -->
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script src="${contextPath }/resources/js/daumPost.js"></script>

</head>
<body>
	header.jsp

	contextPath : ${contextPath }
	
	<h1>CARE LAB</h1>
	<a href="${contextPath }/index">HOME</a>
	
	<c:if test="${login == null }">
		<a href="${contextPath }/member/login">LOGIN</a>
	</c:if>
	<c:if test="${login != null }">
		<a href="${contextPath }/member/logout">LOGOUT</a>
	</c:if>
	<a href="${contextPath }/member/list">MEMBERS</a>
	
	<hr>
</body>
</html>