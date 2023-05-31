package kr.co.kmarket2.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.kmarket2.vo.Cate1VO;
import kr.co.kmarket2.vo.Cate2VO;
import kr.co.kmarket2.vo.CsFaqVO;
import kr.co.kmarket2.vo.CsNoticeVO;
import kr.co.kmarket2.vo.CsQnaVO;


@Mapper
@Repository
public interface CsDAO {
	
	// index
	public List<CsNoticeVO> selectIndexNotice();
	public List<CsQnaVO> selectIndexQna(String cate1);
	
	// notice
	public CsNoticeVO selectNotice(int no);
	public List<CsNoticeVO> selectNoticeByGroup();
	public List<CsNoticeVO> selectNotices(int start);
	public List<CsNoticeVO> selectNoticesCate(int start, String cate1);
	public int selectCountNotice(String cate1);
	public int selectCountNotices();
	
	// qna
	public int selectCountQnas(String cate1);
	public List<CsQnaVO> selectQnas(int start, String cate1);
	public CsQnaVO selectQna(int no);
	public int insertQna(CsQnaVO vo);
	public List<Cate1VO> selectCate1();
	public List<Cate2VO> selectCate2(String cate1);
	
	// faq
	public List<CsFaqVO> selectFaqs(String cate1);
	public List<Cate2VO> selectFaqCate(String cate1);
	public CsFaqVO selectFaq(int no);
	
}
