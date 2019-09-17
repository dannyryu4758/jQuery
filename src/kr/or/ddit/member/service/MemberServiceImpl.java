package kr.or.ddit.member.service;

import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.member.vo.ZipVO;

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

	@Override
	public String selectById(String mem_id) {
		String id = null;
		try {
			id = dao.selectById(mem_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public List<ZipVO> selectByDong(String dong) {
		List<ZipVO> list = null;
		try {
			list = dao.selectByDong(dong);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public String insertMember(MemberVO mvo) {
		String mem_id = null;
		try {
			mem_id = dao.insertMember(mvo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mem_id;
	}
	
}
