package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;

@WebServlet("/BoardServlet")
public class BoardServlet extends HttpServlet {
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int cpage = Integer.parseInt(request.getParameter("page"));
		
		IBoardService service = BoardServiceImpl.getInstance();
		
		int perlist = 5; // 페이지당 게시물이 몇개씩 출력할것인지
		int perpage = 3; // 페이지 네이션이 3개씩 보이도록 주는 변수 1,2,3 / 4,5,6 ....
		// 전체 글개수 구하기
		int totalcount = service.countList();
		// 전체 페이지수 구하기
		int totalpage = (int) (Math.ceil(totalcount / (double)perlist));
		// 시작페이지와 끝 페이지 구하기
		
		// 시작게시판번호 : start 값과 마지막 게시판번호 : end 값 구하기
		
		int start = 1;
		int end = 5;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end",end);
		List<BoardVO> boardList = null;
		boardList = service.selectByPage(map);
		request.setAttribute("boardList", boardList);
		
		String path = "/board/list.jsp";
		request.getRequestDispatcher(path).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
