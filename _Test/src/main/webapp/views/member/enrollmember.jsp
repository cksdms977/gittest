<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<%@ include file="/views/common/header.jsp" %>
<section id=enroll-container>
        <h2>ȸ�� ���� ���� �Է�</h2>
        <form action="<%=request.getContextPath()%>/member/enrollendmember.do" method="post" onsubmit="" >
        <table>
			<tr>
				<th>���̵�</th>
				<td>
					<input type="text" placeholder="4�����̻�" name="userId" id="userId_" >
					<button type="button" name="idcheck" onclick="id_duplicate()" value="�ߺ�Ȯ��">�ߺ�Ȯ��</button>
				</td>
			</tr>
			<tr>
				<th>�н�����</th>
				<td>
					<input type="password" name="password" id="password_" class="pw"><br>
				</td>
			</tr>
			<tr>
				<th>�н�����Ȯ��</th>
				<td>	
					<input type="password" id="password_2" class="pw">
					<font id="checkpwd" size="2"></font>
				</td>
			</tr>  
			<tr>
				<th>�̸�</th>
				<td>	
				<input type="text"  name="userName" id="userName" ><br>
				</td>
			</tr>
			<tr>
				<th>����</th>
				<td>	
				<input type="number" name="age" id="age"><br>
				</td>
			</tr> 
			<tr>
				<th>�̸���</th>
				<td>	
					<input type="email" placeholder="abc@xyz.com" name="email" id="email"><br>
				</td>
			</tr>
			<tr>
				<th>�޴���</th>
				<td>	
					<input type="tel" placeholder="(-����)01012345678" name="phone" id="phone" maxlength="11" required><br>
				</td>
			</tr>
			<tr>
				<th>�ּ�</th>
				<td>	
					<input type="text" placeholder="" name="address" id="address"><br>
				</td>
			</tr>
			<tr>
				<th>���� </th>
				<td>
					<input type="radio" name="gender" id="gender0" value="M">
					<label for="gender0">��</label>
					<input type="radio" name="gender" id="gender1" value ="F">
					<label for="gender1">��</label>
				</td>
			</tr>
			<tr>
				<th>��� </th>
				<td>
					<input type="checkbox" name="hobby" id="hobby0" value="�"><label for="hobby0">�</label>
					<input type="checkbox" name="hobby" id="hobby1" value="���"><label for="hobby1">���</label>
					<input type="checkbox" name="hobby" id="hobby2" value="����"><label for="hobby2">����</label><br />
					<input type="checkbox" name="hobby" id="hobby3" value="����"><label for="hobby3">����</label>
					<input type="checkbox" name="hobby" id="hobby4" value="����"><label for="hobby4">����</label><br />
				</td>
			</tr>
		</table>
		<input type="submit" value="����" >
		<input type="reset" value="���">
        </form>
    </section>
    
    <script>
    const id_duplicate = () => {
		const userId = $("#userId_").val();
		if(userId.length >= 4){
		open("<%=request.getContextPath()%>/member/idDuplicate.do?userId="+userId,"_blank", "width=300 height= 200");
		
		}else{
			alert("4�����̻� �Է��ϼ���!!")
		}
		}
    $(".pw").keyup(function(){
		const password = $("#password_").val();
		const password2 = $("#password_2").val();
		if(password != "" || password2 != ""){
			if(password == password2) {
				$("#checkpwd").html('��ġ');
				$("#checkpwd").attr("color", "green");
			}else{
				$("#checkpwd").html('����ġ');
				$("#checkpwd").attr("color", "red");
			}
		}
	}); 
    
    
    
	</script>
<%@ include file="/views/common/footer.jsp" %>
  