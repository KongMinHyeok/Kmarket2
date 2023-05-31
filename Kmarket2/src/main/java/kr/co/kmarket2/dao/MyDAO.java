package kr.co.kmarket2.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.kmarket2.vo.CouponVO;
import kr.co.kmarket2.vo.CsQnaVO;
import kr.co.kmarket2.vo.MemberPointVO;
import kr.co.kmarket2.vo.MemberVO;
import kr.co.kmarket2.vo.ProductOrderVO;
import kr.co.kmarket2.vo.ProductReviewVO;

@Mapper
@Repository
public interface MyDAO {
	int selectMemberPointByUid(String uid);
	int countDeliveryByUid(String uid);
	int countCouponByUid(String uid);
	int countQnaStatByUid(String uid);
	List<ProductOrderVO> selectOrderByUid(String uid);
	List<MemberPointVO> selectPointByUid(String uid);
	List<ProductReviewVO> selectReviewByUid(String uid);
	List<CsQnaVO> selectQnaByUid(String uid);
	MemberVO selectMemberInfoByUid(String uid);
	List<ProductOrderVO> selectOrderByUidForPeriod(String uid, int periodNum, String period);
	List<ProductOrderVO> selectOrderByUidBetween(String uid, String startDate, String endDate);
	List<MemberPointVO> selectPointByUidForPeriod(String uid, int periodNum, String period);
	List<MemberPointVO> selectPointByUidBetween(String uid, String startDate, String endDate);
	List<ProductReviewVO> selectReviewListByUid(String uid);
	List<CouponVO> selectCouponByUid(String uid);
	int countCouponAllByUid(String uid);
	List<CsQnaVO> selectQnaALLByUid(String uid);
}
