<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String getId = (String) request.getAttribute("getId");
	String inputId = (String) request.getAttribute("inputId");
	
	if(getId==null){
		// 가입 가능한 아이디
		%>
		{ 
			"result" : "<%=inputId %>는 사용 가능한 아이디 입니다."
		}
		<%
	}else{
		%>
		{ 
			"result" : "<%=inputId %>는 사용 불가능한 아이디 입니다."
		}
		<%
	}
%>