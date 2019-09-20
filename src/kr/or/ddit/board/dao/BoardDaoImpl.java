package kr.or.ddit.board.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.ibatis.config.SqlMapClientFactory;

public class BoardDaoImpl implements IBoardDao{
	
	private SqlMapClient smc;
	private static BoardDaoImpl dao;
	
	private BoardDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static BoardDaoImpl getInstance() {
		if(dao==null) {
			dao = new BoardDaoImpl();
		}
		return dao;
	}

	@Override
	public List<BoardVO> selectBoard() throws SQLException {
		return smc.queryForList("board.selectBoard");
	}

	@Override
	public List<BoardVO> selectByPage(Map<String, Object> map) throws SQLException {
		return smc.queryForList("board.selectByPage", map);
	}

	@Override
	public int countList() throws SQLException {
		int count = 0;
		count = (int) smc.queryForObject("board.countList");
		return count;
	}

}
