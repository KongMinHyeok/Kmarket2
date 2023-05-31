package kr.co.kmarket2.utils;


import kr.co.kmarket2.vo.PageVO;

public class PaginationUtils {
	
    public static PageVO getPage(int pageSize, int currentPage, int totalItems) {
        int totalPages = (int) Math.ceil((double) totalItems / pageSize);
        int currentGroup = (currentPage + 9) / 10;
        int startPages = (currentGroup - 1) * 10 + 1;
        int endPages = Math.min(startPages + 9, totalPages);
        
        return new PageVO(totalPages, startPages, endPages);
    }
}
