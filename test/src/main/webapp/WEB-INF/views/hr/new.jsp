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
                    <h2>신규 사원 등록</h2>
				<form action="insert.hr" method="post">
					<table class="table">
						<tbody>
							<tr>
								<th scope="col">이름(이름 성)</th>
								<td><input type="text" name="first_name"/>
								<input type="text" name="last_name"/></td>
							</tr>	
							<tr>
								<th scope="col">이메일</th>
								<td><input type="text" name="email"/></td>
							</tr>
							<tr>
							<tr>
								<th scope="col">전화번호</th>
								<td><input type="text" name="phone_number"/></td>
							</tr>
							
							<tr>
								<th scope="col">고용일</th>
								<td><input type="date" name="hire_date"/></td>
							</tr>
							<tr>
								<th scope="col">급여</th>
								<td><input type="text" name="salary"/></td>
							</tr>
							<tr>
								<th scope="col">업무명</th>
								<td>
								<select name="job_id" id="">
								<c:forEach var="j" items="${jobs}">
								<option value="${j.job_id}">
								${j.job_title}</option>
								</c:forEach>
								</select>
								</td>
							</tr>
							<tr>
								<th scope="col">부서명</th>
								<td>
								<select name="department_id">
								<option value='-1'>부서선택</option>
								<c:forEach var="d" items="${departments}">
								<option value="${d.department_id}">
								${d.department_name}</option>
								</c:forEach>
								</select>
								</td>
							</tr>
						</tbody>
					</table>
				</form>
                   
				<button type="button" class="btn btn-primary modify">확인</button>
                    <button type="button" class="btn btn-secondary"
					onclick="history.go(-1)">취소</button>
                </div>
            </div>
        </div>
     <script>
     $('.modify').click(function(){
  	
     	if(emptyCheck())
     		$('form').submit();
     });

	</script>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
    </body>
</html>
