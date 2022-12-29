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
<form method="post" action="list.bo">
	
	<div class="w-px1200" id="list-top">
	
	<ul>
		<li>
		<select name="search" id="w-px1200">
		<option value="all" >전체</option>
		<option value="title">제목
		
		</option>
		<option value="content">내용</option>
		<option value="writer">작성자</option>
		</select>		</li>
		<li>
		<input type="text" name="keyword" class="w-px300"  value="${page.keyword }"/>
		</li>		
		<li>
		<a class="btn-fill btn-search">검색</a>
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
	<li><select name="viewType" class="w-px100">
	<option value="list">리스트형태</option>
	<option value="grid">그리드형태</option>

	</select></li>
	
	
		<c:if test="${not empty loginInfo}">
		<li><a href="new.bo" class="btn-fill">글쓰기</a></li>
		</c:if>
	</ul>
	</div>
	<input type="hidden" name='curPage' value='1' />
	<input type="hidden" name='id' />
</form>


<!-- 리스트인경우 -->
<c:if test='${page.viewType eq "list" }'>


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

<c:if test="${empty page.list }">
<tr><td colspan='5'>검색결과가 없습니다.</td></tr>
</c:if>


<c:forEach var='vo' items='${page.list}'>
<tr>
	
	<td>${vo.no}</td>
	<!-- 첨부파일 표시를 위한 쿼리문 ㅅ후정 -->
	
	<td class="text-left"><a onclick="fn_submit(${vo.id})">${vo.title }</a>
	
	<c:if test='${vo.fileCount gt 0 }'>
						<i class="font-c fa-solid fa-paperclip"></i>
					</c:if>
	</td>
	<td>${vo.name }</td>
	<td>${vo.writedate}</td>

</tr>
</c:forEach>

</table>
</c:if>

<!-- 그리드 인경우 -->
<c:if test='${page.viewType eq "grid" }'>
<ul class='grid w-px1200'>
	<c:forEach var='vo' items='${page.list }'>
	<li>
	<div><a class="title" data-id='${vo.id }'>${vo.title}</a></div>
	<div>${vo.name}</div>
	<div>${vo.writedate}	<c:if test='${vo.fileCount gt 0 }'>
							<span style="float: right;"><i class="font-c fa-solid fa-paperclip"></i></span>
						</c:if></div>
	</li>
	</c:forEach>
</ul>
</c:if>

<div class="btnSet">
	<jsp:include page="/WEB-INF/views/include/page.jsp" />
</div>
<script>

/* 내용보기위한 함수 폼태그의 액션속성을 바꿔주는 처리를 한다. */
function fn_submit(id){
	
	$('[name=curPage]').val(${page.curPage});
	$('[name=id]').val(id);
	$('form').attr('action','info.bo');
	$('form').submit();
}

/* 그리드에서는 다른방법으로 처리해보기 */
 /* 반복문이 있기때문에 찾아갈수가없음 각각의 값들을 담아놓아야함. */
 /* data속성을 사용해보자. */
 $('.title').on('click',function(){
	 fn_submit($(this).data('id'));
 });

	$('[name=viewType],[name=pageList]').on('change',function(){
		$('form').attr('action','list.bo');
		$('form').submit();
	});//콤마에 주의해야한다.

	
	$('.btn-search').on('click',function(){
		$('form').attr('action','list.bo');
		$('form').submit();
	})
	
	$(function(){
	$('[name=search]').val('${page.search}');
	$('[name=viewType]').val('${page.viewType}');
});

</script>

</body>
</html>