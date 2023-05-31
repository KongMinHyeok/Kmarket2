package kr.co.kmarket2.controller.my;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.kmarket2.service.MyService;

@Controller
public class CouponController {
	private final MyService service;
	public CouponController(MyService service) {
		this.service = service;
	}

	@GetMapping("my/coupon")
	public String coupon(Model model, String uid) {
		if(uid == null) uid = "admin";
		// 상단 고정 정보
		model.addAttribute("topP", service.selectMemberPointByUid(uid));
		model.addAttribute("topD",service.countDeliveryByUid(uid));
		model.addAttribute("topC",service.countCouponByUid(uid));
		model.addAttribute("topQ",service.countQnaStatByUid(uid));
		
		model.addAttribute("couponList",service.selectCouponByUid(uid));
		model.addAttribute("couponCount",service.countCouponAllByUid(uid));
		return "my/coupon";
	}
}
