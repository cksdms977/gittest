<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ajax파일 업로드시키기</title>
<script src="http://code.jquery.com/jquery-3.7.0.min.js"></script>
</head>
<body>
	<h2>ajax를 이용해서 파일 업로드 하기</h2>
	<input type="file" id="upFile" multiple>
	<button id="uploadBtn">업로드파일</button>
	<script>
		$("#uploadBtn").click(e=>{
			// js가 제공하는 FormData클래스를 이용함.
			const form = new FormData();
			// append로 서버에 전송할 데이터를 넣을 수 있음
			// key: value형식으로 저장
			const fileInput = $("#upFile");
			console.log(fileInput);
			$.each(fileInput[0].files, (i,f)=>{
				form.append("upfile"+i,f);
			});
			form.append("name", "유병승");
			$.ajax({
				url:"<%=request.getContextPath()%>/fileUpload",
				data:form,
				type:"post",
				processData:false, // 이렇게 해주면 그냥 form파트로 넘겨주는게 아니라 멀티파티form으로 넘겨줌 
				contentType:false, // 이렇게 해주면 그냥 form파트로 넘겨주는게 아니라 멀티파티form으로 넘겨줌 
				success:data=>{
					alert("업로드가 완료되었습니다.")
				},error:(r,m)=>{
					alert("업로드실패했습니다. "+m);
				},complet: ()=>{
					$("#upFile").val('');
				}
			});
		});
	</script>
	
	<h2>업로드 이미지 미리보기 기능</h2>
	<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSYcwc-vZE3ffRDEsJlbZgjE6NlhDEqedx_5w&usqp=CAU" width="100", height="100", id="profile">
	<input type="file" style="display:none" id="profileInput">
	<input type="file" id="upfile" multiple accept="images/*">
	<div id="uploadpreview"></div>
	<script>
	/* 	  $("#upfile").change(e=>{
			  const $upfile = $("#upfile"); 
			$("#upfiles").html('');
			const files = e.target.files;
			#.each(files, (i,f) =>{
				cosnt reader = new FileReader();
				reader.onload = e=>{
					const img = $("<img>").attr({
						src:e.target.result,
						"width":"100",
						"height":"100"
					});
					$("#uploadpreview").append(img);
				}
				reader.readAsDataURL(f);
			})
		})	 */ 
		$("#upfiles").change(e=>{
			$("#uploadpreview").html('');
			const files=e.target.files;
			$.each(files,(i,f)=>{
				const reader = new FileReader();
				reader.onload=e=>{
					const img =  $("<img>").attr({
						src:e.target.result,
						"width":"100",
						"height":"100"
					});
					$("#uploadpreview").append(img);
				}
				reader.readAsDataURL(f);
			})
			
		})
				
		
		
		$("#profile").click(e=>{
			$("#profileInput").click();
		});
		$("#profileInput").change(e=>{
			const reader = new FileReader();
			reader.onload = e=>{
				//e.target.result속성에 변경된 file(파일)이 나옴.
				$("#profile").attr("src", e.target.result);
			}
			reader.readAsDataURL(e.target.files[0]);
		});
	</script>
	
	<style>
		#profile{
			boarder-radius:100px;
			boarder:3px, solid yellow;
		}
	</style>
</body>
</html>