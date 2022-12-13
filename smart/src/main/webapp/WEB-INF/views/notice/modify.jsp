<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="update.no" method="post">
	<input type="hidden" name="id" value="${vo.id}"/>
		<table>
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" value="${vo.title} "/></td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
				<textarea name="content">${vo.content}</textarea>
				</td>
			</tr>
		</table>
	</form>
	<input type="button" onclick="$('form').submit()" value="수정" />


</body>
</html>