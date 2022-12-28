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

	<h3>공지사항</h3>
	<!-- 이거 처리 안하면 , 가 붙는 현상 배열의 형태로 저장이 되어버리는 .. -->
	<form action="list.no" method="post">
		<div id='list-top' class='w-px1200'>
			<ul>
				<li>
				<select class="w-px100" name="search" >
						<option value="all" ${page.search eq "all" ? " selected" : "" } >전체</option>
						<option value="title" ${page.search eq "title" ? " selected" : ""}>제목</option>
						<option value="content" ${page.search eq 'content' ? ' selected' : ''}>내용</option>
						<option value="tandc" ${page.search eq 'tandc' ? ' selected' : ''}>제목+내용</option>
						<option value="writer" ${page.search eq 'writer' ? ' selected': ''}>작성자</option>
				</select>
				</li>

				<li>
				<input type="text" class='w-px300' name='keyword' value='${page.keyword}' />
				</li>
				
				<li>
				<a class="btn-fill btn-search">검색</a>
				</li>
			</ul>



			<ul>
				<!-- 관리자회원으로 로그인한 경우만 글쓰기 가능 -->
				<c:if test='${loginInfo.admin eq "Y"}'>
					<li><a class='btn-fill' href='new.no'>글쓰기</a></li>
				</c:if>
			</ul>
		</div>
		<input type="hidden" name="curPage" value="1" />
	</form>


	<table>

		<tr>
			<th width='100px'>번호</th>
			<th width='300x'>제목</th>
			<th width='140px'>작성자</th>
			<th width='160px'>작성일</th>
			<th>조회수</th>
			<th>첨부파일</th>
		</tr>

		<c:forEach var='i' items='${ page.list }'>


			<tr>
				<td>${i.no }</td>


				<td class='text-left'><span
					style='margin-right: ${i.indent*10}px'></span> <c:if
						test='${i.indent gt 0}'>
						<i class="fa-brands fa-replyd font-b"></i>
					</c:if> <a href="info.no?id=${i.id}&curPage=${page.curPage}&search=${page.search}&keyword=${page.keyword}"> ${i.title}</a></td>


				<td>${i.name}</td>
				<td>${i.writedate}</td>
				<td>${i.readcnt}</td>
				<td><c:if test='${!empty i.filename}'>
						<i class="font-c fa-solid fa-paperclip"></i>
					</c:if></td>
			</tr>

		</c:forEach>

	</table>
<script>

$('.btn-search').click(function(){
	$('form').submit();
});
</script>

	<jsp:include page="/WEB-INF/views/include/page.jsp" />




</body>
</html>