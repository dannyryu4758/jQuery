<%@page import="kr.or.ddit.board.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
[
	<%
		int i= 0;
		List<BoardVO> boardList = (List<BoardVO>) request.getAttribute("boardList");
		for(BoardVO tmp :boardList) {
			if(i>0) out.print(",");
			%>
			{
				"seq"	    : "<%=tmp.getSeq()%>",
				"subject"   : "<%=tmp.getSubject()%>",
				"writer"    : "<%=tmp.getWriter()%>",
				"mail"      : "<%=tmp.getMail()%>",
				"content"   : "<%=tmp.getContent()%>",
				"hit"	    : "<%=tmp.getHit()%>",
				"date"	    : "<%=tmp.getWdate()%>"
			}
			<%
			i++;
		}
	%>
]