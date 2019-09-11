<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel ="stylesheet" href="../css/mystyle.css">
<!-- <meta name="viewport" content="width=device-width, initial-scale=1"> -->
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css"> -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script> -->
<script type="text/javascript">
$(function(){
	$.ajax({
		url : "<%=request.getContextPath()%>/BuyerServlet",
		type : 'get',
		dataType : 'json',
		success : function(result){
			code = "";
			$.each(result, function(i,v){
				code += "<p id='"+v.id+"'>"+v.name+"</p>"
			})
			$('#namelist').html(code);
		},
		error : function(xhr){
			alert("상태 : " + xhr.status);
		}
	})
	
	$('#namelist').on('click', 'p', function(){  // *********** delegate 방식 
		idvalue = $(this).attr('id');
		$.ajax({
			url : "<%=request.getContextPath()%>/BuyerServlet",
			type : "post",
			data : { "id" : idvalue }, // ******** 서블릿으로 데이터 보내기
			success: function(result){
				code = "<table border='1' class='table table-striped'>"
				code += "<tr><td>BUYER_ID</td>"
				code += "<td>"+result.id+"</td>"
				code += "<tr><td>BUYER_NAME</td>"
				code += "<td>"+result.name+"</td>"
				code += "<tr><td>BUYER_LGU</td>"
				code += "<td>"+result.lgu+"</td>"
				code += "<tr><td>BUYER_BANK</td>"
				code += "<td>"+result.bank+"</td>"
				code += "<tr><td>BUYER_BANK_NO</td>"
				code += "<td>"+result.bankNo+"</td>"
				code += "<tr><td>BUYER_BANK_NAME</td>"
				code += "<td>"+result.bankName+"</td>"
				code += "<tr><td>BUYER_ZIP</td>"
				code += "<td>"+result.zip+"</td>"
				code += "<tr><td>BUYER_MAIL</td>"
				code += "<td>"+result.mail+"</td>"
				code += "<tr><td>BUYER_ADDR</td>"
				code += "<td>"+result.addr+"</td>"
				code += "<tr><td>BUYER_COM_TEL</td>"
				code += "<td>"+result.comtel+"</td></tr>"
				code += "<table>";
				$('#detail').html(code);
			},
			error : function(xhr){
				
			},
			dataType: "json"
		})
	})
})
</script>
<style type="text/css">
	td, th{
		width : 200px;
		height : auto;
	}
	
	p:hover {
		background: lightgreen;
	}
</style>
</head>
<body>
	<div>
		<table class="table table-bordered">
			<tr>
				<th>거래처 이름</th>
				<th>거래처 상세정보</th>
			</tr>
			<tr>
				<td id="namelist"></td>
				<td id="detail"></td>
			</tr>
		</table>
	</div>
</body>
</html>