package kr.or.ddit.member.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.ibatis.config.SqlMapClientFactory;
import kr.or.ddit.member.vo.MemberVO;

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
	
}
