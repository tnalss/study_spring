package com.hanul.web01;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {
	@RequestMapping("/first")
	public String first (Model model) {
		//비지니스 로직
		//오늘 날짜를 화면에 출력할수있도록 저장한다
		String today = new SimpleDateFormat("yyyy-MM-dd").format( new Date());
		model.addAttribute("today",today);
		
		//응답화면 연결
		return "index";
	}
	
	@RequestMapping("/second")
	public String second( Model model ) {
		//비지니스 로직
		
		String now = new SimpleDateFormat("HH:mm:ss").format(new Date());
		model.addAttribute("now", now);
		//응답화면 연결
		return "index";
	}
	
	
	//모델앤뷰로...return type 확인
	@RequestMapping("/third")
	public ModelAndView third() {

		ModelAndView model = new ModelAndView();
		String today =
		new SimpleDateFormat("yyyy/MM/dd HH:MM:ss").format(new Date());
		
		model.addObject("todayNow",today);//데이터 저장
		model.setViewName("index");
		return model;
	}
	
	@RequestMapping("/member")
	public String member() {
		return "member/join";
	}
	
}
