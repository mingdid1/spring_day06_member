<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
	<script src="<%=request.getContextPath() %>/resources/js/img_view.js"></script>
	
</head>
<body>
	<%@ include file="../default/header.jsp" %>
	
	<form action="modify" method="post" enctype="multipart/form-data">
		<input type="hidden" name="imgFileName" value="${content.imgFileName }">
		글번호 <input type="text" name="writeNo" value="${content.writeNo }" readonly><br>
		제목 	<input type="text" name="title" value="${content.title }"><br>
		내용 <textarea name="content">${content.content }</textarea><br>
		
		<img alt="이미지 없음" id="preview" width="100" height="100" src="download?name=${content.imgFileName }"><br>
		<input type="file" name="file" onchange="readURL(this)"><br>
		<hr>
		<input type="submit" value="수정">
		<input type="button" onclick="history.back()" value="이전">
	</form>
</body>
</html>