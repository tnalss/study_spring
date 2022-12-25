<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css"/>
<style>
#profileImg{
width:30px;
height:30px;
}
</style>
<nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
	<div class="container-fluid">
		<button class="btn btn-primary" id="sidebarToggle">Toggle
			Menu</button>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="align-items-center navbar-nav ms-auto mt-2 mt-lg-0">
			<c:if test="${not empty loginInfo.profile}">
<li class="nav-item active">
	<a class="nav-link"><img src="${loginInfo.profile}" alt="profile" id="profileImg"/></a>
</li>
</c:if>
<c:if test="${not empty loginInfo}">
<c:if test="${empty loginInfo.profile}">
<li class="nav-item active">
	<a class="nav-link"><i class="fa-solid fa-user"></i></a>
</li>
</c:if>
</c:if>

<c:if test="${not empty loginInfo}">
<li class="nav-item active">
	<a class="nav-link">${loginInfo.name}</a>
</li>
</c:if>
				<li class="nav-item active"><a class="nav-link" href="<c:url value='/'/>"> Home</a></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" id="navbarDropdown" href="#"
					role="button" data-bs-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false">더보기</a>
					<div class="dropdown-menu dropdown-menu-end"
						aria-labelledby="navbarDropdown">
						<c:if test="${empty loginInfo}">
						<a class="dropdown-item" href="<c:url value='/login.mb'/>"> 로그인</a>
						<a class="dropdown-item" href="<c:url value='/join.mb'/>"> 회원가입</a>
						</c:if>
						<c:if test="${not empty loginInfo}">
						<a class="dropdown-item" href="<c:url value='/change.mb'/>"> 비밀번호변경</a>
						<a class="dropdown-item" href="<c:url value='/logout.mb'/>"> 로그아웃</a>
						</c:if>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="#!">Something else here</a>
					</div></li>
			</ul>
		</div>
	</div>
</nav>