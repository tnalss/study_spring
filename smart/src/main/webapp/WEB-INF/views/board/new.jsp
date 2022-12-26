<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>방명록 글쓰기</h3>
<form action="insert.bo" method="post">
	<table class='w-px1200'>
		<tr>
			<th class="w-px140">제목</th>
			<td><input type="text" name="title" class="full chk" title="제목" /></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea name="content" class='full chk' title="내용"></textarea></td>
		</tr>
	<tr>
		<th>첨부파일</th>
		<td></td>
	</tr>
	
	</table>
	<input type="hidden" name="writer" value="${loginInfo.userid}" />
</form>
	<div class="btnSet">
		<a class="btn-fill save">저장</a>
		<a class="btn-empty cancel">취소</a>
	</div>
	
<script>
	$('.save').on('click', function(){
		if(emptyCheck())
			$('form').submit();
	});
	
	$('.cancel').on('click',function(){
		history.go(-1);
	});

</script>
</body>
</html>