<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
     <%@ page import="com.web.member.model.dto.MemberDto" %>  
    <%
    	MemberDto m = (MemberDto)request.getAttribute("idcheck");
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>���̵� �ߺ�Ȯ��</title>
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
			[<span><%=request.getParameter("userId") %></span>]�� ��밡���մϴ�.	
			<br><br>
			<button type="button" >�ݱ�</button>
			<%}else{ %>

			[<span id="duplicated"><%=request.getParameter("userId")%></span>]�� ������Դϴ�.
			<br><br>
			<!-- ���̵� ���Է�â ���� -->
			<form action="<%=request.getContextPath()%>/member/idDuplicate.do" method="get">
				<input type="text" name="userId" id="userId">
				<input type="submit" value="�ߺ��˻�" >
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