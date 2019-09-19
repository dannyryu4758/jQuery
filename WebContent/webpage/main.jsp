<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="kr">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
  <style>
    /* Remove the navbar's default margin-bottom and rounded borders */ 
    .navbar {
      margin-bottom: 0;
      border-radius: 0;
    }
    
    /* Set height of the grid so .sidenav can be 100% (adjust as needed) */
    .row.content {height: 450px}
    
    /* Set gray background color and 100% height */
    .sidenav {
      padding-top: 20px;
      background-color: #f1f1f1;
      height: 100%;
    }
    
    /* Set black background color, white text and some padding */
    footer {
      background-color: #555;
      color: white;
      padding: 15px;
    }
    
    /* On small screens, set height to 'auto' for sidenav and grid */
    @media screen and (max-width: 767px) {
      .sidenav {
        height: auto;
        padding: 15px;
      }
      .row.content {height:auto;} 
    }
  </style>
  <script type="text/javascript">
  	$(function(){
  		$('.dropdown').on('click', function(){
  			menu = $('.dropdown-toggle', this).text();
  			submenu = $('.dropdown-menu li a', this).text();
  			// alert(menu + "," + submenu);
  			// split(" ")
  			sub2 = $('.dropdown-menu li a', this).map(function(){ // 배열형식으로 가져오기
  				return $(this).text();  // 여기 this 는 a 태그
  			})
  			// alert(sub2[0]);
  			code = '<div class="list-group">';
	  		code += '<a href="#" class="list-group-item active disabled">' + menu + '</a>';
  			$.each(sub2, function(i){
	  			code += '<a href="#" class="list-group-item">' + sub2[i] + '</a>';
  			})
  			code += '</div>';
  			$('.sidenav').html(code);
  		})
  		
  		$('.sidenav').on('click', '.list-group-item', function(){	// 새로 만들어진 메뉴기 때문에 delegate 방식으로 이벤트 설정
  			className = $(this).attr('class');
  			if(className.match('active')) return;
//   			alert($(this).text());
				proc($(this).text());
  		})
  		
  		$('.dropdown-menu li a').on('click', function(){
//   			alert($(this).text());
				proc($(this).text());
  		})
  		
  		function proc(txt){
			if(txt == "공지게시판") $('.text-left #content').load('/jqpro/0916/a01_prod_lprod.html');
		}
  	})
  </script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<jsp:include page="content.jsp"></jsp:include>
	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>
