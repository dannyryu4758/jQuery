package kr.or.ddit.member.dao;

import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.member.vo.ZipVO;

public interface IMemberDao {
	
	public List<MemberVO> seletAll() throws SQLException;
	
	public String selectById(String mem_id) throws SQLException;
	
	public List<ZipVO> selectByDong(String dong) throws SQLException;
	
	public String insertMember(MemberVO mvo) throws SQLException;
	
}
