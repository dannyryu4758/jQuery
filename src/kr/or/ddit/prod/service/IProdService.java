package kr.or.ddit.prod.service;

import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.prod.vo.ProdVO;

public interface IProdService {

	public List<ProdVO> getByLguList(String lprod_gu);
	
	public ProdVO getDetail(String prod_id);
}
