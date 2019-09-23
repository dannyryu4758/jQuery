package kr.or.ddit.board.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.board.vo.BoardVO;

public interface IBoardDao {
	
	public List<BoardVO> selectBoard() throws SQLException;
	public List<BoardVO> selectByPage(Map<String, Object> map) throws SQLException;
	public int countList() throws SQLException;
	public int insertBoard(BoardVO bvo) throws SQLException;
}
