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
		writeServer();
		$('#writeModal').modal('hide');
	})
	// 모달창이 닫힐 떄 입력내용을 지운다.
	$('#writeModal').on('hide.bs.modal', function(){
		$('.txt').val("");
	})
	
	// 수정, 삭제, 등록 버튼 클릭할 때 
	$('#accordionList').on('click', '.action' , function(){
		name = $(this).attr('name');
		idx = $(this).attr('idx');
		if(name == "modify"){
			alert(idx + "번호의 글을 수정합니다.");
		} else if(name=="delete") {
			alert(idx + "번호의 글을 삭제합니다.");
			deleteBoardServer(this);
		} else if(name=="reply"){
// 			alert(idx + "번호의 글에 댓글을 답니다.");
			//입력한 내용가져오기
			rtext = $(this).parent().find('.area').val();
			rname = "qwer1234";
			//이름 작성
			replyServer(this); // 댓글 저장
			bonum = idx;
			// 댓글 목록
// 			replyListServer(this);	// this == 등록버튼
		} else if(name=="list"){
			// 댓글 목록을 가져오기 위해서 - 글 번호 가져오기
			bonum = $(this).attr('idx');
			replyListServer(this); // this == 제목줄 a태그
		} else if(name == "r_modify"){
			
			//댓글 수정 클릭시 
			// modityForm이 열려 있는 걸 닫아야 한다.
			// body로 modigyForm 을 다시 이동 시켜 놓는다.
			if($("#modifyForm").css("display")!="none") {
				replyReset();
			}
			renum = idx;
			modifyCont = $(this).parents('.rep').find('.cont').html();
			modifyCont = modifyCont.replace("/<br>/g", "\n");
			
			$("#modifyForm #test").val(modifyCont);
			$(this).parents(".rep").find(".cont").empty().append($("#modifyForm"));
			
			$("#modifyForm").show();
			
		} else if(name == "r_delete"){
			renum = idx;
			deleteReplyServer(this);
		}
	})
	// 이미 modifyForm이 열려 있는 상태에서 다시 다른 modifyForm이 열릴떄
// 	modifyCont = "";
	replyReset = function(){
		modispan = $("#modifyForm").parent();
		$("body").append($("#modifyForm"));
		$("#modifyForm").hide();
		
		modispan.html(modifyCont.replace(/\n/g, "<br>"));
	}
	
	// 댓글 수정Form 에서 확인 버튼 클릭
	$("#btnOK").on("click", function(){
		modifyCont = $("#modifyForm #test").val();
		modispan = $("#modifyForm").parent();
		$("body").append($("#modifyForm"));
		$("#modifyForm").hide();
		modispan.html(modifyCont.replace(/\n/g, "<br>"));
		
		// DB 를 수정 : renum, cont 매개변수로 보내야함.
		updateReplyServer();
		
	})
	
	modifyCont = $("#modifyForm #test").val();		
	$("#btnCancel").on("click", function(){
		modispan = $("#modifyForm").parent();
		$("body").append($("#modifyForm"));
		$("#modifyForm").hide();
		modispan.html(modifyCont.replace(/\n/g, "<br>"));
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
	.rep{
		background: #ffd1d2;
		margin: 3px;
		padding: 5px;
	}
</style>
</head>
<body>
<!-- 댓글 수정을 위한 form -->
	<div id="modifyForm" style="display:none;">
		<textarea id="test" class="" rows="5" cols="30"></textarea>
		<input type="button" value="확인" id="btnOK">
		<input type="button" value="취소" id="btnCancel">
	</div>
	
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
            <input class="txt" type="text" class="form-control" name="subject" id="subject">
          </div>
          <div class="form-group">
            <label for="recipient-name" class="col-form-label">작성자:</label>
            <input class="txt" type="text" class="form-control" name="writer" id="writer">
          </div>
          <div class="form-group">
            <label for="recipient-name" class="col-form-label">비밀번호:</label>
            <input class="txt" type="password" class="form-control" name="password" id="password">
          </div>
          <div class="form-group">
            <label for="recipient-name" class="col-form-label">이메일:</label>
            <input class="txt" type="email" class="form-control" name="mail" id="mail">
          </div>
          <div class="form-group">
            <label for="message-text" class="col-form-label">내용:</label>
            <textarea class="form-control txt" name="content" id="content"></textarea>
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
