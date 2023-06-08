<%@page import="com.web.board.dto.BoardDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	BoardDto dto = (BoardDto)request.getAttribute("selectboard");
%>

<%@ include file="/views/common/header.jsp" %>  
<style>
    section#board-container{width:600px; margin:0 auto; text-align:center;}
    section#board-container h2{margin:10px 0;}
    table#tbl-board{width:500px; margin:0 auto; border:1px solid black; border-collapse:collapse; clear:both; }
    table#tbl-board th {width: 125px; border:1px solid; padding: 5px 0; text-align:center;} 
    table#tbl-board td {border:1px solid; padding: 5px 0 5px 10px; text-align:left;}
</style>
   
		<section id="board-container">
		<h2>게시판</h2>
		<table id="tbl-board">
			<tr>
				<th>글번호</th>
				<td><input type="text" name="board_no" value="<%=dto.getBoardNo()%>"></td>
			</tr>
			<tr>
				<th>제 목</th>
				<td><input type="text" name="board_title" value="<%=dto.getBoardTitle()%>"></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><input type="text" name="board_writer" value="<%=dto.getBoardWriter()%>"></td>
			</tr>
			<tr>
				<th>조회수</th>
				<td><input type="text" name="board_count" value="<%=dto.getBoardReadcount()%>"></td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td>
					<input type="file" name="board_no" value="<%=dto.getBoardRenamed() != null ? dto.getBoardRenamed() : ""%>">
				</td>
			</tr>
			<tr>
				<th>내 용</th>
				<td><textarea cols="47" rows="7" name="board_content"><%=dto.getBoardContent()%></textarea></td>
			</tr>
			<%--글작성자/관리자인경우 수정삭제 가능 --%>
			
			<tr>
				<th colspan="2">
					<input type="submit" value="수정하기" onclick="location.assign('<%=request.getContextPath()%>/board/updateboard.do?BOARD_NO=<%=dto.getBoardNo()%>')">
					<input type="submit" value="삭제하기" onclick="location.assign('<%=request.getContextPath()%>/board/deleteboard.do?BOARD_NO=<%=dto.getBoardNo()%>')">
				</th>
			</tr>
			
		</table>
    </section>
<%@ include file="/views/common/footer.jsp" %>