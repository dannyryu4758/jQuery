package kr.or.ddit.board.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.ReplyVO;

public interface IBoardService {

	public List<BoardVO> selectBoard();
	public List<BoardVO> selectByPage(Map<String, Object> map);
	public int countList();
	public int insertBoard(BoardVO bvo);
	public int insertReply(ReplyVO rvo);
	public List<ReplyVO> replyList(int bonum);
	public int updateReply(ReplyVO rvo);
	public int deleteReply(int renum);
	public int deleteBoard(int seq); 
	public int hitUdateBoard(int seq);
	public int updateBoard(BoardVO board);
}
