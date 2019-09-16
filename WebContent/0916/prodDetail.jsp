<%@page import="kr.or.ddit.prod.vo.ProdVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<%
		ProdVO prodInfo = (ProdVO) request.getAttribute("prodInfo");
	%> 
		{
			"id" : "<%=prodInfo.getProd_id() %>",
			"name" : "<%=prodInfo.getProd_name() %>",
			"lgu" : "<%=prodInfo.getProd_lgu() %>",
			"buyer" : "<%=prodInfo.getProd_buyer() %>",
			"cost" : "<%=prodInfo.getProd_cost() %>",
			"price" : "<%=prodInfo.getProd_price() %>",
			"sale" : "<%=prodInfo.getProd_sale() %>",
			"outline" : "<%=prodInfo.getProd_outline() %>",
			"detail" : "<%=prodInfo.getProd_detail() %>"
		}
	
