<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>채팅</title><!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>

<!-- Popper JS -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<script src="${path }/resources/js/jquery-3.7.0.min.js"></script>
</head>
<body>
	<div class="container-fluid">
	<h2>채팅하자</h2>
	<div id="chattingcontainer"></div>
	<div id="accessMember"></div>
	<input class="form-control" type="text" id="msg"><button class="btn btn-outline-danger" id="send">전송</button>
	</div>
	
	<script type="text/javascript">
		const server = new WebSocket("ws://localhost:8080/spring/chatting");
		const me = '${loginMember.userId}';
		server.onopen = data=>{
			server.send(JSON.stringify(new Message("open", '${loginMember.userId}')));
		}
		server.onmessage=data=>{
			console.log(data);
		}
		server.onclose=data=>{
			const msg=JSON.parse(data.data);
			console.log(msg);
			switch(msg.type){
			case "msg" : printMessage(msg);break;
			case "system" : systemMessage(msg.msg); break;
			}
		}
		$("#send").click(e=>{
			const msg = $("#msg").val();
			server.send(JSON.stringify(new Message("msg", me,"",msg,"")));
		});
		function systemMessage(msg){
			$("#accessMember").html("");
			msg.msg.split(",").forEach(e=>{
				const m=$("<h5>").text(e);
				$("#accessMember").append(m);
			})
		}
		function printMessage(msg){
			const msgContainter = $("<div>");
			const content = $("<h4>").text(msg.msg).css("text-align", msg.sender==me?"right":"left");
			msgContainer.append(content);
			$("#chattingcontainer").append(msgContainer);
		}
		class Message{
			constructor(type="", sender="", reciever="", msg="", room=""){
				this.type=type;
				this.sender=sender;
				this.reciever=reciever;
				this.msg=msg;
				this.room=room;
			}
		}
	</script>
</body>
</html>