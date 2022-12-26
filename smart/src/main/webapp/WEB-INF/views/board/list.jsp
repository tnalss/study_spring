<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>방명록 목록</h3>
<form method="post">
	
	<div class="w-px1200" id="list-top">
	
	<ul>
		<li>
		<select name="search" id="w-px1200">
		<option value="all">전체</option>
		<option value="title">제목</option>
		<option value="content">내용</option>
		</select>		</li>
		<li>
		<input type="text" name="keyword" class="w-px300" />
		</li>		
		<li>
		<a href="" class="btn-fill">검색</a>
		</li>
	</ul>
	
	

	
	<ul>
		<li>
	<select name="pageList" class="w-px100">
		<c:forEach var='i' begin='1' end="6">
			<option value="${i*10}" ${page.pageList eq i*10?' selected':'' }>${10*i}개씩</option>
		
		</c:forEach>
	</select>
	</li>
	
	
		<c:if test="${not empty loginInfo}">
		<li><a href="new.bo" class="btn-fill">글쓰기</a></li>
		</c:if>
	</ul>
	</div>
	<input type="hidden" name='curPage' value='1' />
</form>

<table class='w-px1200 tb-list'>
<colgroup>
	<col width="100px"/>
	<col />
	<col width="160px"/>
	<col width="160px"/>
</colgroup>
<tr>
	<th>번호</th>
	<th>제목</th>
	<th>작성자</th>
	<th>작성일자</th>
</tr>

<c:forEach var='vo' items='${page.list}'>
<tr>
	<td>${vo.no}</td>
	<td class="text-left">${vo.title }</td>
	<td>${vo.name }</td>
	<td>${vo.writedate}</td>

</tr>
</c:forEach>





</table>

<div class="btnSet">
	<jsp:include page="/WEB-INF/views/include/page.jsp" />
</div>
<script>
	$('[name=pageList]').on('change',function(){
		$('form').submit();
	});
</script>

</body>
</html>