<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="css/styles.css" rel="stylesheet" />
<link href="css/common.css" rel="stylesheet" />

<!-- Bootstrap core JS-->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<script src="js/scripts.js"></script>
<!-- Jquery CDN -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<style>
.naver { background: url("img/naver_login.png") center no-repeat;
  			background-size: contain;
  			width:300px;
  			height:100px;
  			border:none; }
.kakao{background: url("img/kakao_login.png") center no-repeat;
  			background-size: contain;
  			width:300px;
  			height:100px;
  			border:none;}
</style>
</head>
<body>
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-6 mt-5 align-middle">
				<h2 class="text-center">로그인</h2>
				<form>
					<div class="mb-3 mt-5">
						<label class="form-label" for="inputEmail">ID</label> <input
							type="text" class="form-control chk" id="userid" placeholder="ID">
					</div>
					<div class="mb-3">
						<label class="form-label" for="inputPassword">Password</label> <input
							type="password" class="form-control chk" id="userpw"
							placeholder="Password">
					</div>
					<div class="mb-3"></div>
				</form>
			</div>
			<div class="row justify-content-center">
				<input onclick="fn_login()" type="submit"
					class="mt-3 col-6 btn btn-primary" value="로그인" />
			<div class="mt-3 row justify-content-center">
					<input type='button' class='naver social col-6' id='naver'></div>
			<div class="mt-3 row justify-content-center">
					<input type='button' class='kakao social col-6' id='kakao'></div>

				
			</div>
			
		</div>
	</div>

	<script>
		function emptyCheck() {
			var ok = true;
			$('.chk').each(function() {
				if ($(this).val() == '') {
					var tag = $(this).attr('placeholder');
					alert(tag + ' 입력하세요');
					$(this).focus();
					ok = false;
					return ok;
				}
			});
			return ok;
		}

		function fn_login() {
			if (!emptyCheck())
				return;
			$.ajax({
				url : 'smartLogin.mb',
				dataType : 'json',
				data : {
					id : $('#userid').val(),
					pw : $('#userpw').val()
				},
				success : function(response) {
					if (response)
						location = '<c:url value="/"/>';
					else {
						alert("아이디나 비밀번호가 일치 하지 않습니다!");
					}
				},
				error : function(req, text) {
					alert(text + ':' + req.status);
				}

			});
		}

		$('#userid').keypress(function(e) {
			if (e.keyCode == 13)
				$('#userpw').focus();
		})
		$('#userpw').keypress(function(e) {
			//console.log(e);
			//엔터키 누를때만
			if (e.keyCode == 13)
				fn_login();
		})
		
		
		$('.social').click(function(){
			location = $(this).attr('id') + 'Login.mb';
			});
		
	</script>
</body>
</html>