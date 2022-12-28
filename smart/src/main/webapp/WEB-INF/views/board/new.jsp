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
<!-- 첨부파일을 하려면 폼태그에 반드시!@#!@#!@# 멀티파트 폼데이터가 들어가야한다. -->
<form action="insert.bo" method="post" enctype='multipart/form-data'>
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
		<!-- 첨부파일 여러개 가능하게 하기 우선 클래스로 변경 common.js ㄱㄱ -->
		<td class='text-left'>
			<div class='align'><label >
			<input type="file" name="file" class="attach-file" />
			<a >	<i class="fa-solid fa-file-arrow-up"></i>	</a>
			</label>
			<span class='file-name'>${vo.filename }</span>
			<span class="preview"></span>
			<a class="delete-file"> <i class="fa-solid fa-trash-can"></i> </a></div>
			</td>
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