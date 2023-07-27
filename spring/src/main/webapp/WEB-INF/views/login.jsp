<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기본 로그인 화면</title>
</head>
<body>
	<h2>로그인</h2>
	<form action="${pageContext.request.contextPath }/login.do" method="post">
		<input type="text" name="userId"><br>
		<input type="password" name="pw"><br>
	</form>
</body>
</html>