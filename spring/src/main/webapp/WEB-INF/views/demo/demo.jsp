<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--  <%
 String str = null;
 str.length();
 %> --%>
 
 <c:set var="path" value="${pageContext.request.contextPath }"/>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value="RequestTest"/>
</jsp:include>
<section id="content">
	<div id="demo-container">
		<form id="devFrm" method="post">
			<div class="form-group row">
			<label for="devName" class="col-sm-2 col-form-label">이름</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="devName" name="devName">
			</div>
			</div>
			<div class="form-group row">
			<label for="devAge" class="col-sm-2 col-form-label">나이</label>
			<div class="col-sm-10">
				<input type="number" class="form-control" id="devAge" name="devAge">
			</div>
			</div>
			<div class="form-group row">
			<label for="devEmail" class="col-sm-2 col-form-label">이메일</label>
			<div class="col-sm-10">
				<input type="email" class="form-control" id="devEmail" name="devEmail">
			</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label">성별</label>
				<div class="col-sm-10">
					<div class="form-check form-check-inline">
					<input class="form-check-input" type="radio" name="devGender" id="devGender0" value="M">
					<label class="form-check-label" for="devGender0">남</label>
					</div>
					<div class="form-check form-check-inline">
					<input class="form-check-input" type="radio" name="devGender" id="devGender1" value="F">
					<label class="form-check-label" for="devGender1">여</label>
					</div>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label">개발언어</label>
				<div class="col-sm-10">
					<div class="form-check form-check-inline">
					<input class="form-check-input" type="checkbox" name="devLang" id="devLang0" value="Java">
					<label class="form-check-label" for="devLang0">Java</label>
					</div>
					<div class="form-check form-check-inline">
					<input class="form-check-input" type="checkbox" name="devLang" id="devLang1" value="C">
					<label class="form-check-label" for="devLang1">C</label>
					</div>
					<div class="form-check form-check-inline">
					<input class="form-check-input" type="checkbox" name="devLang" id="devLang2" value="Javascript">
					<label class="form-check-label" for="devLang2">Javascript</label>
					</div>
				</div>
			</div>
			<div class="form-group row">
				<label for="brithDay" class="col-sm-2 col-form-label">생년월일</label>
					<div class="col-sm-10">
						<input type="date" class="form-control" id="birthDay" name="birthDay">
					</div>
			</div>
			
			<div class="form-group row">
				<div class="col-sm-12">
					<button type="button" class="col-sm-12 btn btn-outline-primary" onclick="requestSend('demo/demo1.do')">
						spring 메소드 서블릿처럼이용하기
					</button>
				</div>
			</div>
			
			<div class="form-group row">
				<div class="col-sm-12">
					<button type="button" class="col-sm-12 btn btn-outline-primary" onclick="requestSend('demo/demo2.do')">
						1:1로 매칭해서 데이터를 받기
					</button>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm-12">
					<button type="button" class="col-sm-12 btn btn-outline-primary" onclick="requestSend('demo/demo3.do')">
						@RequestParam 매칭해서 데이터를 받기
					</button>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm-12">
					<button type="button" class="col-sm-12 btn btn-outline-primary" onclick="requestSend('demo/demo4.do')">
						Vo(command)를 이용해서 데이터를 받기
					</button>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm-12">
					<button type="button" class="col-sm-12 btn btn-outline-primary" onclick="requestSend('demo/demo5.do')">
						Map을 이용해서 데이터를 받기
					</button>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm-12">
					<button type="button" class="col-sm-12 btn btn-outline-primary" onclick="requestSend('demo/demo6.do')">
						추가데이터 확인하기
					</button>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm-12">
					<button type="button" class="col-sm-12 btn btn-outline-primary" onclick="requestSend('demo/demo7.do')">
						ModelAndView 이용하기
					</button>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm-12">
					<button type="button" class="col-sm-12 btn btn-outline-primary" onclick="requestSend('demo/demo8.do')">
						@ResponseBody 이용하기
					</button>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm-12">
					<button type="button" class="col-sm-12 btn btn-outline-primary" onclick="requestSend('demo/demo9.do')">
						방식에 따라 요청처리하기
					</button>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm-12">
					<button type="button" class="col-sm-12 btn btn-outline-primary" onclick="insertDemo();">
						저장하기
					</button>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm-12">
					<button type="button" class="col-sm-12 btn btn-outline-primary" onclick="selectAllDemo();">
						리스트로 가져오기
					</button>
				</div>
			</div>
		</form>
	</div>
</section>
<script>
	const requestSend=(url)=>{
		$("#devFrm").attr("action","${path}/"+url);
		$("#devFrm").submit();
	}
	const insertDemo = () => {
		$("#devFrm").attr("action","${path}/demo/insertDemo.do");
		$("#devFrm").submit();
	}
	const selectAllDemo = () => {
		$("#devFrm").attr("action", "${path}/demo/selectAllDemo.do");
		$("#devFrm").submit();
		
	}
</script>
<style>
div#demo-container{
		width:50%;
		padding:15px;
		margin:0 auto;
		border:1px solid lightgray;
		border-radius:10px;
}
</style>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>