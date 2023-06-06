<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ page import="com.web.member.model.dto.MemberDto" %>  
    <%
    	MemberDto m = (MemberDto)request.getAttribute("idcheck");
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>아이디 중복확인</title>
</head>
<body>
	<style>
	div#checkId-container{
		text-align:center;
		
	}
	span#duplicated{
	text-align:center;
	padding-top:
	}
	span#duplicated{
		color:red;
		font-weight:
	}
</style>
</head>
<body>
	<div id="checkId-container">
			<%if(m == null) {%>
			[<span><%=request.getParameter("userId") %></span>]는 사용가능합니다.	
			<br><br>
			<button type="button" >닫기</button>
			<%}else{ %>

			[<span id="duplicated"><%=request.getParameter("userId")%></span>]는 사용중입니다.
			<br><br>
			<!-- 아이디 재입력창 구성 -->
			<form action="<%=request.getContextPath()%>/member/idDuplicate.do" method="get">
				<input type="text" name="userId" id="userId">
				<input type="submit" value="중복검사" >
			</form>
			<%} %>
	</div>
	<script>
	const btn=document.querySelector("button[type=button]");
		  btn.addEventListener("click",e=>{
			opener.document.querySelector("#userId_").value="<%=request.getParameter("userId")%>";
		 	close();
		});
	</script>
</body>
</html>