package kr.co.kmarket2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.co.kmarket2.dao.MyDAO;
import kr.co.kmarket2.vo.CouponVO;
import kr.co.kmarket2.vo.CsQnaVO;
import kr.co.kmarket2.vo.MemberPointVO;
import kr.co.kmarket2.vo.MemberVO;
import kr.co.kmarket2.vo.ProductOrderVO;
import kr.co.kmarket2.vo.ProductReviewVO;
/*
 * 날짜 : 2023/02/23
 * 이름 : 김지홍
 * 내용 : my service
 */
@Service
public class MyService{
	
	private final MyDAO dao;

	public MyService(MyDAO dao) {
		this.dao = dao;
	}
	
	// top info my 페이지 고정
	public int selectMemberPointByUid(String uid) {
		return dao.selectMemberPointByUid(uid);
	}

	public int countDeliveryByUid(String uid) {
		return dao.countDeliveryByUid(uid);
	}

	public int countCouponByUid(String uid) {
		return dao.countCouponByUid(uid);
	}

	public int countQnaStatByUid(String uid) {
		return dao.countQnaStatByUid(uid);
	}

	// 최근 5개 
	//// 주문내역
	public List<ProductOrderVO> selectOrderByUid(String uid) {
		return dao.selectOrderByUid(uid);
	}
	// 포인트적립내역
	public List<MemberPointVO> selectPointByUid(String uid) {
		return dao.selectPointByUid(uid);
	}
	// 상품평
	public List<ProductReviewVO> selectReviewByUid(String uid) {
		return dao.selectReviewByUid(uid);
	}
	// 문의내역
	public List<CsQnaVO> selectQnaByUid(String uid) {
		return dao.selectQnaByUid(uid);
	}
	// 내정보
	public MemberVO selectMemberInfoByUid(String uid) {
		return dao.selectMemberInfoByUid(uid);
	}

	public List<ProductOrderVO> selectOrderByUidForPeriod(String uid, int periodNum, String period) {
		return dao.selectOrderByUidForPeriod(uid, periodNum, period);
	}

	public List<ProductOrderVO> selectOrderByUidBetween(String uid, String startDate, String endDate) {
		return dao.selectOrderByUidBetween(uid, startDate, endDate);
	}

	public List<MemberPointVO> selectPointByUidForPeriod(String uid, int periodNum, String period) {
		return dao.selectPointByUidForPeriod(uid, periodNum, period);
	}

	public List<MemberPointVO> selectPointByUidBetween(String uid, String startDate, String endDate) {
		return dao.selectPointByUidBetween(uid, startDate, endDate);
	}

	public List<ProductReviewVO> selectReviewListByUid(String uid) {
		return dao.selectReviewListByUid(uid);
	}

	public List<CouponVO> selectCouponByUid(String uid){
		return dao.selectCouponByUid(uid);
	}
	
	public int countCouponAllByUid(String uid) {
		return dao.countCouponAllByUid(uid);
	}
	public List<CsQnaVO> selectQnaALLByUid(String uid){
		return dao.selectQnaALLByUid(uid);
	}

}
