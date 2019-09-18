<%@page import="kr.or.ddit.prod.vo.ProdVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<ProdVO> prodList = (List) request.getAttribute("prodList");
	if(prodList.size() > 0){
%>
		<%-- res.sw		res.data[i].id		res.data[i].name --%>
		{
			"sw" : "OK",
			"data" : [
			<%
				for(int i=0 ; i <prodList.size() ; i++){
					ProdVO vo = prodList.get(i);
					if(i>0) out.print(",");
			%>
					{
						"prod_id" : "<%=vo.getProd_id() %>",
						"prod_name" : "<%=vo.getProd_name() %>"
					}
				<%
				}
				%>
			]
		}		
		<%
	} else {
		%>
		{
			"sw" : "NO"
		}
		<%
	}
%>