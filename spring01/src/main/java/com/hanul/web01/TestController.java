package com.hanul.web01;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import member.MemberVO;

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
	
	//HttpServlet 방식요청시
	@RequestMapping("/joinRequest")
	public String join(HttpServletRequest request, Model model) {
		//비지니스 로직
		// 파라미터 정보를 화면에 출력할수 있도록 담는다
		model.addAttribute("name", request.getParameter("name"));
		model.addAttribute( "gender", request.getParameter("gender"));
		model.addAttribute("email",request.getParameter("email"));
		model.addAttribute("method", "httpservletRequest 방식");
		
		//응답화면
		return "member/info";
	}
	
	//RequestParam 방식 ,필요한 정보만 파라미터로 씀
	@RequestMapping("/joinParam")
	public String join(String name, @RequestParam("gender") String code, @RequestParam String email,Model model) {
		model.addAttribute("method","RequestParam");
		model.addAttribute("name",name);
		model.addAttribute("gender",code);
		model.addAttribute("email",email);
		
		return "member/info";
		
	}
	
	//데이터 객체를 이용한 방식
	@RequestMapping("/joinDataObject")
	public String join(Model model, MemberVO vo) {
		
		model.addAttribute("method","데이터 객체");
		// 서블릿에서 DTO-> 스프링에서는 VO	
		model.addAttribute("vo",vo);
		
		return "member/info";
		
	}
	
	//경로의 형태로 데이터를 접근
	//데이터 한두개보낼때
	@RequestMapping("/joinPath/{name}/{g}/{email}")
	public String join(Model model,@PathVariable String name, @PathVariable("g") String gender, @PathVariable String email) {
		//비지니스 로직
		//경로를 통해 받은 정보를 화면에 출력할 수 있도록 담는다.
		model.addAttribute("name",name);
		model.addAttribute("gender",gender);
		model.addAttribute("email",email);
		model.addAttribute("method","@pathVariable방식");
		// 응답화면 연결
		return "member/info";
	}
	
	
	@RequestMapping("/login")
	public String login() {
		//응답화면연결
		return "member/login";
	}
	
	
	//redirect는 어떻게 해야하나 ????????????????
	// redirect:매핑요청주소!!!!!!!!!!!!!!!!!!!!
	@RequestMapping("/login_result")
	public String login(String id, String pw, Model model) {
		//성공  vs 실패
		if(id.equals("admin") && pw.equals("1234")) {
			//return "home";     //forward 방식
			return "redirect:/";        
		} else {
			//return "member/login";  //forward방식
			return "redirect:/login";
		}
		
	}
	
	
	
}
