package kr.or.ddit.lprod.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.lprod.service.ILprodService;
import kr.or.ddit.lprod.service.ILprodServiceImpl;
import kr.or.ddit.lprod.vo.LprodVO;

@WebServlet("/LprodList")
public class LprodList extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ILprodService service = ILprodServiceImpl.getInstance();
		
		List<LprodVO> list = service.selectAll();
		
		req.setAttribute("list", list);
		
		String path = "0910/a02-1_lprodlist.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	}
}
