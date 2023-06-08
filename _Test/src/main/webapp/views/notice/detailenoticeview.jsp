<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.web.notice.dto.NoticeDto"%>
<%@page import="java.util.List"%>
<%
	NoticeDto dto = (NoticeDto)request.getAttribute("detailenotice");
%>    
<!DOCTYPE html>
<%@ include file="/views/common/header.jsp" %>  
<div id="notice-container">
		
        <table id="tbl-notice">
        <tr>
            <th>제 목</th>
            <td><input type="text" name="notice_title" value="<%=dto.getNoticeTitle()%>"></td>
        </tr>
        <tr>
            <th>작성자</th>
            <td><input type="text" name="notice_wirter" value="<%=dto.getNoticeWriter()%>"></td>
        </tr>
        <tr>
            <th>첨부파일</th>
            <td><input type="text" name="notice_filepath" value="<%=dto.getNoticefilePath() != null ? dto.getNoticefilePath() : "" %>"></td>
        </tr>
        <tr>
            <th>내 용</th>
            <td><input type="text" name="notice_content" value="<%=dto.getNoticeContent() %>"></td>
        </tr>
        <%if(loginmember != null && loginmember.getUserId().equals("admin") || loginmember.getUserId().equals(dto.getNoticeWriter())) {%>
        <tr>
            <th colspan="2">
                <input type="button" value="수정하기" onclick="location.assign('<%=request.getContextPath()%>/notice/updatenotice.do')">
                <input type="button" value="삭제하기" onclick="location.assign('<%=request.getContextPath()%>/notice/deletenotice.do?NOTICE_NO=<%=dto.getNoticeNo()%>')">
            </th>
        </tr>
        <%} %>
    </table>
    </div>
     <style>
    section#notice-container{width:600px; margin:0 auto; text-align:center;}
    section#notice-container h2{margin:10px 0;}
    table#tbl-notice{width:500px; margin:0 auto; border:1px solid black; border-collapse:collapse; clear:both; }
    table#tbl-notice th {width: 125px; border:1px solid; padding: 5px 0; text-align:center;} 
    table#tbl-notice td {border:1px solid; padding: 5px 0 5px 10px; text-align:left;}
    </style>
<%@ include file="/views/common/footer.jsp" %>