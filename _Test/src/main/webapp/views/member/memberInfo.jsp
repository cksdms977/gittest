<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.List"%>
<%@page import="com.web.member.model.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.web.member.model.dto.MemberDto" %>
<%
String userId = (String)session.getAttribute("userId");

List<MemberDto> m = (List)request.getAttribute("selectMemberInfo");

%>    
    
    
<!DOCTYPE html>

<%@ include file="/views/common/header.jsp" %>    
<section id=enroll-container>
        <h2>내 정보보기</h2>
        <%if(m != null) {
        for(MemberDto d : m) {%>
        <form action="<%=request.getContextPath()%>/member/selectmemberinfo.do" method="post">
        <table>
			<tr>
				<th>아이디</th>
				<td><%=d.getUserId()%></td>
			</tr>
			<tr>
				<th>패스워드</th>
				<td><%=d.getPassword()%></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><%=d.getUserName()%></td>
			</tr>
			<tr>
				<th>나이</th>
				<td><%=d.getAge()%></td>
			</tr> 
			<tr>
				<th>이메일</th>
				<td><%=d.getEmail()%></td>
			</tr>
			<tr>
				<th>휴대폰</th>
				<td><%=d.getPhone()%></td>
			</tr>
			<tr>
				<th>주소</th>
				<td><%=d.getAddress()%></td>
			</tr>
			<tr>
				<th>성별</th>
				<td><%=d.getGender()%></td>
			</tr>
			<tr>
				<th>취미</th>
				<td><%=d.getHobby()%></td>
			</tr>
		</table>
		<button type="button" name="updatemember" onclick="location." value="회원정보수정">회원정보수정</button>
        </form>
        <%}
        }%>
    </section>

<%@ include file="/views/common/footer.jsp" %>
