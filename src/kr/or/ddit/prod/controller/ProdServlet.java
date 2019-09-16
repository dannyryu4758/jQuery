package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.prod.dao.ProdDaoImpl;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.prod.vo.ProdVO;

/**
 * Servlet implementation class ProdServlet
 */
@WebServlet("/ProdServlet")
public class ProdServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 0. 클라이언트의 요청시 (ajax lprod_gu를 전달받고)
		String lprod_gu = (String) request.getParameter("lprod_gu");
		
		// 1. service 객체 얻어오기 
		IProdService service = ProdServiceImpl.getInstance();
		
		// 2. service 메소드 호출(getByLguList) - 리턴 결과값(list)
		List<ProdVO> prodList = service.getByLguList(lprod_gu);
		
		// 3. 결과 list 를 request 객체에 저장
		request.setAttribute("prodList", prodList);
		
		// 4. jsp 로 이동 prodNameList.jsp
		String path = "0916/prodNameList.jsp";
		request.getRequestDispatcher(path).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 0. 클라이언트의 요청시 (ajax) prod_id를 전달받고
		String prod_id = (String) request.getParameter("prod_id");
		
		// 1. service 객체 얻어오기 
		IProdService service = ProdServiceImpl.getInstance();
		
		// 2. service 메소드 호출(getDetail) - 리턴 결과값(ProdVO)
		ProdVO prodInfo = service.getDetail(prod_id);
		
		// 3. 결과 ProdVO 를 request 객체에 저장
		request.setAttribute("prodInfo", prodInfo);
		
		// 4. jsp 로 이동 prodDetail.jsp
		String path = "0916/prodDetail.jsp";
		request.getRequestDispatcher(path).forward(request, response);
	}

}
