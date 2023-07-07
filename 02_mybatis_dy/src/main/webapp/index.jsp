<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>마이바티스 동적쿼리</h3>
	<h4><a href="${path }/selectemployeeall">전체사원조회</a></h4>
	
	<h2>Data연관관계설정하기</h2>
	<h3><a href="${pageContext.request.contextPath }/emp/association.do">join으로 객체가져오기</a></h3>
	
	<h2>부서조회하기</h2>
	<h3><a href="${pageContext.request.contextPath }/emp/selectDeptAll.do">join으로 객체가져오기</a></h3>
	
	<h2>다른환경 접속하기</h2>
	<h2><a href="${pageContext.request.contextPath }/member.do">회원가져오기</a></h2>
	
	<h2>게시글가져오기</h2>
	<h3>
		<a href="${pageContext.request.contextPath }/board.do?no=51">게시글&댓글 전체출력, 작성자 이름, 이메일 출력</a>
	</h3>
</body>
</html>