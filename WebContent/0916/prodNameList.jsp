<%@page import="kr.or.ddit.prod.vo.ProdVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

[
	<%
		List<ProdVO> prodList = (List) request.getAttribute("prodList");
		int i =0;
		for(ProdVO tmp : prodList){
			if(i>0) out.print(",");
			%>
			{
				"prod_id" : "<%=tmp.getProd_id() %>",
				"prod_name" : "<%=tmp.getProd_name() %>"
			}
			<%
			i++;
		}
	%>

]