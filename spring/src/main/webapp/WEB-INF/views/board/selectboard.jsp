<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <c:set var="path" value="${pageContext.request.contextPath }"/>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value="회원가입"/>
</jsp:include>    
 <div id="board-container">
        <input type="text" class="form-control" placeholder="Á¦¸ñ" name="boardTitle" id="boardTitle" value="${board.boardTitle }"  required>
        <input type="text" class="form-control" name="boardWriter" value="${board.boardWriter.userId }" readonly required>
		<c:if test="${not empty board.file}">
		<c:forEach var="f" items="${board.file }">
                    <button type="button" 
                    class="btn btn-outline-success btn-block"
                    onclick="fn_fileDownload('${f.originalFilename}', '${f.renamedFilename }')">
                    ${f.originalFilename }
            </button>
        	</c:forEach>
        </c:if>
        <textarea class="form-control" name="boardContent" placeholder="${board.boardContent }" required></textarea>
    </div>
<script type="text/javascript">
	function fn_fileDownload(oriName, reName){
		location.assign("${path}/board/filedownload?oriname="+oriName+"&rename="+reName);
	}
</script>
     <style>
    div#board-container{width:400px; margin:0 auto; text-align:center;}
    div#board-container input,div#board-container button{margin-bottom:15px;}
    div#board-container label.custom-file-label{text-align:left;}
    </style>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>