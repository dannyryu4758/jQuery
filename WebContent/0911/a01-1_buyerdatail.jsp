<%@page import="java.util.ArrayList"%>
<%@page import="kr.or.ddit.buyer.vo.BuyerVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	BuyerVO buyerVO = (BuyerVO) request.getAttribute("buyerVO");
%>

{
	"id"	   : "<%=buyerVO.getBuyer_id() %>",
	"name" 	   : "<%=buyerVO.getBuyer_name() %>",
	"lgu" 	   : "<%=buyerVO.getBuyer_lgu() %>",
	"bank"     : "<%=buyerVO.getBuyer_bank() %>",
	"bankNo"   : "<%=buyerVO.getBuyer_bankno() %>",
	"bankName" : "<%=buyerVO.getBuyer_bankname() %>",
	"zip" 	   : "<%=buyerVO.getBuyer_zip() %>",
	"addr" 	   : "<%=buyerVO.getBuyer_add1() +" "+ buyerVO.getBuyer_add2() %>",
	"comtel"   : "<%=buyerVO.getBuyer_comtel() %>",
	"mail" 	   : "<%=buyerVO.getBuyer_mail() %>"
}

