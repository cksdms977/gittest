<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Arrays" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>  
<!DOCTYPE html>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value="리스트로가져오기"/>
</jsp:include>
<section id="content">
	<table class="table">
		<tr>
			<th scope="col">번호</th>
			<th scope="col">이름</th>
			<th scope="col">나이</th>
			<th scope="col">이메일</th>
			<th scope="col">성별</th>
			<th scope="col">개발가능언어</th>
			<th scope="col">수정</th>
		</tr>
		
		<c:if test="${not empty demos}">
		<c:forEach var="demos" items="${demos}">
		<tr>
			<td>${demos.devNo }</td>
			<td>${demos.devName }</td>
			<td>${demos.devAge }</td>
			<td>${demos.devEmail }</td>
			<td>${demos.devGender }</td>
			<td>${Arrays.toString(demos.devLang)}</td>
			<td><button id="updateDemo" onclick="update_btn()">수정하기</button></td>
		</tr>	
		</c:forEach>
		</c:if>
	</table>	
</section>
<script>
	const update_btn = () =>{
		$("#updateDemo").attr("action", "${path}/demo/updateDemo.do");
		$("#updateDemo").submit();
	}
</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>