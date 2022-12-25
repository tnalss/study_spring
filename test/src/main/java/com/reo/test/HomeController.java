package com.reo.test;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.reo.test.member.MemberService;

@Controller
public class HomeController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpSession session) {
		/*
		 * 기존회원들에 대한 솔트처리
		 * 
		 * 복호화가 여러번 될 수 있으므로 단 한번만 실행할것 
		 * CommonService common = new CommonService(); List<MemberVO> list =
		 * member.member_list(); for(MemberVO vo : list ) { //비밀번호가 있는 회원에 대해 암호화에 사용할
		 * salt를 만든다 if( vo.getUserpw()!=null ) { String salt = common.generateSalt();
		 * String pw = common.getEncrypt(salt, vo.getUserpw()); vo.setSalt(salt);
		 * vo.setUserpw(pw); member.member_password_update(vo); } }
		 */
			
		session.removeAttribute("category");
		session.removeAttribute("title");
			
		
		return "home";
	}
	
}
