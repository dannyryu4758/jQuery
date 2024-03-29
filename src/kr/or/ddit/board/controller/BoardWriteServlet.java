package kr.or.ddit.board.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;

@WebServlet("/BoardWriter")
public class BoardWriteServlet extends HttpServlet{
	IBoardService service = BoardServiceImpl.getInstance();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BoardVO bvo = new BoardVO();
		try {
			BeanUtils.populate(bvo, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
		//ajax에서 보낸 ip를넣어야함
		bvo.setWip(req.getRemoteAddr());
		
		int seq = service.insertBoard(bvo);
		
		req.setAttribute("seq", seq);
		
	    RequestDispatcher disp = req.getRequestDispatcher("board/write.jsp");
	    disp.forward(req, resp);
	}
}
