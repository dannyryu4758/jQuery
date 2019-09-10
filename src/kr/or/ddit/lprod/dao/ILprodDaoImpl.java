package kr.or.ddit.lprod.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.ibatis.config.SqlMapClientFactory;
import kr.or.ddit.lprod.vo.LprodVO;

public class ILprodDaoImpl implements ILprodDao {
	private SqlMapClient smc;
	private static ILprodDao dao;
	
	private ILprodDaoImpl () {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static ILprodDao getInstance() {
		if(dao == null) {
			dao = new ILprodDaoImpl();
		}
		return dao;
	}

	@Override
	public List<LprodVO> selectAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
