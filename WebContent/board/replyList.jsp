<%@page import="kr.or.ddit.board.vo.ReplyVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

[
<%
	List<ReplyVO> replyList = (List<ReplyVO>) request.getAttribute("replyList");
	for(int i=0 ; i<replyList.size() ; i++){
		if(i>0) out.print(",");
		%>
		{
			"renum" : "<%=replyList.get(i).getRenum() %>",
			"bonum" : "<%=replyList.get(i).getBonum() %>",
			"name" : "<%=replyList.get(i).getName() %>",
			"cont" : "<%=replyList.get(i).getCont() %>",
			"redate" : "<%=replyList.get(i).getRedate().replaceAll("\r", "").replaceAll("\n", "<br>") %>"
		}
		<%
	}
%>
]