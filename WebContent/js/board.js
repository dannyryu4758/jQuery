/**
 * 
 */
readServer = function(cpage){
	$.ajax({
		url : "/jqpro/BoardServlet",
		data : {'page' : cpage},
		dataType : 'json',
		success : function(result){
			code="<div class='panel-group' id='accordion'>";
			$.each(result, function(i,v){
				code +=' <div class="panel panel-default">';
				code +='  <div class="panel-heading">';
				code +='    <h4 class="panel-title">';
				code +='      <a data-toggle="collapse" data-parent="#accordion" href="#collapse' +v.seq + '">' + v.subject + '</a>';
				code +='</h4>';
				code +='   </div>';
				code +='   <div id="collapse' + v.seq + '" class="panel-collapse collapse">';
				code +='    <div class="panel-body">';
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
			    code +='         <button type="button" name="modify" class="action">수정</button>';
			    code +='        <button type="button" name="delete" class="action">삭제</button>';
			    code +='      </p>';
			    code +='      <hr>';
			    code +='      <p style="width:80%; float:left;">';
			    code +=     v.content ;
			    code +='       </p>';
			    code +='       <textarea class="area"  cols="80">댓글쓰기댓글쓰기</textarea>';
			    code +='      <button style="height:45px; width:50px;vertical-align:top;" type="button" name="reply" class="action">등록</button>';
			    code +='    </div>';
			    code +='  </div>';
			    code +=' </div>';
			})
			code+="</div>";
//			$('#accordionList').empty().append(code);
			$('#accordionList').html(code);
		},
		error : function(xhr){
			alert("상태 " + xhr.status);
		}
	})
}