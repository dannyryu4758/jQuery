package kr.or.ddit.board.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.ReplyVO;

public interface IBoardDao {
	
	public List<BoardVO> selectBoard() throws SQLException;
	public List<BoardVO> selectByPage(Map<String, Object> map) throws SQLException;
	public int countList() throws SQLException;
	public int insertBoard(BoardVO bvo) throws SQLException;
	public int insertReply(ReplyVO rvo) throws SQLException;
	public List<ReplyVO> replyList(int bonum) throws SQLException;
	public int updateReply(ReplyVO rvo) throws SQLException;
	public int deleteReply(int renum) throws SQLException;
	public int deleteBoard(int seq) throws SQLException;
	public int hitUdateBoard(int seq) throws SQLException;
	public int selectHit(int seq) throws SQLException;
	public int updateBoard(BoardVO board) throws SQLException;
}
