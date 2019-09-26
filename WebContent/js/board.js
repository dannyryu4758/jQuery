/**
 * 
 */

var currentpage = 1;
readServer = function(cpage){
	$.ajax({
		url : "/jqpro/BoardServlet",
		data : {'page' : cpage},
		dataType : 'json',
		success : function(result){
			code="<div class='panel-group' id='accordion'>";
			$.each(result.data, function(i,v){
				code +=' <div class="panel panel-default">';
				code +='  <div class="panel-heading">';
				code +='    <h4 class="panel-title">';
				code +='      <a idx="'+v.seq+'" name="list" class="action" data-toggle="collapse" data-parent="#accordion" href="#collapse' +v.seq + '"><span id="subjectsp">' + v.subject + '<span></a>';
				code +='</h4>';
				code +='   </div>';
				code +='   <div id="collapse' + v.seq + '" class="panel-collapse collapse">';
				code +='    <div class="panel-body pbody">';
				code +='      <p style="width:80%; float:left;">';
				code +='         작성자 :<span id="writersp">' + v.writer + '</span>';
				code +='       &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp;   ';      
				code +='        &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;';
				code +='         이메일 : <span id="mailsp">' + v.mail + '</span>';
				code +='       &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp;  ';       
				code +='        &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;';
				code +='          작성일 : <span id="date">' + v.date + '</span>';
				code +='       &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp;   ';      
				code +='        &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;';
				code +='          조회수 : <span id="hitsp">' + v.hit + '</span>';
				code +='       &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp;  ';       
				code +='        &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;';
				code +='      </p>';
			    code +='      <p  style="text-align:right;">';
			    code +='        <button idx="'+v.seq+'" type="button" name="modify" class="action">수정</button>';
			    code +='        <button idx="'+v.seq+'" type="button" name="delete" class="action">삭제</button>';
			    code +='      </p>';
			    code +='      <hr>';
			    code +='      <p style="width:80%; float:left;">';
			    code +=     '<span id="contentsp">' + v.content + '</span>' ;
			    code +='       </p>';
			    code +='       <textarea class="area"  cols="80">댓글쓰기댓글쓰기</textarea>';
			    code +='      <button idx="'+v.seq+'" style="height:45px; width:50px;vertical-align:top;" type="button" name="reply" class="action">등록</button>';
			    code +='    </div>';
			    code +='  </div>';
			    code +=' </div>';
			})
			code+="</div>";
//			$('#accordionList').html(code);
			$('#accordionList').empty().append(code);
			
			// 페이지 번호 출력
			// 이전 버튼
			$('#btngroup').empty();
			pager ="";
			if(result.spage > 1){
				pager += "<ul class='pager'>";
				pager += "<li class='previous'><a href='#'>이전</a></li>";
				pager += "</ul>";
				$(pager).appendTo('#btngroup');
			}
			
			pager = "<ul class='pagination pager'>";
			for(i=result.spage ; i <= result.epage; i++){
				if(currentpage == i){
					pager += "<li class='active'>";
					pager += "<a class='paging' href='#'>";
					pager += i + "</a></li>"; 
				} else {
					pager += "<li>";
					pager += "<a class='paging' href='#'>";
					pager += i + "</a></li>";
				}
			}
			pager += "</ul>";
			$('#btngroup').append(pager);
			
			// 다음버튼
			if(result.epage < result.tpage){
				pager = "<ul class='pager'>";
				pager += "<li class='next'><a href='#'>다음</a></li>";
				pager += "</ul>";
				$(pager).appendTo('#btngroup');
			}
		},
		error : function(xhr){
			alert("상태 " + xhr.status);
		}
	})
}

writeServer = function(){
	// 입력한 모든 값을 가져오기
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
}
replyServer = function(bb){
	$.ajax({
		url :"/jqpro/ReplySave",
		type : 'post',
		data : {
			'name' : rname,
			'cont' : rtext,
			'bonum' : idx
		},
		dataType : 'json',
		success : function(res){
			console.log("댓글" + res.sw);
			replyListServer(bb);
		},
		error : function(xhr){
			alert("상태 : " + xhr.status);
		}
	})
}

replyListServer = function(a){
	// a: 제목줄의 a태그 또는 등록버튼
	
	$(a).parents('.panel').find('.pbody').find('.rep').remove();
	
	$.ajax({
		url : "/jqpro/ReplyList",
		method : "post",
		data : {"bonum" : bonum},
		dataType : "json",
		success : function(resp) {
			repl = "";
			$.each(resp, function(i, v){
				repl +='    <div class="panel-body rep">';
				repl +='      <p style="width:80%; float:left;">';
				repl +=' 	  <span> ' + v.name;
				repl +='       &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp;   ';      
				repl += 		v.redate;
				repl +='       &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp;   ';
				repl +='	  </span><br><br>';
				repl +='	  <span class="cont">' + v.cont + '</span>';
				repl +='      </p>';
				repl +='      <p style="text-align:right;">';
			    repl +='        <button idx="'+v.renum+'" type="button" name="r_modify" class="action">댓글수정</button>';
			    repl +='        <button idx="'+v.renum+'" type="button" name="r_delete" class="action">댓글삭제</button>';
			    repl +='      </p>';
			    repl +='	 </div> ';
			})
			$(a).parents('.panel').find('.pbody').append(repl);
		},
		error : function(errorResp) {
			console.log(errorResp.status);
		}
	});
}

updateReplyServer = function(){
	$.ajax({
		url : "/jqpro/updateReply",
		type : "post",
		data : {"renum" : renum , "cont" :modifyCont},
		dataType : "json",
		success : function(res){
			alert(res.sw);
		},
		error: function(xhr){
			console.log("상태 : " + xhr.status);
		}
	})
}

deleteReplyServer = function(th){
	$.ajax({
		url : "/jqpro/deleteReply",
		type : "get",
		data : {"renum" : renum},
		dataType : "json",
		success : function(res){
			alert(res.sw);
			$(th).parents(".rep").remove();
		},
		error: function(xhr){
			console.log("상태 : " + xhr.status);
		}
	})
}

deleteBoardServer = function(th){
	$.ajax({
		url : "/jqpro/deleteBoard",
		type : "post",
		data : {"seq" : idx},
		dataType : "json",
		success : function(res){
			alert(res.sw);
			$(th).parents(".panel-default").remove();
		},
		error: function(xhr){
			console.log("상태 : " + xhr.status);
		}
	})
}

readHitServer = function(th){
	var hipsp = $(th).parents(".panel-default").find("#hitsp");
	$.ajax({
		url : "/jqpro/hitUdateBoard",
		method : "get",
		data : {"seq" : bonum},
		dataType : "json",
		success : function(resp) {
			console.log(resp.sw);
			$(hipsp).html(resp.hit);
		},
		error : function(errorResp) {
			console.log(errorResp.status);
		}
	});
}


updateBoardServer = function(th){
	indatas = $('#wform').serialize();
	$.ajax({
		url : "/jqpro/updateBoardServlet",
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
}
