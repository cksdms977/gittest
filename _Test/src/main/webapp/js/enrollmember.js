
const id_duplicate = () => {
		const userId = $("#userId_").val();
		if(userId.length >= 4){
		open("<%=request.getContextPath()%>/member/idDuplicate.do?userId="+userId,"_blank", "width=300 height= 200");
		
		}else{
			alert("4글자이상 입력하세요!!")
		}
		}