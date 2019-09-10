package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

/**
 * Servlet implementation class MemberList
 */
@WebServlet("/MemberList")
public class MemberList extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 클라이언트 요청시 데이터를 전송 받아서 처리(CRUD) 후, 응답할 데이터를 생성
		// 데이터의 형태는 json형태 : Object 배열
		
		// service 객체 얻어온다.
		IMemberService serivce = MemberServiceImpl.getInstance();
		
		// service 메소드 호출 - 결과 값 가져온다.
		List<MemberVO> list = serivce.seletAll();
		
		// list를 request 에 저장
		request.setAttribute("list", list);
		// jsp로 forward
		String path = "0910/a01_memberlist.jsp";
		request.getRequestDispatcher(path).forward(request, response);
	}

}
