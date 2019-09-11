package kr.or.ddit.buyer.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.buyer.service.IBuyerService;
import kr.or.ddit.buyer.service.IBuyerServiceImpl;
import kr.or.ddit.buyer.vo.BuyerVO;

@WebServlet("/BuyerServlet")
public class BuyerServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 전체 리스트 가져오기를 수행
		
		//1. service 객체 얻어오기
		IBuyerService service = IBuyerServiceImpl.getInstance();
		//2. 메소드 호출 : 리턴값 받기
		List<BuyerVO> buyerList = service.selectNameList();
		//3.리턴결과값 저장하기(request)
		request.setAttribute("buyerList", buyerList);
		//4.jsp로 forword
		String path = "0911/a01-1_buyerList.jsp";
		request.getRequestDispatcher(path).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//0. parameter 받기
		String buyer_id = request.getParameter("id");
		//1. service 객체 얻어오기
		IBuyerService service = IBuyerServiceImpl.getInstance();
		//2. 메소드 호출 : 리턴값 받기
		BuyerVO buyerVO = service.buyerDetail(buyer_id);
		//3.리턴결과값 저장하기(request)
		request.setAttribute("buyerVO", buyerVO);
		//4.jsp로 forword
		String path = "0911/a01-1_buyerdatail.jsp";
		request.getRequestDispatcher(path).forward(request, response);
	}

}
