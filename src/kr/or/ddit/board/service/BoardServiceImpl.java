package kr.or.ddit.board.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.vo.BoardVO;

public class BoardServiceImpl implements IBoardService{
	private BoardDaoImpl dao;
	private static BoardServiceImpl service;
	
	private BoardServiceImpl() {
		dao = BoardDaoImpl.getInstance();
	}
	
	public static BoardServiceImpl getInstance() {
		if(service==null) {
			service = new BoardServiceImpl();
		}
		return service;
	}
	
	@Override
	public List<BoardVO> selectBoard() {
		List<BoardVO> list = null;
		try {
			list = dao.selectBoard();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<BoardVO> selectByPage(Map<String, Object> map) {
		List<BoardVO> list = null;
		try {
			list = dao.selectByPage(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int countList() {
		int count = 0;
		try {
			count = dao.countList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

}
