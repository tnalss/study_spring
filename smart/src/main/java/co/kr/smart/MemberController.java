package co.kr.smart;

import java.util.HashMap;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.CommonService;
import member.MemberService;
import member.MemberVO;

@Controller
public class MemberController {
	
	@Autowired private MemberService member;
//	@Autowired private MemberServiceImpl service;
//	private MemberService member;
//	public MemberController(MemberService member) {
//		this.member = member;
//	}
	@Autowired private CommonService common;
	
	//로그아웃처리 요청
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		//비지니스로직: 세션에 있는 로그인정보를 삭제한다
		session.removeAttribute("loginInfo");
		//응답화면연결
		return "redirect:/";
	}	
	//비밀번호 변경 화면 요청
		@RequestMapping("/changePW")
		public String changePw() {
			return "member/change";				
		}
	
	//비밀번호 재발급처리 요청
		@ResponseBody
		@RequestMapping(value = "/reset" ,  produces="text/html; charset=utf-8")
		public String reset(MemberVO vo) {
			//비지니스로직-화면에서 입력한 아이디/이메일이 일치하는 회원에게 임시 비번을 발급해준다 
			//임시 비번을 생성한 후 회원정보에 변경저장 하고, 임시비번을 회원에게 이메일로 알려준다
			String name = member.member_userid_email(vo);
			
			if( name == null ) {
				
				StringBuffer msg = new StringBuffer("<script>");
				msg.append("alert('일치하는 정보가 없습니다. \\n확인하세요!');");
				msg.append("history.go(-1);");
				msg.append("</script>");
				
				return msg.toString();
			}
			
			String pw = UUID.randomUUID().toString();
			pw = pw.substring(pw.lastIndexOf("-")+1);
			
			String salt =  common.generateSalt();
			vo.setSalt(salt);
			vo.setUserpw(common.getEncrypt(salt, pw));
			vo.setName(name);
			
			StringBuffer msg = new StringBuffer("<script>");
			
			if ( member.member_myInfo_update(vo) ==1 && common.sendPassword(vo, pw)) {
				msg.append("alert('임시비밀번호가 발송되었습니다.\\n이메일을 확인하세요.');");
				msg.append("location='login';"); //임시비밀번호로 로그인 시도할 수 있도록 로그인하면 연결
			}else {
				msg.append("alert('임시 비밀번호 발급 실패');");
				msg.append("history.go(-1);");
			}
			
			msg.append("</script>");
			return msg.toString();
		}
		
		
	
	//비밀번호찾기 화면 요청 - 비밀번호재발급(임시비번발급) 화면
	@RequestMapping("/find")
	public String find() {
		return "default/member/find";
	}
	
	//로그인처리 요청
	@ResponseBody @RequestMapping("/smartLogin")
	public boolean login(String id, String pw, HttpSession session) {
		//해당 아이디의 salt를 조회해온다.
		String salt = member.member_salt(id);
		
		pw = common.getEncrypt(salt, pw);//db에서 조회해온 salt를 사용해 화면에서 입력한 비번을 암호화
		
		//비지니스로직-화면에서 입력한 아이디/비번이 일치하는 회원정보를 DB에서 조회한다
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pw", pw);
		MemberVO vo = member.member_login(map);
		//          화면엣 출력할 수 있도록 세션에 attribute로 담는다
		session.setAttribute("loginInfo", vo);
		return vo==null ? false : true;
	}
	
	
	//로그인화면 요청
	@RequestMapping("/login")
	public String login(HttpSession session) {
		session.setAttribute("category", "login");
		return "default/member/login";
	}
}
