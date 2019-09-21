package kr.or.ddit.member.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

	@Override
	public String[] selectSido() {
		List<ZipVO> list = null;
		String[] str = null;
		try {
			list = dao.selectSido();
			str = new String[list.size()];
			int size=0;
			for(ZipVO temp : list){
				str[size++] = temp.getSido();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return str;
	}

	@Override
	public List<ZipVO> selectBySido(String sido) {
		List<ZipVO> list = null;
		try {
			list = dao.selectBySido(sido);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<ZipVO> selectGugun(ZipVO zvo) {
		List<ZipVO> list = null;
		try {
			list = dao.selectGugun(zvo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<ZipVO> selectByGugun(ZipVO zvo) {
		List<ZipVO> list = null;
		try {
			list = dao.selectByGugun(zvo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<ZipVO> selectDong(ZipVO zvo) {
		List<ZipVO> list = null;
		List<ZipVO> tmp = new ArrayList<>();
		try {
			list = dao.selectDong(zvo);
			for(int i=0 ; i<list.size(); i++) {
				tmp = list.parallelStream().distinct().collect(Collectors.toList());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tmp;
	}

	@Override
	public List<ZipVO> selectByDong2(ZipVO zvo) {
		List<ZipVO> list = null;
		try {
			list = dao.selectByDong2(zvo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
