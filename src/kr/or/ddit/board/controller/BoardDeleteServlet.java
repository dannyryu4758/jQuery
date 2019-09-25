package kr.or.ddit.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;

@WebServlet("/deleteBoard")
public class BoardDeleteServlet extends HttpServlet {
	IBoardService service = BoardServiceImpl.getInstance();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int boardNum = Integer.parseInt(req.getParameter("seq"));
		String viewName = null;
		if(boardNum > 0) {
			int result = service.deleteBoard(boardNum);
			req.setAttribute("result", result);
			viewName = "/board/delete.jsp";
		}
		req.getRequestDispatcher(viewName).forward(req, resp);
	}
}
