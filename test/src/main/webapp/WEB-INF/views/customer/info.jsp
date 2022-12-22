<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html lang="ko">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Simple Sidebar - Start Bootstrap Template</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="css/styles.css" rel="stylesheet" />
<link href="css/common.css" rel="stylesheet" />
</head>
<body>
	<div class="d-flex" id="wrapper">
		<!-- Sidebar-->
		<jsp:include page="/WEB-INF/views/include/sidebar.jsp" />
		<!-- Page content wrapper-->
		<div id="page-content-wrapper">
			<!-- Top navigation-->
			<jsp:include page="/WEB-INF/views/include/topnav.jsp" />
			<!-- Page content-->
			<div class="container-fluid">
				<h2>${vo.name}님의 상세 정보</h2>
					<table class="table">
						<tbody>
							<tr>
								<th scope="col">이름</th>
								<td>${vo.name}</td>
							</tr>
							<tr>
								<th scope="col">성별</th>
								<td>${vo.gender}</td>
							</tr>
							<tr>
								<th scope="col">이메일</th>
								<td>${vo.email}</td>
							</tr>
							<tr>
								<th scope="col">전화번호</th>
								<td>${vo.phone }</td>
							</tr>
						</tbody>
					</table>
			</div>
		</div>
	</div>
	<!-- Bootstrap core JS-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="js/scripts.js"></script>
</body>
</html>
