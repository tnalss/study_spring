<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<form action="login_result" method="post">

<div class="">
	ID : <input type="text" placeholder="ID" name ="id" />
	
	</div>

<div class="">
	PW : <input type="password" placeholder="PW" name ="pw" />
	
	</div>
	<input type="submit" value="로그인"/>
</form>



</body>
</html>