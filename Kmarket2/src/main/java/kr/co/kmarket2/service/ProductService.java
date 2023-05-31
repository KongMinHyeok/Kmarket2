package kr.co.kmarket2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.kmarket2.dao.ProductDAO;
import kr.co.kmarket2.vo.ProductCartVO;
import kr.co.kmarket2.vo.ProductCate1VO;
import kr.co.kmarket2.vo.ProductCate2VO;
import kr.co.kmarket2.vo.ProductOrderVO;
import kr.co.kmarket2.vo.ProductReviewVO;
import kr.co.kmarket2.vo.ProductVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductService {
	
	@Autowired
	private ProductDAO dao;
	
	public List<ProductCate1VO> selectCate1(){
		return dao.selectCate1();
	}
	public List<ProductCate2VO> selectCate2(){
		return dao.selectCate2();
	}
	
	//main list
	public List<ProductVO> selectProductsBest(){
		return dao.selectProductsBest();
	}
	public List<ProductVO> selectProductsHit(){
		return dao.selectProductsHit();
	}
	public List<ProductVO> selectProductsScore(){
		return dao.selectProductsScore();
	}
	public List<ProductVO> selectProductsNew(){
		return dao.selectProductsNew();
	}
	public List<ProductVO> selectProductsDiscount(){
		return dao.selectProductsDiscount();
	}
	
	// list
	public List<ProductVO> selectProducts(String prodcate1, String prodcate2, String sort){
		return dao.selectProducts(prodcate1,prodcate2,sort);
	}

	public int selectCountTotal(String prodcate1, String prodcate2) {
		return dao.selectCountTotal(prodcate1, prodcate2);
	}
	
	// view
	public ProductVO selectProduct(String prodNo) {
		return dao.selectProduct(prodNo);
	}
	
	// cart
	public int insertCart(ProductCartVO vo) {
		return dao.insertCart(vo);
	}
	public List<ProductCartVO> selectCartProduct(String uid){
		return dao.selectCartProducts(uid);
	}
	public int deleteCart(List<String> chks) {
		return dao.deleteCart(chks);
	}
	
	// order
	public List<ProductCartVO> selectCartByCartNo(List<String> cartNo){
		return dao.selectCartByCartNo(cartNo);
	}
		

	// review
	public List<ProductReviewVO> selectReview(String prodNo, int start){
		return dao.selectReview(prodNo, start);
	}
	public int selectCountTotalReview(String prodNo) {
		return dao.selectCountTotalReview(prodNo);
	}
	
	// 현재 페이지 번호
	public int getCurrentPage(String pg) {
	  int currentPage = 1;
	
	  if(pg != null) {
	      currentPage = Integer.parseInt(pg);
	  }
	  return currentPage;
	}
	
	// 페이지 시작값
	public int getLimitStart(int currentPage) {
	  return (currentPage - 1) * 10;
	}
	
	// 마지막 페이지 번호
	public int getLastPageNum(int total) {
	
	  int lastpageNum = 0;
	
	  if(total % 10 == 0) {
	      lastpageNum = total / 10;
	
	  }else {
	      lastpageNum = total / 10 + 1;
	  }
	  return lastpageNum;
	}
	
	// 페이지 시작 번호
	public int getpageStartNum(int total, int start) {
	  return total - start;
	}
	
	// 페이지 그룹
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
	
/////// review 페이징 처리
	// 현재 페이지 번호
	public int getCurrentPage2(String pg) {
	  int currentPage = 1;
	
	  if(pg != null) {
	      currentPage = Integer.parseInt(pg);
	  }
	  return currentPage;
	}
	
	// 페이지 시작값
	public int getLimitStart2(int currentPage) {
	  return (currentPage - 1) * 5;
	}
	
	// 마지막 페이지 번호
	public int getLastPageNum2(int total) {
	
	  int lastpageNum = 0;
	
	  if(total % 10 == 0) {
	      lastpageNum = total / 5;
	
	  }else {
	      lastpageNum = total / 5 + 1;
	  }
	  return lastpageNum;
	}
	
	// 페이지 시작 번호
	public int getpageStartNum2(int total, int start) {
	  return total - start;
	}
	
	// 페이지 그룹
	public int[] getPageGroup2(int currentPage, int lastPageNum) {
	
	  int groupCurrent = (int) Math.ceil(currentPage / 5.0);
	  int groupStart = (groupCurrent - 1) * 5 + 1;
	  int groupEnd = groupCurrent * 5;
	
	  if(groupEnd > lastPageNum) {
	      groupEnd = lastPageNum;
	  }
	
	  int[] groups = {groupStart, groupEnd};
	
	  return groups;
	}
    
    
}
