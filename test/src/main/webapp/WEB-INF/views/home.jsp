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
                    <h1 class="mt-4">홈페이지</h1>
                    
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.6.3.min.js"></script>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
    </body>
</html>
