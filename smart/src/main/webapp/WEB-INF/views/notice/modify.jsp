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

	<h3>공지글 수정</h3>
	<form method='post' action='update.no'>
	<input type="hidden" name="id" value="${vo.id }" />
		<table class='w-px1200'>
			<tr>
				<th class='w-px140'>제목</th>
				<td><input type='text' name='title' class='full'
					value='${vo.title}'></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea name='content' class='full'  style='height:400px'>${vo.content}</textarea></td>
			</tr>
		</table>
	</form>
	<input type="button" onclick="$('form').submit()" value="수정" />


</body>
</html>