<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>회원정보 [${method}]</h3>
<div class="">성명 : ${name}</div>
<div class="">성별 : ${gender}</div>
<div class="">이메일: ${email} </div>
<hr />

<h3>회원정보 vo. [${method}]</h3>
<div class="">성명 : ${vo.name}</div>
<div class="">성별 : ${vo.gender}</div>
<div class="">이메일: ${vo.email} </div>


<div class="">
<a href='<c:url value="/member"></c:url>'>회원가입</a>
</div>

</body>
</html>