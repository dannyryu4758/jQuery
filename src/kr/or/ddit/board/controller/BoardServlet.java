package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
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
		int perpage = 2; // 페이지 네이션이 3개씩 보이도록 주는 변수 1,2,3 / 4,5,6 ....
		
		// 전체 글개수 구하기
		int totalcount = service.countList();
		
	      //전체 페이지 수 구하기
//      int totalPage = totalCount/ perlist;
//      if(totalPage%perlist >= 1) totalPage++;
		int totalpage = (int) Math.ceil(totalcount/(double)perlist);
		
		// 시작페이지와 끝 페이지 구하기
		int startpage = ( (cpage-1) / perpage * perpage ) +1;
		int endpage = startpage + perpage -1;
		if(endpage > totalpage) endpage = totalpage; 
			
		// 시작게시판번호 : start 값과 마지막 게시판번호 : end 값 구하기
		int start = (cpage - 1) * perlist + 1 ;
		int end = start + perlist -1;
		if(end > totalcount) end = totalcount;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end",end);
		List<BoardVO> list = null;
		list = service.selectByPage(map);
	    //3. 메서드 호출해서 결과값 req에 저장
	    request.setAttribute("list", list);
	    request.setAttribute("totalpage", totalpage);
	    request.setAttribute("startpage", startpage);
	    request.setAttribute("endpage", endpage);
	    //4.jsp로 이동
//	    RequestDispatcher disp = 
//	           request.getRequestDispatcher("board/list.jsp");
	    RequestDispatcher disp = request.getRequestDispatcher("board/listpage.jsp");
	    disp.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
