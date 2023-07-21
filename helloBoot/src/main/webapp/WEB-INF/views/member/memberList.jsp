<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>전체회원조회</h3>
	<c:if test="${not empty members }">
		<table>
			<tr>
				<td>아이디</td>
				<td>이름</td>
				<td>나이</td>
				<td>성별</td>
				<td>이메일</td>
			</tr>
			<c:forEach var="m" items="${members }">
				<tr>
					<td>${m.userId }</td>
					<td>${m.userName }</td>
					<td>${m.age }</td>
					<td>${m.gender }</td>
					<td>${m.email }</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>