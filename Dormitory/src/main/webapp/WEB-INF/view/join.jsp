<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script>
	function fnMoveLogin() {
		location.href = "/login.do";
	}
</script>
</head>
<body>
	<form method="post" action="/join.do">
		ID : <input type="text" name="id" />
		<br />
		PW : <input type="password" name="pw" />
		<br />
		성명 : <input type="text" name="name" />
		<br />
		학년 :
		<select name="grade">
			<option value="">-- 선택 --</option>
			<option value="1">1학년</option>
			<option value="2">2학년</option>
			<option value="3">3학년</option>
			<option value="4">4학년</option>
		</select>
		<br />
		<input type="submit" value="회원가입" />
		<input type="button" value="로그인으로 이동" 
		       onclick="fnMoveLogin()" />
	</form>
</body>
</html>












