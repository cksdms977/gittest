<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boot메인화면</title>
</head>
<body>
	<h2>나의 첫 boot화면</h2>
	<h3><a href="${pageContext.request.contextPath }/member/memberAll">전체회원조회</a></h3>
	<form action="${path }/fileUpload" enctype="multipart/form-data" method="post">
		<input type="file" name="upFile">
		<input type="file" name="upFile">
		<input type="file" name="upFile">
		<input type="submit" value="파일저장">
	</form>
	<form action="${page }/datatest" method="post">
		<input type="text" name="data">
		<input type="submit" value="전송">	
	</form>
	<!-- 아이디로 조회 -->
	<form action="${page }/memberId" method="post">
		<input type="text" name="userId">
		<input type="submit" value="전송">	
	</form>
</body>
</html>