package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.tribes.MembershipService;
import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

@WebServlet("/InsertMember")
public class InsertMemberServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		MemberVO mvo = new MemberVO();
		try {
			BeanUtils.populate(mvo, request.getParameterMap());  // input 태그의 name 값과 VO 값이 동일해야 한다.
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		IMemberService service = MemberServiceImpl.getInstance(); 
		String result_id = service.insertMember(mvo);
		request.setAttribute("result_id", result_id);
		
		String path = "member/insertMember.jsp";
		request.getRequestDispatcher(path).forward(request, response);
	}

}
