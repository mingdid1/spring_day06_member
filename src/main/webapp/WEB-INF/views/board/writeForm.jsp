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
	
	<h3>글 작성</h3>
	<form action="writeSave" method="post" enctype="multipart/form-data">
		<b>작성자</b>
		<input type="text" name="id" value="${login}" readonly>
		<br>
		<b>제목</b>
		<input type="text" name="title"><br>
		<b>내용</b>
		<textarea name="content"></textarea>
		<br>
		<b>이미지</b>
		<input type="file" name="img_FileName" onchange="readURL(this)">
		<br>
		<img id="preview" width="100" height="100" alt="선택없음">
		<hr>
		<input type="submit" value="등록">
		<a href="boardAllList">목록 이동</a>
	</form>
</body>
</html>