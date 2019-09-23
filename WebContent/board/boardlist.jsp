<%@page import="kr.or.ddit.board.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
  <script src="../js/board.js"></script>
<script type="text/javascript">
$(function(){
	readServer(1);
	
	// 페이지 번호를 클릭하면 
	$('#btngroup').on('click', '.paging', function(){
		currentpage = $(this).text();
		readServer(currentpage);
	})
	
	// 다음버튼 클릭하면 
	$('#btngroup').on('click', '.next a', function(){
		currentpage = parseInt($('.paging:last').text()) + 1;
		readServer(currentpage);
	})
	
	// 이전버튼 클릭하면 
	$('#btngroup').on('click', '.previous a', function(){
		currentpage = parseInt($('.paging:first').text()) - 1;
		readServer(currentpage);
	})
	
	// 글쓰기에서 데이터 입력 후 확인 버튼 클릭할때
	$('#wok').bind('click', function(){
		indatas = $('#wform').serialize();
		$.ajax({
			url : "/jqpro/BoardWriter",
			type : "post",
			data : indatas,
			dataType : "json",
			success : function(res){
				readServer(1);
			},
			error: function(xhr){
				console.log("상태 : " + xhr.status);
			}
			
		})
	})
})
</script>
<style type="text/css">
	#btngroup{
		text-align : center;
		margin-left : 30%;
	}
	.pager{
		float : left;
	}
	.next, ,.previous{
		margin-left : 20px;
	}
	#divwrite{
		text-align: right;
	}
</style>
</head>
<body>
	<h2>Accordion Example</h2>
	<br>
	<div id="btnwrite">
		<button type="button" name="write" class="action btn btn-success" 
					data-toggle="modal" data-target="#writeModal">글쓰기</button>
	</div>
	<br><br>
	
	<!-- 글 목록 출력 -->
	<div id="accordionList">
	</div>
	<br>
	<!-- 페이지 출력 -->
	<div id="btngroup"></div>
	
	
<div class="modal fade" id="writeModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">새글 작성</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form id="wform" name="wform">
          <div class="form-group">
            <label for="recipient-name" class="col-form-label">재목:</label>
            <input type="text" class="form-control" name="subject" id="subject">
          </div>
          <div class="form-group">
            <label for="recipient-name" class="col-form-label">작성자:</label>
            <input type="text" class="form-control" name="writer" id="writer">
          </div>
          <div class="form-group">
            <label for="recipient-name" class="col-form-label">비밀번호:</label>
            <input type="password" class="form-control" name="password" id="password">
          </div>
          <div class="form-group">
            <label for="recipient-name" class="col-form-label">이메일:</label>
            <input type="email" class="form-control" name="mail" id="mail">
          </div>
          <div class="form-group">
            <label for="message-text" class="col-form-label">내용:</label>
            <textarea class="form-control" name="content" id="content"></textarea>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" id="wok" name="wok">확인</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
	
</body>
</html>
