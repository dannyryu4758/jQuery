package kr.or.ddit.member.service;

import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.member.vo.ZipVO;

public interface IMemberService {
	
	public List<MemberVO> seletAll();
	
	public String selectById(String mem_id);
	
	public List<ZipVO> selectByDong(String dong);
	
	public String insertMember(MemberVO mvo);
	
	public String[] selectSido()  ;

	public List<ZipVO> selectBySido(String sido)  ;
	
	public List<ZipVO> selectGugun(ZipVO zvo)  ;
	
	public List<ZipVO> selectByGugun(ZipVO zvo)  ;
	
	public List<ZipVO> selectDong(ZipVO zvo)  ;
	
	public List<ZipVO> selectByDong2(ZipVO zvo)  ;
}
