package kr.co.kmarket2.controller.my;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.kmarket2.service.MyService;

@Controller
public class PointController {
	private final MyService service;
	public PointController(MyService service) {
		this.service = service;
	}

	@GetMapping("my/point")
	public String point(Model model, String uid) {
		if(uid == null) uid = "admin";
		model.addAttribute("topP", service.selectMemberPointByUid(uid));
		model.addAttribute("topD",service.countDeliveryByUid(uid));
		model.addAttribute("topC",service.countCouponByUid(uid));
		model.addAttribute("topQ",service.countQnaStatByUid(uid));
		return "my/point";
	}
}
