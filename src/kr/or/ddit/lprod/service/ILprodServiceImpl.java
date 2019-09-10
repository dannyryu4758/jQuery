package kr.or.ddit.lprod.service;

import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.lprod.dao.ILprodDao;
import kr.or.ddit.lprod.dao.ILprodDaoImpl;
import kr.or.ddit.lprod.vo.LprodVO;

public class ILprodServiceImpl implements ILprodService{
	private ILprodDao dao;
	private static ILprodServiceImpl service;
		
	private ILprodServiceImpl() {
		dao = ILprodDaoImpl.getInstance();
	}
	
	public static ILprodService getInstance() {
		if(service==null) {
			service = new ILprodServiceImpl();
		}
		return service;
	}

	@Override
	public List<LprodVO> selectAll() {
		List<LprodVO> list = null;
		try {
			list = dao.selectAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
