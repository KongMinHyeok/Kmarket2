package kr.co.kmarket2.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.kmarket2.vo.ProductCartVO;
import kr.co.kmarket2.vo.ProductCate1VO;
import kr.co.kmarket2.vo.ProductCate2VO;
import kr.co.kmarket2.vo.ProductOrderVO;
import kr.co.kmarket2.vo.ProductReviewVO;
import kr.co.kmarket2.vo.ProductVO;
import lombok.extern.slf4j.Slf4j;

@Mapper
@Repository
public interface ProductDAO {

	// 카테고리 출력
	public List<ProductCate1VO> selectCate1();
	public List<ProductCate2VO> selectCate2();
	
	//main list
	public List<ProductVO> selectProductsBest();
	public List<ProductVO> selectProductsHit();
	public List<ProductVO> selectProductsScore();
	public List<ProductVO> selectProductsNew();
	public List<ProductVO> selectProductsDiscount();

	//list
	public List<ProductVO> selectProducts(String prodcate1, String prodcate2, String sort);
	public int selectCountTotal(String prodcate1, String prodcate2); //홍민준 list paging

	//view
	public ProductVO selectProduct(String prodNo);
	
	// review
	public List<ProductReviewVO> selectReview(String prodNo, int start);
	public int selectCountTotalReview(String prodNo);
	
	//cart
	public int insertCart(ProductCartVO vo);
	public List<ProductCartVO> selectCartProducts(String uid);
	public int deleteCart(List<String> chks);
	
	// order
	public List<ProductCartVO> selectCartByCartNo(List<String> cartNo);

	public List<ProductOrderVO> selectOrder(String uid);
	public int insertOrder(ProductOrderVO ovo);
	public void updateProduct(ProductVO vo);
}
