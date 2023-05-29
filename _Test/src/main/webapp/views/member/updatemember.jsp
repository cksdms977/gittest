<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<%@ include file="/views/common/header.jsp" %>    
<section id=enroll-container>
		<h2>회원 정보 수정</h2>
		<form id="memberFrm" method="post" action="<%=request.getContextPath()%>/member/memberUpdate.do">
			<table>
				<tr>
					<th>아이디</th>
					<td>
						<input type="text" name="userId" id="userId_" >
					</td>
				</tr>
				<tr>
					<th>패스워드</th>
					<td>
						<input type="password" name="password" id="password_">
					</td>
				</tr>
				<tr>
					<th>패스워드확인</th>
					<td>	
						<input type="password" id="password_2"><br>
					</td>
				</tr>  
				<tr>
					<th>이름</th>
					<td>	
					<input type="text"  name="userName" id="userName" required><br>
					</td>
				</tr>
				<tr>
					<th>나이</th>
					<td>	
					<input type="number" name="age" id="age"><br>
					</td>
				</tr> 
				<tr>
					<th>이메일</th>
					<td>	
						<input type="email" placeholder="abc@xyz.com" name="email" id="email"><br>
					</td>
				</tr>
				<tr>
					<th>휴대폰</th>
					<td>	
						<input type="tel" placeholder="(-없이)01012345678" name="phone" id="phone" maxlength="11"><br>
					</td>
				</tr>
				<tr>
					<th>주소</th>
					<td>	
						<input type="text" placeholder="" name="address" id="address" ><br>
					</td>
				</tr>
				<tr>
					<th>성별 </th>
					<td>
						DB정보에 따라 분기처리할것
							<input type="radio" name="gender" id="gender0" value="M" >
							<label for="gender0">남</label>
							<input type="radio" name="gender" id="gender1" value="F">
							<label for="gender1">여</label>
					</td>
				</tr>
				<tr>
					<th>취미 </th>
					<td>
						<input type="checkbox" name="hobby" id="hobby0" value="운동" ><label for="hobby0">운동</label>
						<input type="checkbox" name="hobby" id="hobby1" value="등산" ><label for="hobby1">등산</label>
						<input type="checkbox" name="hobby" id="hobby2" value="독서" ><label for="hobby2">독서</label><br />
						<input type="checkbox" name="hobby" id="hobby3" value="게임" ><label for="hobby3">게임</label>
						<input type="checkbox" name="hobby" id="hobby4" value="여행" ><label for="hobby4">여행</label><br />
						

					</td>
				</tr>
			</table>
			<input type="button" value="정보수정"/>
			<input type="button" value="탈퇴"/>
		</form>
	</section>
<%@ include file="/views/common/footer.jsp" %>