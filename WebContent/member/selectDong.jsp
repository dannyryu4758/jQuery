<%@page import="kr.or.ddit.member.vo.ZipVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
[
	<%
		List<ZipVO> list = (List) request.getAttribute("zipList");
		for(int i=0 ; i<list.size() ; i++){
			ZipVO vo = list.get(i);
			String bunji = vo.getBunji();
			if(bunji==null){
				bunji = "";
			}
			if(i>0) out.print(",");
			%>
			{
				"zipcode" : "<%=list.get(i).getZipcode() %>",
				"add" : "<%=vo.getSido()%> <%=vo.getGugun()%> <%=vo.getDong()%>",
				"bunji" : "<%=bunji %>"
			}
			<%
		}
	%>
]
