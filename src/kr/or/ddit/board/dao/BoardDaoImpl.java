package kr.or.ddit.board.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.ReplyVO;
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

	@Override
	public int insertBoard(BoardVO bvo) throws SQLException {
		int result = 0;
		result = (int) smc.insert("board.insertBoard", bvo);
		return result;
	}

	@Override
	public int insertReply(ReplyVO rvo) throws SQLException {
		int result = 0;
		result = (int) smc.insert("board.insertReply", rvo);
		return result;
	}

	@Override
	public List<ReplyVO> replyList(int bonum) throws SQLException {
		List<ReplyVO> list = null;
		list = smc.queryForList("board.replyList", bonum);
		return list;
	}

	@Override
	public int updateReply(ReplyVO rvo) throws SQLException {
		return smc.update("board.updateReply", rvo);
	}

	@Override
	public int deleteReply(int renum) throws SQLException {
		return smc.delete("board.deleteReply", renum);
	}

	@Override
	public int deleteBoard(int seq) throws SQLException {
		return smc.delete("board.deleteBoard", seq);
	}

	@Override
	public int hitUdateBoard(int seq) throws SQLException {
		return smc.update("board.hitUdateBoard", seq);
	}

	@Override
	public int selectHit(int seq) throws SQLException {
		return (int) smc.queryForObject("board.selectHit", seq);
	}

	@Override
	public int updateBoard(BoardVO board) throws SQLException {
		return smc.update("board.updateBoard", board);
	}

}
