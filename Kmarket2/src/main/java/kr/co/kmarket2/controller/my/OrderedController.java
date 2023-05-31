package kr.co.kmarket2.controller.my;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.kmarket2.service.MyService;

@Controller
public class OrderedController {
	private final MyService service;
	public OrderedController(MyService service) {
		this.service = service;
	}

	@GetMapping("my/ordered")
	public String Ordered(Model model, String uid, 
			@RequestParam(defaultValue = "1") int periodNum,
			@RequestParam(defaultValue = "MONTH") String period,
			@RequestParam(required = false) String startDate,
			@RequestParam(required = false) String endDate) {
		if(uid == null) uid = "admin";
		model.addAttribute("topP", service.selectMemberPointByUid(uid));
		model.addAttribute("topD",service.countDeliveryByUid(uid));
		model.addAttribute("topC",service.countCouponByUid(uid));
		model.addAttribute("topQ",service.countQnaStatByUid(uid));
		
		// 선택한 버튼 활성화
		model.addAttribute("periodOn", periodNum+period);
		
		// 날짜 지정 검색시 분기
		if(startDate != null && endDate != null) {
			model.addAttribute("OrdList",service.selectOrderByUidBetween(uid, startDate, endDate));
		}else {
			model.addAttribute("OrdList",service.selectOrderByUidForPeriod(uid, periodNum, period));
		}
		
		
		return "my/ordered";
	}
}
