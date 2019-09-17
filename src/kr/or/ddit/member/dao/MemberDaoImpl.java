package kr.or.ddit.member.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.ibatis.config.SqlMapClientFactory;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.member.vo.ZipVO;

public class MemberDaoImpl implements IMemberDao {
	
	private SqlMapClient smc;
	private static IMemberDao dao;
	
	private MemberDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IMemberDao getInstance() {
		if(dao==null) {
			dao = new MemberDaoImpl();
		}
		return dao;
	}
	
	@Override
	public List<MemberVO> seletAll() throws SQLException {
		return smc.queryForList("memberTest.getMemberAll");
	}

	@Override
	public String selectById(String mem_id) throws SQLException {
		return (String) smc.queryForObject("memberTest.selectById", mem_id);
	}

	@Override
	public List<ZipVO> selectByDong(String dong) throws SQLException {
		return smc.queryForList("memberTest.selectByDong", dong);
	}

	@Override
	public String insertMember(MemberVO mvo) throws SQLException {
		return (String) smc.insert("memberTest.insertMember");
	}

	
}
