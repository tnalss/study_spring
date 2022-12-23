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
<h3>공지글 답글 등록</h3>
	<form action="reply_insert.no" method="post" enctype='multipart/form-data'>
	<input type="hidden" name='writer' value='${loginInfo.userid}'/>
	<input type="hidden" name='root' value='${vo.root}'/>
	<input type="hidden" name='step' value='${vo.step}'/>
	<input type="hidden" name='indent' value='${vo.indent}'/>
		<table class='w-px1200'>
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" class='full chk' title="제목" /></td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
				<textarea name="content"  class='full chk' title="내용" ></textarea>
				</td>
			</tr>
			<tr>
			<th>첨부파일</th>
			<td class='text-left'>
			<div class='align'><label >
			<input type="file" name="file" id="attach-file" />
			<a >	<i class="fa-solid fa-file-arrow-up"></i>	</a>
			</label>
			<span id='file-name'>${vo.filename }</span>
			<span id="preview"></span>
			<a id="delete-file"> <i class="fa-solid fa-trash-can"></i> </a></div>
			</td>
			</tr>
		</table>
	</form>
	<div class="btnSet">
<a class="btn-fill save">저장</a>
<a href="list.no" class="btn-empty">취소</a>	
	</div>

<script>

$('.save').click(function(){
	
	if ( emptyCheck() ) {
	
		$('form').submit();
	}
})

</script>
</body>
</html>