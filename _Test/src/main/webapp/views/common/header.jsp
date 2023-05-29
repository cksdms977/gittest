<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%@ page import="com.web.member.model.dto.MemberDto" %>
<%
	MemberDto loginmember = (MemberDto)session.getAttribute("loginmember");
	
	Cookie[] cookies = request.getCookies();
	String saveId = null;
	if(cookies != null) {
		for(Cookie c : cookies) {
			if(c.getName().equals("saveId")){
				saveId = c.getValue();
				break;
		}
	}
}

%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>header</title>
<<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css"/>
<script src="<%=request.getContextPath()%>/js/jquery-3.7.0.min.js"></script>
</head>
<body>
	<div id="container">
		<header>
			<h1>HelloMVC</h1>
			<div class="login-container">
			<%if(loginmember == null) { %>
				<form id="loginFrm" action="<%=request.getContextPath()%>/login.do" method="post">
					<table>
						<tr>
							<td>
								<input type="text" name="userId" id="userId" placeholder="���̵�" value="<%=saveId != null ? saveId: ""%>">
							</td>
						</tr>
						<tr>
							<td>
								<input type="password" name="password" id="password" placeholder="�н�����">
							</td>
							<td>
								<input type="submit" value="�α���">
							</td>
						</tr>
						<tr>
							<td>
								<input type="checkbox" name="saveId" id="saveId" <%=saveId != null ? "checked" : "" %>>
								<label for="saveId">���̵�����</label>
								<input type="button" value="ȸ������" onclick="location.assign('<%=request.getContextPath()%>/member/enrollMember.do')">
							</td>
						</tr>
					</table>
				</form>
				<%}else{ %>
					<table id="logged-in">
						<tr>
							<td colspan="2">
								<%=loginmember.getUserName()%>��, ȯ���մϴ�. :)
							</td>
						</tr>
						<tr>
							<td>
								<input type="button" value="�� ��������" onclick="location.assign('<%=request.getContextPath()%>/member/selectmemberinfo.do')">
							</td>
							<td>
								<input type="button" value="�α׾ƿ�" onclick="location.replace('<%=request.getContextPath()%>/logout.do')">
							</td>
						</tr>
					</table>
					<%}%>
			</div>
			<nav>
				<ul class="main-nav">
					<li class="home"><a href="">Home</a></li>
					<li id="notice"><a href="">��������</a></li>
					<li id="board"><a href="">�Խ���</a></li>
				</ul>
			</nav>
		</header>
