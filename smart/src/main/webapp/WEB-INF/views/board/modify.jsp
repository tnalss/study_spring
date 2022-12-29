<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>방명록 글수정</h3>
<form action="update.bo" method="post" enctype='multipart/form-data'>

	<table class='w-px1200'>
		<tr>
			<th class="w-px140">제목</th>
			<td><input type="text" name="title" value="${vo.title}" class="full chk" title="제목" /></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea name="content" class='full chk' title="내용">${vo.content} </textarea></td>
		</tr>
	<tr>
		<th>첨부파일</th>
		<!-- 첨부파일 여러개 가능하게 하기 우선 클래스로 변경 common.js ㄱㄱ -->
		<td class='text-left'>
		<c:forEach var='f' items='${vo.fileList}'>
			<div class='align' data-file='${f.id}'><label >
			<input type="file" name="file" class="attach-file" />
			<a >	<i class="fa-solid fa-file-arrow-up"></i>	</a>
			</label>
			<span class='file-name'>${f.filename } </span>
			<span class="preview"></span>
			<a class="delete-file" style="display:inline;"> <i class="fa-solid fa-trash-can"></i> </a></div>
			</c:forEach>
			<div class='align'><label >
			<input type="file" name="file" class="attach-file" />
			<a >	<i class="fa-solid fa-file-arrow-up"></i>	</a>
			</label>
			<span class='file-name'></span>
			<span class="preview"></span>
			<a class="delete-file" style="display:inline;"> <i class="fa-solid fa-trash-can"></i> </a></div>
			
			</td>
	</tr>
	
	</table>
	
	<!-- 페이지정보 -->
	<input type="hidden" name="curPage" value="${page.curPage}"/>
	<input type="hidden" name="search" value="${page.search}"/>
	<input type="hidden" name="keyword" value="${page.keyword}"/>
	<input type="hidden" name="viewType" value="${page.viewType}"/>
	<input type="hidden" name="pageList" value="${page.pageList}"/>
	<input type="hidden" name="id" value="${vo.id}"/>
	<input type="hidden" name="removed" />
	
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