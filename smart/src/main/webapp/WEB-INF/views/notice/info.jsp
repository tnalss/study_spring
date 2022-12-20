<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<table class="w-px1200">

<tr>
	<th>번호</th>
	<td>${vo.id}</td>
</tr>

<tr>
	<th>제목</th>
	<td>${vo.title}</td>
</tr>

<tr>
	<th>내용</th>
	<td>${vo.content}</td>
</tr>

<tr>
	<th>작성자</th>
	<td>${vo.name}</td>
</tr>

<tr>
	<th>작성일</th>
	<td>${vo.writedate}</td>
</tr>

<tr>
	<th>조회수</th>
	<td>${vo.readcnt}</td>
</tr>


</table>

<div class='btnSet'>

<a class='btn-fill' onclick='history.go(-1)' >공지글목록</a>

<c:if test="${loginInfo.userid eq vo.writer }">
<a class='btn-fill' href='modify.no?id=${vo.id}'>수정</a>
<a class='btn-fill btn-delete'>글 삭제</a>
</c:if>



</div>

<script>
$('.btn-delete').click(function(){
	if( confirm('삭제?') ){
		location = 'delete.no?id=${vo.id }';
	}	
});

</script>

</body>
</html>