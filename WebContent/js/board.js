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
				code +='      <a idx="'+v.seq+'" name="list" class="action" data-toggle="collapse" data-parent="#accordion" href="#collapse' +v.seq + '">' + v.subject + '</a>';
				code +='</h4>';
				code +='   </div>';
				code +='   <div id="collapse' + v.seq + '" class="panel-collapse collapse">';
				code +='    <div class="panel-body pbody">';
				code +='      <p style="width:80%; float:left;">';
				code +='         작성자 :' + v.writer;
				code +='       &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp;   ';      
				code +='        &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;';
				code +='         이메일 :  ' + v.mail;
				code +='       &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp;  ';       
				code +='        &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;';
				code +='          작성일 :' + v.date;
				code +='       &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp;   ';      
				code +='        &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;';
				code +='          조회수 :' + v.hit;
				code +='       &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp;  ';       
				code +='        &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;';
				code +='      </p>';
			    code +='      <p  style="text-align:right;">';
			    code +='        <button idx="'+v.seq+'" type="button" name="modify" class="action">수정</button>';
			    code +='        <button idx="'+v.seq+'" type="button" name="delete" class="action">삭제</button>';
			    code +='      </p>';
			    code +='      <hr>';
			    code +='      <p style="width:80%; float:left;">';
			    code +=     v.content ;
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
replyServer = function(){
	// 입력한 내용
	
	// 이름 
	
	
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
		},
		error : function(xhr){
			alert("상태 : " + xhr.status);
		}
	})
}

replyListServer = function(a){
	
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