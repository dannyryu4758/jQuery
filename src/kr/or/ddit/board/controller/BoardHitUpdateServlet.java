package kr.or.ddit.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;

@WebServlet("/hitUdateBoard")
public class BoardHitUpdateServlet extends HttpServlet{
	IBoardService service = BoardServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int seq = Integer.parseInt(req.getParameter("seq"));
		int hit = service.hitUdateBoard(seq);
		req.setAttribute("seq", hit);
		String path = "/board/getHit.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	}
}
