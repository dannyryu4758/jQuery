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
import kr.or.ddit.member.vo.ZipVO;

@WebServlet("/SelectDong")
public class SelectDongServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String dong = req.getParameter("dong");
		IMemberService service = MemberServiceImpl.getInstance();
		List<ZipVO> zipList =  service.selectByDong(dong);
		req.setAttribute("zipList", zipList);
		
		String path ="member/selectDong.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	}
}
