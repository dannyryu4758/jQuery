package kr.or.ddit.buyer.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.buyer.vo.BuyerVO;
import kr.or.ddit.ibatis.config.SqlMapClientFactory;

public class IBuyerDaoImpl implements IBuyerDao{
	
	private SqlMapClient smc;
	private static IBuyerDao dao;
	
	private IBuyerDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IBuyerDao getInstance() {
		if(dao==null) {
			dao = new IBuyerDaoImpl();
		}
		return dao;
	}

	@Override
	public List<BuyerVO> selectNameList() throws SQLException {
		List<BuyerVO> list = null;
		list = smc.queryForList("buyer.selectNameList");
		return list;
	}

	@Override
	public BuyerVO buyerDetail(String buyer_id) throws SQLException {
		BuyerVO vo = null;
		vo = (BuyerVO) smc.queryForObject("buyer.buyerDetail", buyer_id);
		return vo;
	}
	
	
}
