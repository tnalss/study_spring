package com.reo.test;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.reo.test.common.CommonService;
import com.reo.test.member.MemberService;
import com.reo.test.member.MemberVO;


@Controller
public class MemberController {
	@Autowired private MemberService member;
	@Autowired private CommonService common;
	
	@ResponseBody
	@RequestMapping("/smartLogin.mb")
	public boolean smart_login(String id, String pw,HttpSession session) {
		String salt = member.member_salt(id);
		pw = common.getEncrypt(salt, pw);
				
		HashMap<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("pw", pw);	
		MemberVO vo = member.member_login(map);
		session.setAttribute("loginInfo", vo);
		if(vo!=null)
			return true;
		return false;
	}
	
	@RequestMapping("/logout.mb")
	public String member_logout(HttpSession session) {
		session.removeAttribute("loginInfo");
		return "redirect:/";
	}
	
	@RequestMapping("/login.mb")
	public String member_login() {
		return "member/login";
	}
}
