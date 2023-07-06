<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<title>페이징처리</title>
</head>
<body>
	<h2>학생조회결과</h2>
	<c:if test="${empty students }">
		<h3>조회된 학생이 없습니다.</h3>
	</c:if>
	<c:if test="${students != null && not empty students}">
		<c:forEach var="students" items="${students }">
			<h2>학생번호 <c:out value="${students.studentNo }"/></h2>
			<h2>학생이름<c:out value="${students.studentName }"/></h2>
			<h2>학생전화번호<c:out value="${students.studentTel }"/></h2>
			<h2>학생이메일<c:out value="${students.studentEmail }"/></h2>
			<h2>학생주소<c:out value="${students.studentAddress }"/></h2>
			<h2>학생등록일<c:out value="${students.reg_date }"/></h2>
		</c:forEach>
	</c:if>
	<c:out value="${pageBar }" escapeXml="false"/>
</body>
</html>