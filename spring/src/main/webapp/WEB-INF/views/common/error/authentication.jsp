<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3 style="color:red"><%=exception !=null?exception.getMessage():"접근권한이 없습니다." %></h3>
	<h4>3초후 메인화면으로 이동합니다.</h4>
	<script>
		setTimeout(()=>{
			location.replace("${pageContext.request.contextPath}");
		},3000)
	</script>	
</body>
</html>