<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>회원가입 화면</h3>

<form action="joinRequest" method="post">

<div>이름 :
<input type="text" name="name"/>
</div>

<div>성별 : 
<input type="radio" name="gender" value="남" checked />남
<input type="radio" name="gender" value="여" />여

</div>
<div>
이메일 :
<input type="text" name="email" />
</div>

<input type="submit" value="HttpServletRequest" />
<input type="submit" value="@requestParam" onclick="action='joinParam'" />
<input type="submit" value="데이터 객체" onclick="action='joinDataObject'" />
<input type="submit" value="@PathVariable" onclick="fn_submit(this.form)" />



</form>
<script>
function fn_submit(f){
	//action="joinPath/박문수/여/park@"
	f.action="joinPath/"+f.name.value+"/"+f.gender.value+"/"+f.email.value;
}

</script>


</body>
</html>