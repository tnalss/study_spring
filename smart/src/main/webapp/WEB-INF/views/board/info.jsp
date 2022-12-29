<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>방명록 안내</h3>
<table class="w-px1200">
<colgroup>
<col width='140px'/>
<col />
<col width='160px' />
<col width='160px' />
<col width='100px'/>
<col width='100px'/>

</colgroup>

<tr>
	<th>제목</th>
	<td colspan='5'>${vo.title }</td>
</tr>
<tr>
	<th>작성자</th>
	<td>${vo.name }</td>
	<th>작성일자</th>
	
	<td>${vo.name }</td>
	<th>조회수</th>
	<td>${vo.readcnt }</td>
</tr>
<tr><th>내용</th><td colspan='5'>${vo.content }</td></tr>
<tr><th>첨부파일</th><td colspan='5'>
<c:forEach items='${vo.fileList}' var='file'>
<div class='align'>
	<span>${file.filename} </span>
	<a class="download" data-file="${file.id}"><i class="font-black fa-solid fa-file-arrow-down"></i></a>
	<span class="preview"></span>
</div>
</c:forEach></td></tr>


</table>
<div class="btnSet">
<a class="btn-fill list">목록으로</a>

<!--  작성자가 로그인한 경우만 수정/ 삭제가능 -->

<c:if test='${loginInfo.userid eq vo.writer}'>

<a class="btn-fill modify">정보수정</a>
<a class="btn-fill remove">정보삭제</a>
</c:if>
</div>

<form method="post">
	<input type="hidden" name="id" value="${vo.id}"/>
	<input type="hidden" name="url"/>
	<input type="hidden" name="file"/>
	<input type="hidden" name="curPage" value="${page.curPage}"/>
	<input type="hidden" name="search" value="${page.search}"/>
	<input type="hidden" name="keyword" value="${page.keyword}"/>
	<input type="hidden" name="viewType" value="${page.viewType}"/>
	<input type="hidden" name="pageList" value="${page.pageList}"/>
</form>
<div id="popup-background"></div>
<div id="popup" class='center'></div>


<script>
<c:forEach items="${vo.fileList}" var="f" varStatus='state'>
if(isImage('${f.filename}')){
	$('.preview').eq(${state.index}).html( '<img src="${f.filepath}">' );
}

</c:forEach>

/* 클릭시 팝업 */
 
 $('.preview img').on('click',function(){
	 $('#popup,#popup-background').css('display','block');
	 $('#popup').append( $(this).clone() );
 });

 $('#popup-background').on('click',function(){
	 $('#popup').empty();
	 $('#popup,#popup-background').css('display','none');
 });
 

$('.download').on('click',function(){
	
	$('[name=url]').val($(location).attr('href'));
	$('[name=file]').val($(this).data('file'));
	$('form').attr('action','download.bo');
	$('form').submit();
	
});

$('.list').on('click',function(){
	$('form').attr('action','list.bo');
	$('form').submit();
});

$('.remove').on('click',function(){
	if( confirm('정말 삭제?')){
		$('form').attr('action','remove.bo').submit();
	}
});

$('.modify').on('click',function(){
	$('form').attr('action','modify.bo');
	$('form').submit();
});

</script>

</body>
</html>