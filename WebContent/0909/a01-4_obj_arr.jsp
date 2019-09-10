<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	// 클라이언트 요청시 데이터를 전송 받아서 처리(CRUD) 후, 응답할 데이터를 생성
	// 데이터의 형태는 json형태의 Object 배열이다.
	// "키 " : "value"  * 키는 영문으로만 가능
%>

[
	{
		"name" : "홍길동",
		"id"   : "a001",
		"tel"  : "010-1234-7896",
		"addr" : "대전 중구 대흥동" 
	},
	
	{
		"name" : "개나리",
		"id"   : "a002",
		"tel"  : "010-5553-4885",
		"addr" : "대전 서구 내동" 
	},
	
	{
		"name" : "노오란",
		"id"   : "a003",
		"tel"  : "010-7565-4654",
		
		"addr" : "대전 서구 도마동" 
	},
	
	{
		"name" : "꽃그늘",
		"id"   : "a003",
		"tel"  : "010-5641-1111",
		"addr" : "대전 서구 월평동" 
	}
]