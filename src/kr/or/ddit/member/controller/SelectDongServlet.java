package kr.or.ddit.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.ZipVO;
import kr.or.ddit.utils.MarshallingUtils;

@WebServlet("/SelectDong")
public class SelectDongServlet extends HttpServlet {
	IMemberService service = MemberServiceImpl.getInstance();
	MarshallingUtils marshalling = new MarshallingUtils();
	ZipVO zvo = new ZipVO();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json;charset=UTF-8");
		String json=null;
		String accept = req.getHeader("Accept");
		String sido = req.getParameter("sido");
		String gugun = req.getParameter("gugun");
		String deleteVO = req.getParameter("deleteVO");
		if(accept.toUpperCase().contains("JSON")) {
			String[] sidoNameList = service.selectSido();
			json = marshalling.marshalling(sidoNameList);
			
			if(StringUtils.isNotBlank(sido) && !"시도".equals(sido)) {
				zvo.setSido(sido);
				List<ZipVO> list = service.selectGugun(zvo);
				json = marshalling.marshalling(list);
			}
			
			if(StringUtils.isNotBlank(gugun) && !"구군".equals(gugun)) {
				zvo.setGugun(gugun);
				List<ZipVO> list = service.selectDong(zvo);
				json = marshalling.marshalling(list);
			}
				
			try(
				PrintWriter out = resp.getWriter();
			){
				out.print(json);
			}
		} else {
			String path = "/member/memberJoin.html";
			req.getRequestDispatcher(path).forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json;charset=UTF-8");
		String json=null;
		String path=null;
		String sido = req.getParameter("sido");
		String gugun = req.getParameter("gugun");
		String dong2 = req.getParameter("dong2");
		
		String accept = req.getHeader("Accept");
		resp.setContentType("application/json;charset=UTF-8");
		if(accept.toUpperCase().contains("JSON")) {
			String dong = req.getParameter("dong");
			if(StringUtils.isNotBlank(dong)) {
				List<ZipVO> zipList =  service.selectByDong(dong);
				req.setAttribute("zipList", zipList);
				path ="member/selectDong.jsp";
				req.getRequestDispatcher(path).forward(req, resp);
			}
			
			if(StringUtils.isNotBlank(sido) && !"시도".equals(sido)) {
				List<ZipVO> list = service.selectBySido(sido);
				json = marshalling.marshalling(list);
			}
			
			if(StringUtils.isNotBlank(gugun) && !"구군".equals(gugun)) {
				zvo.setGugun(gugun);
				List<ZipVO> list = service.selectByGugun(zvo);
				json = marshalling.marshalling(list);
			}
			
			if(StringUtils.isNotBlank(dong2) && !"동".equals(dong2)) {
				zvo.setDong(dong2);
				List<ZipVO> list = service.selectByDong2(zvo);
				json = marshalling.marshalling(list);
			}
			
			try(
					PrintWriter out = resp.getWriter();
				){
					out.print(json);
				}
		}
		
	}
}
