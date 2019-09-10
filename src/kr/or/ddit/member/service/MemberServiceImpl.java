package kr.or.ddit.member.service;

import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.member.vo.MemberVO;

public class MemberServiceImpl implements IMemberService{
	
	private IMemberDao dao;
	private static MemberServiceImpl service;
	
	private MemberServiceImpl() {
		dao = MemberDaoImpl.getInstance();
	}
	
	public static IMemberService getInstance() {
		if(service==null) {
			service = new MemberServiceImpl();
		}
		return service;
	}
	
	@Override
	public List<MemberVO> seletAll() {
		List<MemberVO> list = null;
		try {
			list = dao.seletAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
