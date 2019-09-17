package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;

@WebServlet("/checkId")
public class CheckIdServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//ajax 에서 
		String inputId = req.getParameter("id");
		IMemberService service = MemberServiceImpl.getInstance();
		String getId = service.selectById(inputId);
		req.setAttribute("getId", getId);
		req.setAttribute("inputId", inputId);
		
		String path = "member/checkId.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	}
}
