<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String result_id = (String) request.getAttribute("result_id");
	if(result_id!=null){
	%>
		{
			"sw" : "<%=result_id %>님 가입성공!!!"
		}
	<%	
	} else {
	%>
		{
			"sw" : "가입실패...."
		}
	<%
	}
%>