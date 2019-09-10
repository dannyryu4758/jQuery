package kr.or.ddit.lprod.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


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
//		return smc.queryForList("lprod.getLprodAll");
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<LprodVO> list = new ArrayList<>();
		try{
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			StringBuffer sql = new StringBuffer();
			sql.append("select lprod_id, lprod_gu, lprod_nm ");
			sql.append(" from lprod");
			rs = stmt.executeQuery(sql.toString());
//			ResultSetMetaData rsmd = rs.getMetaData();
//			int colCnt = rsmd.getColumnCount();
//			String[] headers = new String[colCnt];
//			for(int idx=1; idx<=colCnt; idx++){
//				headers[idx-1] = rsmd.getColumnName(idx);
//			}
			while(rs.next()){
				LprodVO vo = new LprodVO();
				list.add(vo);
				vo.setLprod_id(rs.getInt(1));
				vo.setLprod_gu(rs.getString("lprod_gu"));
				vo.setLprod_nm(rs.getString("lprod_nm"));
			}
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}finally{
			try{
				if(rs!=null) rs.close();
				if(stmt!=null)stmt.close();
				if(conn!=null)conn.close();
			}catch(SQLException e){
				throw new RuntimeException(e);
			}
		}
		return list;
	}
}
