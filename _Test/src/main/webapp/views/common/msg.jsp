<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>  
    
<%
	String msg = (String)request.getAttribute("msg");
	String loc = (String)request.getAttribute("loc");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�ý��� �޼���</title>
</head>
<body>
	<script>
		alert("<%=msg%>");
		location.replace("<%=request.getContextPath()%><%=loc%>");
	</script>
</body>
</html>