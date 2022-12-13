<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>공지사항</h3>

<table>

<tr>
	<th>번호</th>
	<th>제목</th>
	<th>작성자</th>
	<th>작성일</th>
	<th>조회수</th>
</tr>

<c:forEach var='i' items='${ list }'>


<tr>
	<td>${i.id }</td>
	
	
	<td>
	<a href="info.no?id=${i.id}">
	${i.title}</a></td>
	
	
	<td>${i.writer}</td>
	<td>${i.writedate}</td>
	<td>${i.readcnt}</td>
</tr>

</c:forEach>

</table>

<input type="button" value="글쓰기" onclick="location='write.no'" />

</body>
</html>