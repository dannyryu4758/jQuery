package kr.or.ddit.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;

@WebServlet("/deleteReply")
public class ReplyDeleteServlet extends HttpServlet{
	IBoardService service = BoardServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int renum = Integer.parseInt(req.getParameter("renum"));
		
		int result = service.deleteReply(renum);
		req.setAttribute("result", result);
		String path = "/board/delete.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	}
}
