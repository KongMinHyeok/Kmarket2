package kr.co.kmarket2.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.kmarket2.dao.AdminDAO;
import kr.co.kmarket2.dto.ProductDTO;
import kr.co.kmarket2.entity.CsCate1Entity;
import kr.co.kmarket2.entity.CsCate2Entity;
import kr.co.kmarket2.entity.CsFaqEntity;
import kr.co.kmarket2.entity.CsNoticeEntity;
import kr.co.kmarket2.entity.CsQnaEntity;
import kr.co.kmarket2.entity.ProductCate2Entity;
import kr.co.kmarket2.entity.ProductEntity;
import kr.co.kmarket2.repository.CsCate1Repo;
import kr.co.kmarket2.repository.CsCate2Repo;
import kr.co.kmarket2.repository.CsFaqRepo;
import kr.co.kmarket2.repository.CsNoticeRepo;
import kr.co.kmarket2.repository.CsQnaRepo;
import kr.co.kmarket2.repository.ProductCate2Repo;
import kr.co.kmarket2.repository.ProductRepo;
import kr.co.kmarket2.specification.AdminFaqSpecification;
import kr.co.kmarket2.specification.AdminNoticeSpecification;
import kr.co.kmarket2.specification.ProductSpecification;
import kr.co.kmarket2.utils.PaginationUtils;
import kr.co.kmarket2.vo.CsFaqVO;
import kr.co.kmarket2.vo.CsQnaVO;
import kr.co.kmarket2.vo.PageVO;
import lombok.extern.slf4j.Slf4j;

/*
 * 날짜 : 2023/02/16
 * 이름 : 김지홍
 * 내용 : admin Service
 */

@Slf4j
@Service
public class AdminService{

	private final AdminDAO adminDAO;
	private final ProductRepo productRepo;
	private final ProductCate2Repo prodCate2Repo;
	private final CsFaqRepo csFaqRepo;
	private final CsQnaRepo csQnaRepo;
	private final CsNoticeRepo csNoticeRepo;
	private final CsCate1Repo cate1Repo;
	private final CsCate2Repo csCate2Repo;
	
	public AdminService(AdminDAO adminDAO, ProductRepo productRepo, ProductCate2Repo prodCate2Repo, CsFaqRepo csFaqRepo,
			CsQnaRepo csQnaRepo, CsNoticeRepo csNoticeRepo, CsCate1Repo cate1Repo, CsCate2Repo csCate2Repo) {
		this.adminDAO = adminDAO;
		this.productRepo = productRepo;
		this.prodCate2Repo = prodCate2Repo;
		this.csFaqRepo = csFaqRepo;
		this.csQnaRepo = csQnaRepo;
		this.csNoticeRepo = csNoticeRepo;
		this.cate1Repo = cate1Repo;
		this.csCate2Repo = csCate2Repo;
	}
	
	// pageable 반환
	private Pageable getPageable(int pageNum) {
		return PageRequest.of(pageNum-1, 10, Sort.by("rdate").descending());
	}
	
	// 상품 등록
	public void insertProduct(ProductEntity entity) {
		productRepo.save(entity);
	}
	
	// cate 1에 따라 cate2 불러오기
	public List<ProductCate2Entity> selectAdminCate2(int cate1) {
		//List<ProductCate2VO> cate2List =  adminDAO.selectAdminCate2(cate1);
		List<ProductCate2Entity> cate2List = prodCate2Repo.findByCate1(cate1);
		return cate2List;
	}
	
	// IPv4 형식의 ip 값 가져오기
    public String getRemoteIP(HttpServletRequest request){
        String ip = request.getHeader("X-FORWARDED-FOR"); 
        //proxy 환경일 경우
        if (ip == null || ip.length() == 0) ip = request.getHeader("Proxy-Client-IP");
        //웹로직 서버일 경우
        if (ip == null || ip.length() == 0) ip = request.getHeader("WL-Proxy-Client-IP");
        if (ip == null || ip.length() == 0) ip = request.getRemoteAddr() ;
        return ip;
   }
    
   // 파일 업로드
   public List<String> upload(ProductDTO dto) {
	   MultipartFile[] files = {dto.getThumb1(), dto.getThumb2(), dto.getThumb3(), dto.getDetail()};
	   List<String> fList = new ArrayList<>();
	   for (MultipartFile file : files) {
		   String oriName = file.getOriginalFilename();
		   String ext = oriName.substring(oriName.lastIndexOf("."));
		   String newFileName = UUID.randomUUID().toString() + ext;
		   
		   DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd");
		   Date date = new Date();
		   String dateStr = dateFormat.format(date);
		   
		   File folder = new File("file/" + dateStr);
		   if(!folder.exists()) {
			   folder.mkdirs();
		   }
		   
		   Path path = Paths.get("file/" + dateStr + "/" + newFileName);
		   try {
			   Files.write(path, file.getBytes());
			   
		   } catch (IOException e) {
			   log.error(e.getMessage());
		   }
		   
		    fList.add(dateStr + "/" + newFileName);
	   }
	   
	   return fList;
   }
   	// 상품 체크 삭제
   public void deleteAllByProdNoIn(int[] checks) {
	   productRepo.deleteAllByProdNoIn(checks);
   }
   // 상품 개별 삭제
   public void deleteByProdNo(int prodNo) {
	   productRepo.deleteById(prodNo);
   }
   
   
   
   
	// admin product list - jpa 로 페이징 하기
	public Page<ProductEntity> getProducts(int pageNum, String prodName, String prodNo, String company, String seller){
		Specification<ProductEntity> specification = new ProductSpecification(prodName, prodNo, company, seller);
		return productRepo.findAll(specification, getPageable(pageNum));
	}
	
	// 페이징에 필요한 start, endpage 가져오기
	public int[] getPageNumbers(Page<?> productPage) {
	    int totalPages = productPage.getTotalPages();
	    int currentPage = productPage.getNumber() + 1;

	    int startPage = ((currentPage - 1) / 10) * 10 + 1;
	    int endPage = Math.min(startPage + 9, totalPages);

	    int[] pageNumbers = {startPage, endPage};
	    return pageNumbers;
	}

	// cs notice list
	public Page<CsNoticeEntity> getNoticeList(int cate1, int pageNum){
		Specification<CsNoticeEntity> specification = new AdminNoticeSpecification(cate1);
		Pageable pageable = PageRequest.of(pageNum-1, 10, Sort.by("rdate").descending());
		return csNoticeRepo.findAll(specification, pageable);
//		return csNoticeRepo.findAllByOrderByRdateDesc(pageable);
	}
	
	// cs noite one
	public CsNoticeEntity getNoticeOne(int no) {
		return csNoticeRepo.findById(no).get();
	}
	
	// cs notice write
	public void writeNotice(CsNoticeEntity csNoticeEntity, int cate1, HttpServletRequest req) {
		CsCate1Entity cate1Entity = cate1Repo.findById(cate1).orElse(null);
		if (cate1Entity != null) {
		    csNoticeEntity.setCate1Entity(cate1Entity);
		    csNoticeEntity.setRegip(getRemoteIP(req));
		    csNoticeRepo.save(csNoticeEntity);
		}
	}
	
	// cs notice modify
	public void modifyNotice(CsNoticeEntity entity, int cate1, HttpServletRequest req) {
		CsCate1Entity cate1Entity = cate1Repo.findById(cate1).get();
		if(cate1Entity != null) {
			entity.setCate1Entity(cate1Entity);
			entity.setRegip(getRemoteIP(req));
			csNoticeRepo.save(entity);
		}
	}
	
	// cs notice delete
	public void deleteNotice(int no) {
		csNoticeRepo.deleteById(no);
	}
	

	
	
	
	// cs faq list
	public Page<CsFaqEntity> getFaqList(int pageNum, int cate1, int cate2){
		Specification<CsFaqEntity> specification = new AdminFaqSpecification(cate1, cate2);
		return csFaqRepo.findAll(specification, getPageable(pageNum));
	}
	
	
	
	
	
	// cs qna list
	public List<CsQnaVO> getQnaList(int offset, int limit, int cate1, int cate2){
		return adminDAO.selectListQna(offset, limit, cate1, cate2);
	}
	// cs qna count total
	public int countTotalQna(int cate1, int cate2) {
		return adminDAO.countTotalQna(cate1, cate2);
	}
	
	// cs qna view
	public CsQnaVO selectQnaById(int no) {
		return adminDAO.selectQnaById(no);
	}
	
	// cs qna modify
	@Transactional
	public CsQnaEntity updateQna(int no, String answer) {
		CsQnaEntity entity = csQnaRepo.findById(no).get();
		entity.setAnswer(answer);
		entity.setStatus(1);
		return entity;
	}
	// cs qna delete
	public void deleteQna(int no) {
		csQnaRepo.deleteById(no);
	}
	
	
	// cs cate2 list
	public List<CsCate2Entity> getCate2List(int cate1){
		return csCate2Repo.findByCate1(cate1);
	}
	
	
	// page info 
	public PageVO getPageNumFaq(int pageSize, int currentPage, int countTotal) {
		return PaginationUtils.getPage(pageSize, currentPage, countTotal);
	}
	
	// offset
	public int getOffset(int pageNum) {
		int offset = (pageNum-1) * 10;
		return offset;
	}
	
	// delete check
	public void deleteCheck(int[] checks) {
		csQnaRepo.deleteAllByNoIn(checks);
	}
	

}
