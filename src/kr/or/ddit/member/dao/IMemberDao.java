package kr.or.ddit.member.dao;

import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.member.vo.MemberVO;

public interface IMemberDao {
	
	public List<MemberVO> seletAll() throws SQLException;
}
