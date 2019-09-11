<%@page import="kr.or.ddit.buyer.vo.BuyerVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
[
	<%
		List<BuyerVO> buyerList = (List) request.getAttribute("buyerList");
		int i = 0;
		for(BuyerVO vo : buyerList){
			if(i>0) out.print(",");
	%>
			{
				"id" : "<%=vo.getBuyer_id() %>",
				"name" : "<%=vo.getBuyer_name() %>"
			}
	<%
		i++;
		}
	%>
]