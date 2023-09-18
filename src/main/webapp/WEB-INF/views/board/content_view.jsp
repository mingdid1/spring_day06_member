<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="../default/header.jsp" %>
	<table border="1">
		<tr>
			<th>글 번호</th> <td>${dto.writeNo }</td>
			<th>작성자</th> <td>${dto.id }</td>
		</tr>
		<tr>
			<th>제목</th> <td>${dto.title }</td>
			<th>등록일자</th> <td>${dto.saveDate }</td>
		</tr>
		<tr>
			<th>내용</th> <td>${dto.content }</td>
			<td colspan="2">
				<img src="download?name=${dto.imgFileName }" width="100" height="100" alt="없음">
			</td>
		</tr>
		<tr>
			<td colspan="4">
				<c:if test="${dto.id == login }">
					<button type="button" onclick="">수정</button>
					<button type="button" onclick="">삭제</button>
				</c:if>
				<button type="button" onclick="">답글</button>
				<button type="button" onclick="location.href='boardAllList'">목록</button>
			</td>
		</tr>
	</table>
</body>
</html>