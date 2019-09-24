package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.ReplyVO;

@WebServlet("/ReplyList")
public class ReplyListServlet extends HttpServlet {
	IBoardService service = BoardServiceImpl.getInstance();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int bonum = Integer.parseInt(request.getParameter("bonum"));
		List<ReplyVO> replyList = service.replyList(bonum);
		request.setAttribute("replyList", replyList);
		String path = "/board/replyList.jsp";
		request.getRequestDispatcher(path).forward(request, response);
	}

}
