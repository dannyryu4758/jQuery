package kr.or.ddit.prod.dao;

import java.sql.SQLException;
import java.util.List;

import javax.xml.ws.Provider;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.ibatis.config.SqlMapClientFactory;
import kr.or.ddit.prod.vo.ProdVO;

public class ProdDaoImpl implements IProdDao{

	private SqlMapClient smc;
	private static IProdDao dao;
	
	private ProdDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IProdDao getInstance() {
		if(dao==null) {
			dao = new ProdDaoImpl();
		}
		return dao;
	}
	
	
	@Override
	public List<ProdVO> getByLguList(String lprod_gu) throws SQLException {
		List<ProdVO> list = null;
		list = smc.queryForList("prod.getByLguList", lprod_gu);
		return list;
	}
	@Override
	public ProdVO getDetail(String prod_id) throws SQLException {
		ProdVO vo = null;
		vo = (ProdVO) smc.queryForObject("prod.getDetail", prod_id);
		return vo;
	}
}
