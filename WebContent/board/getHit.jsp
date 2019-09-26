<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int hit = (Integer) request.getAttribute("seq");
	if(hit != 0 ){
		%>
		{
			"sw" : "수정성공",
			"hit" : <%=hit %>
			
		}
		<%
	} else {
		%>
		{
			"sw" : "수정실패"
		}
		<%
	}
%>