package kr.or.ddit.lprod.service;

import kr.or.ddit.lprod.dao.ILprodDao;
import kr.or.ddit.lprod.dao.ILprodDaoImpl;

public class ILprodServiceImpl {
	private static ILprodDao dao;
	private ILprodServiceImpl service;
	
	public ILprodServiceImpl() {
		dao = ILprodDaoImpl.getInstance();
	}
}
