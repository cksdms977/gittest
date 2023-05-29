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
        <h2>�� ��������</h2>
        <%if(m != null) {
        for(MemberDto d : m) {%>
        <form action="<%=request.getContextPath()%>/member/selectmemberinfo.do" method="post">
        <table>
			<tr>
				<th>���̵�</th>
				<td><%=d.getUserId()%></td>
			</tr>
			<tr>
				<th>�н�����</th>
				<td><%=d.getPassword()%></td>
			</tr>
			<tr>
				<th>�̸�</th>
				<td><%=d.getUserName()%></td>
			</tr>
			<tr>
				<th>����</th>
				<td><%=d.getAge()%></td>
			</tr> 
			<tr>
				<th>�̸���</th>
				<td><%=d.getEmail()%></td>
			</tr>
			<tr>
				<th>�޴���</th>
				<td><%=d.getPhone()%></td>
			</tr>
			<tr>
				<th>�ּ�</th>
				<td><%=d.getAddress()%></td>
			</tr>
			<tr>
				<th>����</th>
				<td><%=d.getGender()%></td>
			</tr>
			<tr>
				<th>���</th>
				<td><%=d.getHobby()%></td>
			</tr>
		</table>
		<button type="button" name="updatemember" onclick="location." value="ȸ����������">ȸ����������</button>
        </form>
        <%}
        }%>
    </section>

<%@ include file="/views/common/footer.jsp" %>
