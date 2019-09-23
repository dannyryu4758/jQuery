package kr.or.ddit.board.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.board.vo.BoardVO;

public interface IBoardService {

	public List<BoardVO> selectBoard();
	public List<BoardVO> selectByPage(Map<String, Object> map);
	public int countList();
	public int insertBoard(BoardVO bvo);
	
}
