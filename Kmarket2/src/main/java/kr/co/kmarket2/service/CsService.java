package kr.co.kmarket2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.kmarket2.dao.CsDAO;
import kr.co.kmarket2.vo.Cate1VO;
import kr.co.kmarket2.vo.Cate2VO;
import kr.co.kmarket2.vo.CsFaqVO;
import kr.co.kmarket2.vo.CsNoticeVO;
import kr.co.kmarket2.vo.CsQnaVO;

@Service
public class CsService {

	@Autowired
	private CsDAO dao;
	
	// index
	public List<CsNoticeVO> selectIndexNotice() {
		return dao.selectIndexNotice();
	}
	
	public List<CsQnaVO> selectIndexQna(String cate1) {
		return dao.selectIndexQna(cate1);
	}
	
	// notice
	public List<CsNoticeVO> selectNotices(int start) {
		return dao.selectNotices(start);
	}
	public List<CsNoticeVO> selectNoticesCate(int start, String cate1) {
		return dao.selectNoticesCate(start, cate1);
	}
	
	public int selectCountNotice(String cate1) {
		return dao.selectCountNotice(cate1);
	}
	public int selectCountNotices() {
		return dao.selectCountNotices();
	}
	
	public int getCurrentPage(String pg) {
		int currentPage = 1;
		
		if(pg != null) {
			currentPage = Integer.parseInt(pg);
		}
		return currentPage;
	}
	
	public int getLastPageNum(int total) {
		int lastPageNum = 0;
		
		if(total % 10 == 0) {
			lastPageNum = total / 10;
		}else {
			lastPageNum  = total / 10 + 1;
		}
		return lastPageNum;
	}
	
	public int getPageStartNum(int total, int start) {
		return total - start;
	}
	
	public int[] getPageGroup(int currentPage, int lastPageNum) {
		
		int groupCurrent = (int) Math.ceil(currentPage / 10.0);
		int groupStart = (groupCurrent - 1) * 10 + 1;
		int groupEnd = groupCurrent * 10;
		
		if(groupEnd > lastPageNum) {
			groupEnd = lastPageNum;
		}
		int[] groups = {groupStart, groupEnd};
		
		return groups;
	}
	
	public int getLimitStart(int currentPage) {
		return (currentPage - 1) * 10;
	}
	
	public CsNoticeVO selectNotice(int no) {
		return dao.selectNotice(no);
	}
	
	// FAQ
	
	public List<CsFaqVO> selectFaqs(String cate1) {
		return dao.selectFaqs(cate1);
	}
	
	public List<Cate2VO> selectFaqCate(String cate1) {
		return dao.selectFaqCate(cate1);
	}
	
	public CsFaqVO selectFaq(int no) {
		return dao.selectFaq(no);
	}
	
	// QNA
	
	public int selectCountQnas(String cate1) { 
		return dao.selectCountQnas(cate1);
	}
	
	public List<CsQnaVO> selectQnas(int start, String cate1) {
		return dao.selectQnas(start, cate1);
	}
	
	public CsQnaVO selectQna(int no) {
		return dao.selectQna(no);
	}
	
	public int insertQna(CsQnaVO vo) {
		
		// 글 등록
		int result = dao.insertQna(vo);
		
		return result;	
	}
	
	public List<Cate1VO> selectCate1() {
		return dao.selectCate1();
	}
	
	public List<Cate2VO> selectCate2(String cate1) {
		return dao.selectCate2(cate1);
	}
	
}
