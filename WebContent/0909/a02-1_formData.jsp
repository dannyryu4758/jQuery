<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");
	String id = request.getParameter("id");
	String name = request.getParameter("name");
	
	
%>
{
	"id" : "<%=id %>님 환경합니다.",
	"name" : "당신의 이름은 <%=name %> 입니다."
}
