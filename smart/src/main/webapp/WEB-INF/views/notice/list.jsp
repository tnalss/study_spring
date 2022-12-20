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
<div id='list-top' class='w-px1200'>
<ul>
	<!-- 관리자회원으로 로그인한 경우만 글쓰기 가능 -->
	<c:if test='${loginInfo.admin eq "Y"}'>	
	<li><a class='btn-fill' href='new.no'>글쓰기</a></li>
	</c:if>
</ul>
</div>


<table>

<tr>
	<th width='100px'>번호</th>
	<th width='200x'>제목</th>
	<th width='140px'>작성자</th>
	<th width='160px'>작성일</th>
	<th>조회수</th>
</tr>

<c:forEach var='i' items='${ list }'>


<tr>
	<td>${i.id }</td>
	
	
	<td>
	<a href="info.no?id=${i.id}">
	${i.title}</a></td>
	
	
	<td>${i.name}</td>
	<td>${i.writedate}</td>
	<td>${i.readcnt}</td>
</tr>

</c:forEach>

</table>

</body>
</html>