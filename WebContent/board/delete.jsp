<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int result = (Integer) request.getAttribute("result");
	if(result != 0 ){
		%>
		{
			"sw" : "삭제 성공"
		}
		<%
	} else {
		%>
		{
			"sw" : "삭제 실패"
		}
		<%
	}
%>