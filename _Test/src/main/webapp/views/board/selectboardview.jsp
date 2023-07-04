<%@page import="com.web.board.model.dao.BoardComment"%>
<%@page import="java.util.List"%>
<%@page import="com.web.board.dto.BoardDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	BoardDto dto = (BoardDto)request.getAttribute("selectboard");
	List<BoardComment> comments = (List)request.getAttribute("boardcomment");
%>

<%@ include file="/views/common/header.jsp" %>  
<style>
    section#board-container{width:600px; margin:0 auto; text-align:center;}
    section#board-container h2{margin:10px 0;}
    table#tbl-board{width:500px; margin:0 auto; border:1px solid black; border-collapse:collapse; clear:both; }
    table#tbl-board th {width: 125px; border:1px solid; padding: 5px 0; text-align:center;} 
    table#tbl-board td {border:1px solid; padding: 5px 0 5px 10px; text-align:left;}
    
     div#comment-container button#btn-insert{width:60px;height:50px; color:white;
    background-color:#3300FF;position:relative;top:-20px;}
        /*댓글테이블*/
    table#tbl-comment{width:580px; margin:0 auto; border-collapse:collapse; clear:both; } 
    table#tbl-comment tr td{border-bottom:1px solid; border-top:1px solid; padding:5px; text-align:left; line-height:120%;}
    table#tbl-comment tr td:first-of-type{padding: 5px 5px 5px 50px;}
    table#tbl-comment tr td:last-of-type {text-align:right; width: 100px;}
    table#tbl-comment button.btn-reply{display:none;}
    table#tbl-comment button.btn-update{display:none;}
    table#tbl-comment button.btn-delete{display:none;}
    table#tbl-comment tr:hover {background:lightgray;}
    table#tbl-comment tr:hover button.btn-reply{display:inline;}
    table#tbl-comment tr:hover button.btn-delete{display:inline;}
    table#tbl-comment tr:hover button.btn-update{display:inline;}
    table#tbl-comment tr.level2 {color:gray; font-size: 14px;}
    table#tbl-comment sub.comment-writer {color:navy; font-size:14px}
    table#tbl-comment sub.comment-date {color:tomato; font-size:10px}
    table#tbl-comment tr.level2 td:first-of-type{padding-left:100px;}
    table#tbl-comment tr.level2 sub.comment-writer {color:#8e8eff; font-size:14px}
    table#tbl-comment tr.level2 sub.comment-date {color:#ff9c8a; font-size:10px}
    /*답글관련*/
    table#tbl-comment textarea{margin: 4px 0 0 0;}
    table#tbl-comment button.btn-insert2{width:60px; height:23px; color:white; background:#3300ff; position:relative; top:-5px; left:10px;}
    
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
			<%-- <%if(loginmember != null && (loginmember.getUserId().equals(dto.getBoardWriter()))) { %> --%>
			<tr>
				<th colspan="2">
					<input type="submit" value="수정하기" onclick="location.assign('<%=request.getContextPath()%>/board/updateboard.do?BOARD_NO=<%=dto.getBoardNo()%>')">
					<input type="submit" value="삭제하기" onclick="location.assign('<%=request.getContextPath()%>/board/deleteboard.do?BOARD_NO=<%=dto.getBoardNo()%>')">
				</th>
			</tr>
			<%-- <%} %> --%>
		</table>
		<div id="comment-container">
			<div class="comment-deitor">
				<form action="<%=request.getContextPath()%>/board/insertComment.do">
					<textarea name="content" rows="3" cols="55"></textarea>
					<input type="hidden" name="boardRef" value="<%=dto.getBoardNo()%>">
					<input type="hidden" name="level" value="1">
					<input type="hidden" name="boardCommentWriter" value="<%=loginmember != null ? loginmember.getUserId() : ""%>">
					<input type="hidden" name="boardCommentRef" value="0">
					<button type="submit" id="btn-insert">등록</button>
				</form>
			</div>
		</div>
		<table id="tbl-comment">
		<%if(comments != null) {
			for(BoardComment bc : comments) {
			if(bc.getLevel() == 1) {%>
			<tr class="levell">
				<td>
					<sub class="comment-writer"><%=bc.getBoardCommentWriter()%></sub>
					<sub class="comment-date" ><%=bc.getBoardCommentDate()%></sub>
					<br>
					<%=bc.getBoardCommentContent()%>
				</td>
				<td>
					<%if(loginmember != null) { %>
					<button class = "btn-replay" value="<%=bc.getBoardCommentNo()%>">답글</button>
					
					<button class = "btn-update">수정</button>
					<button class = "btn-delete">삭제</button>
					<%} %>
				</td>
			</tr>
			<%}else {%>
			 	<tr class="level2">
				<td>
					<sub class="comment-writer"><%=bc.getBoardCommentWriter()%></sub>
					<sub class="comment-date" ><%=bc.getBoardCommentDate()%></sub>
					<br>
					<%=bc.getBoardCommentContent()%>
				</td>
				<td></td>
			 	</tr>
			 <%} 
			 }%>
			<%} %>
		</table>
    </section>
    <script>
    	$(".btn-replay").click(e =>{
    		const tr = $("<tr>");
    		const td = $("<td>").attr("colspan", "2");
    		const boardRef = $(e.target).val();
    		//console.log(boardRef);
    		const form = $(".comment-deitor>form").clone();
    		/* console.log(form.find("textarea"));
    		console.log(form.find("input[name=level]")); */
    		
    		form.find("textarea").attr("rows", "1");
    		form.find("input[name=level]").val("2");
    		form.find("input[name=boardCommentRef]").val(boardRef);
    		td.css("dilsplay", "none");
    		td.append(form);
    		tr.append(td);
    		/* $(e.target).parents("tr").after(tr);
    		tr.children("td").slideDown(800); */
    		tr.insertAfter($(e.target).parents("tr")).children("td").slideDown(800);
    		$(e.target).off("click");
    	});

		/* const click = (loginmember) =>{
			if(loginmember != null)
		} */
		$("#comment-container textarea[name=content]").focus(e=>{
			if(<%=loginmember == null%>) {
				alert("로그인후이용이가능합니다.");
				$("#userId").focus();
			}
		})
	</script>
<%@ include file="/views/common/footer.jsp" %>