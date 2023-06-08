<%@page import="java.util.ArrayList"%>
<%@page import="com.web.notice.dto.NoticeDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<NoticeDto> noticeinfo = (List)request.getAttribute("noticeinfo");
%>    
    
<%@ include file="/views/common/header.jsp" %>
    <style>
    section#notice-container{width:600px; margin:0 auto; text-align:center;}
    section#notice-container h2{margin:10px 0;}
    table#tbl-notice{width:100%; margin:0 auto; border:1px solid black; border-collapse:collapse;}
    table#tbl-notice th, table#tbl-notice td {border:1px solid; padding: 5px 0; text-align:center;}
    #noticebtn{float: right; margin-bottom: 5px;} 
    </style>
<section id="notice-container">
        <h2>공지사항</h2> 
        <%if(loginmember != null && loginmember.getUserId().equals("admin")) {%>
        <input type="button" value="공지사항작성" id="noticebtn" onclick="location.assign('<%=request.getContextPath()%>/notice/noticewriter.do')">
        <%}%>
        
        <table id="tbl-notice">
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>첨부파일</th>
                <th>작성일</th>
                <th>상세보기</th>
            </tr>
            <tbody>
            <%if(noticeinfo.isEmpty()) {%>
				<tr>
					<td colspan="5">조회된 공지사항이 없습니다.</td>
				</tr>	
            <%}else{ 
            	for(NoticeDto n : noticeinfo) {%>
            	<tr> 
            		<td><%=n.getNoticeNo()%></td>
            		<td><%=n.getNoticeTitle()%></td>
            		<td><%=n.getNoticeWriter()%></td>
            		<td><%if(n.getNoticefilePath() != null) {%>
            			<img src="<%=request.getContextPath()%>/imges/file.png" width="20">
            		<%} %>
            		</td>
            		<td><%=n.getNoticeDate()%></td>
            		<td><input type="button" value="보기" onclick="location.assign('<%=request.getContextPath()%>/notice/noticedetaileview.do?NOTICE_NO=<%=n.getNoticeNo()%>')"></td>
            	</tr>
           	 
            <%}
            }%>	
           
            </tbody>
        </table>
        <div id="pageBar">
        	<%=request.getAttribute("pageBar")%>
        </div>
        
    </section>

<%@ include file="/views/common/footer.jsp" %>		