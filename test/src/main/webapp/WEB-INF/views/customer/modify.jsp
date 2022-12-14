<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html lang="ko">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<c:choose>
	<c:when test="${category eq 'cu'}"><c:set var='title' value='고객관리'/></c:when>
	<c:when test="${category eq 'hr'}"><c:set var='title' value='사원관리'/></c:when>
	<c:when test="${category eq 'no'}"><c:set var='title' value='공지사항'/></c:when>
	<c:when test="${category eq 'bo'}"><c:set var='title' value='방명록'/></c:when>
	<c:when test="${category eq 'da'}"><c:set var='title' value='공공데이터'/></c:when>
	<c:when test="${category eq 'vi'}"><c:set var='title' value='시각화'/></c:when>
	<c:when test="${category eq 'join'}"><c:set var='title' value='회원가입'/></c:when>
</c:choose>
<title>스프링연습 ${title}</title>

        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
        <link href="css/common.css" rel="stylesheet" />
<script src="https://code.jquery.com/jquery-3.6.3.min.js"></script>
 <script src="js/common.js"></script>
        
    </head>
    <body>
        <div class="d-flex" id="wrapper">
            <!-- Sidebar-->
			<jsp:include page="/WEB-INF/views/include/sidebar.jsp"/>
            <!-- Page content wrapper-->
            <div id="page-content-wrapper">
                <!-- Top navigation-->
			<jsp:include page="/WEB-INF/views/include/topnav.jsp"/>
                <!-- Page content-->
                <div class="container-fluid">
                    <h2>${vo.name}님의 정보 수정</h2>
				<form action="update.cu" method="post">
						<input type="hidden" name="id" value="${vo.id}" />
							<table class="table">
						<tbody>
							<tr>
								<th scope="col">이름</th>
								<td><input type="text" name="name" 
								class="chk" title="이름" value="${vo.name}" /></td>
								
							</tr>
							<tr>
								<th scope="col">성별</th>
								<td>
								
								<label>
								<input type="radio" value="남" name="gender"
								  ${vo.gender eq '남' ? 'checked':''}/>남</label>
								<label>
								<input type="radio" value="여" name="gender"
								  ${vo.gender eq '여' ? 'checked':''}/>여</label>
								
							
								</td>
							</tr>
							<tr>
								<th scope="col">이메일</th>
								<td><input type="text" name="email" value="${vo.email}" /></td>
							</tr>
							<tr>
								<th scope="col">전화번호</th>
								<td><input type="text" name="phone" value="${vo.phone}" /></td>
							</tr>
						</tbody>
					</table>
				</form>
				<button type="button" class="btn btn-primary modify"
					>수정</button>
				<button type="button" class="btn btn-secondary"
					onclick="history.go(-1)">취소</button>

                </div>
            </div>
        </div>
        <script> 
        $('.modify').click(function(){
        	if(tagIsInvalid($('[name=email]'))) return;
        	
        	if(emptyCheck())
        		$('form').submit();
        });
		var member = {
				tag_status: function( tag ){
					var name = tag.attr('name');
					if (name == 'email' ) return this.email_status(tag.val());
				},
				//이메일 입력상태 확인
				email_status:function(email){
					var reg = /^[a-zA-Z0-9+-\_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;
					if (reg.test(email)) 	return this.email.valid;
					else					return this.email.invalid;
				},
				email: {
					valid: {code:'valid',desc:'사용가능한 이메일'},
					invalid: {code:'invalid',desc:'이메일 형식이 맞지 않습니다.'},
					
				}
		} 
		function tagIsInvalid( tag ){
			var status = member.tag_status(tag);
			if (status.code == 'invalid'){
				alert('수정 불가!\n'+ status.desc);
				tag.focus();
				return true;
			}else{
				return false;
			}
		}
        </script>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
    </body>
</html>
