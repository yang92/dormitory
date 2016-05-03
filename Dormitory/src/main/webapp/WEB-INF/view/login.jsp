<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script>
	function fnMoveJoin() {
		location.href = "/join.do";
	}
</script>
</head>
<body>
	<form method="post" action="/login.do">
		ID : <input type="text" name="id" />
		<br />
		PW : <input type="password" name="pw" />
		<br />
		<input type="submit" value="로그인" />
		<input type="button" value="회원가입" 
		       onclick="fnMoveJoin()" />
	</form>
</body>
</html>












