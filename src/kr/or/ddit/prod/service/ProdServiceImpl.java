package kr.or.ddit.prod.service;

import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.prod.dao.IProdDao;
import kr.or.ddit.prod.dao.ProdDaoImpl;
import kr.or.ddit.prod.vo.ProdVO;

public class ProdServiceImpl implements IProdService{

	private IProdDao dao;
	private static IProdService service;
	
	private ProdServiceImpl() {
		dao = ProdDaoImpl.getInstance();
	}
	
	public static IProdService getInstance() {
		if(service==null) {
			service = new ProdServiceImpl();
		}
		return service;
	}

	
	@Override
	public List<ProdVO> getByLguList(String lprod_gu) {
		List<ProdVO> list = null;
		try {
			list = dao.getByLguList(lprod_gu);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public ProdVO getDetail(String prod_id) {
		ProdVO vo = null;
		try {
			vo = dao.getDetail(prod_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}
}
