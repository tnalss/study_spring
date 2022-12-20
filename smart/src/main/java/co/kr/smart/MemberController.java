package co.kr.smart;

import java.util.HashMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
	
	private String NaverClientId="jc0TvUqPpVAcscvLYPgz";
	private String NaverClientSecret="C7BBbocmZB";
	private String KakaoClientId="8aaf02d56e30aa6fd895baa3b52265d2";
	
	
	
	
	//회원가입화면 요청
	@RequestMapping("/member")
	public String member(HttpSession session) {
		session.setAttribute("category","join");
		return "member/join";
	}
	
	//회원가입폼 요청
	@ResponseBody
	@RequestMapping(value = "/join" , produces = "text/html; charset=utf-8")
	public String join(MemberVO vo, MultipartFile profile_image , HttpServletRequest request) {
		//salt만들어서 비밀번호 복호화 필요
		String salt =  common.generateSalt();
		vo.setSalt(salt);
		vo.setUserpw(common.getEncrypt(salt, vo.getUserpw()));
		
		
		//첨부된 프로필 파일이 있는 경우 업로드하는 처리 필요
		if( ! profile_image.isEmpty() ) {
			//서버의 물리적영역에 첨부파일을 저장한다.
			//커먼서비스에 메소드생성 파일저장 및 주소 값 반환
			vo.setProfile(common.fileUplaod("myinfo", profile_image, request));
						
		}
		
		
		
		//회원가입 잘된경우와 안된 경우 분기
		StringBuffer msg = new StringBuffer("<script>");
		if ( member.member_join(vo) == 1) {
			msg.append("alert('회원가입을 축하합니다 ^^*'); location='")
			.append( request.getContextPath() )
			.append("';");
		}else {
			msg.append("alert('회원가입 실패'); histiry.go(-1); ");
			
		}
		msg.append("</script>");
		
		
		return msg.toString();
	}
	
	
	
	
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
			
			if ( member.member_password_update(vo) ==1 && common.sendPassword(vo, pw)) {
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
	
	//비밀번호 변경처리 요청
	@RequestMapping("/changePassword")
	public String changePassword(String userpw,HttpSession session) {
		//비지니스 로직 - 화면에서 입력한 비밀버호로 db에 변경저장한다.
		//  기존의 솔트를 사용해서 새로입력한 비번을 암호화 기존솔트는 세션에 존재
		MemberVO vo = (MemberVO) session.getAttribute("loginInfo");
		userpw =  common.getEncrypt(vo.getSalt(), userpw);
		
		
		vo.setUserpw(userpw);
		member.member_password_update(vo);
	
		//바뀐정보 다시 담아줘야하는 처리
		session.setAttribute("loginInfo", vo);
		
		//응답화면 redirect로 웰컴페이지로
		return "redirect:/";
		
	}
	
	@RequestMapping("/naverLogin")
	public String naverLogin(HttpServletRequest request , HttpSession session) {
		//https://nid.naver.com/oauth2.0/authorize?response_type=code
		//&client_id=CLIENT_ID
		//&state=STATE_STRING
		//&redirect_uri=CALLBACK_URL
		String state =  UUID.randomUUID().toString();		
		session.setAttribute("state", state);
		
		StringBuffer url = new StringBuffer("https://nid.naver.com/oauth2.0/authorize?response_type=code");
		url.append("&client_id=").append(NaverClientId);
		url.append("&state=").append(state);
		url.append("&redirect_uri=").append(common.appURL(request)).append("/navercallback");
		
		//동의항목 재동의 요청
		//url.append("&auth_type=reprompt");
		
		
		return "redirect:"+url.toString();
	}
	@RequestMapping("/navercallback")
	public String navercallback(String code,String state, HttpSession session) {
		//코드가 null이거나 state가 같지 않을 경우 날려버림
		if ( code == null || ! state.equals(session.getAttribute("state")) ) 
			return "redirect:/";
		
		//정상처리
		
		//https://nid.naver.com/oauth2.0/token?grant_type=authorization_code
		//&client_id=jyvqXeaVOVmV
		//&client_secret=527300A0_COq1_XV33cf
		//&code=EIc5bFrl4RibFls1
		//&state=9kgsGTfH4j7IyAkg
		
		StringBuffer url = new StringBuffer("https://nid.naver.com/oauth2.0/token?grant_type=authorization_code");
		url.append("&client_id=").append(NaverClientId);
		url.append("&client_secret=").append(NaverClientSecret);
		url.append("&code=").append(code);
		url.append("&state=").append(state);
		
		//json값을 받을 json라이브러리 필요
		String response = common.requestAPI(url.toString());
		JSONObject json = new JSONObject(response);
		
		String token = json.getString("access_token");
		String type = json.getString("token_type");
		
		//"https://openapi.naver.com/v1/nid/me"
	    //Authorization: Bearer AAAAPIuf0L+qfDkMABQ3IJ8heq2mlw71DojBj3oc2Z6OxMQESVSrtR0dbvsiQbPbP1/cxva23n7mQShtfK4pchdk/rc="

		response = common.requestAPI("https://openapi.naver.com/v1/nid/me", type +" "+token);
		json = new JSONObject(response);
	    
		//api호출 겨로가코드가 00인경우 프로필정보에 접근
		if( json.getString("resultcode").equals("00")) {
			MemberVO vo = new MemberVO();
			vo.setSocial("N");
			
			json = json.getJSONObject("response");
			vo.setUserid( json.getString("id") );
			//nickname, name, email, gender(- F: 여성,- M: 남성,- U: 확인불가), profile_image, mobile
			//vo.setName( json.has("nickname") ? json.getString("nickname") : "");
			vo.setName( jsonValue(json,"nickname") );
			if( vo.getName().isEmpty() ) 
				vo.setName( jsonValue(json,"name",  "...") );
			vo.setEmail( jsonValue(json,"email") );
			vo.setGender( jsonValue( json,"gender", "M").equals("M") ? "남" : "여" );
			vo.setProfile( jsonValue(json, "profile_image") );
			vo.setPhone( jsonValue(json,"mobile") );
			
			if( member.member_idCheck(vo.getUserid())==1 ) { //update
				member.member_myInfo_update(vo);
			}else { //insert
				member.member_join(vo);
			}
			//소셜로그인되게 세션에 로그인정보를 담는다
			session.setAttribute("loginInfo", vo);
		}
	    
	    
		return "redirect:/";
	}
	
	
	@RequestMapping("/kakaoLogin")
	public String kakaoLogin(HttpServletRequest request) {
		//https://kauth.kakao.com/oauth/authorize?response_type=code
		//&client_id=${REST_API_KEY}
		//&redirect_uri=${REDIRECT_URI}
		
		StringBuffer url = new StringBuffer("https://kauth.kakao.com/oauth/authorize?response_type=code");
		url.append("&client_id=").append(KakaoClientId);
		url.append("&redirect_uri=").append(common.appURL(request)).append("/kakaocallback");
		
		return "redirect:"+url.toString();
	}
	
	@RequestMapping("/kakaocallback")
	public String kakaoCallback(String code,HttpServletRequest request, HttpSession session) {
		
		if (code != null) {
			StringBuffer url = new StringBuffer("https://kauth.kakao.com/oauth/token?");
			//"Content-Type: application/x-www-form-urlencoded" \
			
			url.append( "grant_type=authorization_code" );
			url.append("&client_id=").append(KakaoClientId);
			url.append("&redirect_uri=").append(common.appURL(request)).append("/kakaocallback");
			url.append("&code=").append(code);
			
			String response = common.requestAPI(url.toString());
			JSONObject json = new JSONObject(response);
			String type =  json.getString("token_type");
			String token = json.getString("access_token");
			
			//"token_type": "bearer",
		    //"access_token": "${ACCESS_TOKEN}",
			
			//https://kapi.kakao.com/v2/user/me" \
			//-H "Authorization: Bearer ${ACCESS_TOKEN}"
			
			response = common.requestAPI("https://kapi.kakao.com/v2/user/me", type+ " " + token);
			json = new JSONObject(response);
			
			MemberVO vo = new MemberVO();
			vo.setSocial("K");
			vo.setUserid(  json.get("id").toString() );
			json = json.getJSONObject("kakao_account");
			vo.setEmail(json.getString("email"));
			vo.setGender(json.getString("gender").equals("male") ? "남" :"여" );
			json = json.getJSONObject("profile");
			
			vo.setName(json.getString("nickname"));
			vo.setProfile(json.getString("profile_image_url"));
			
			if( member.member_idCheck(vo.getUserid())==1 ) { //update
				member.member_myInfo_update(vo);
			}else { //insert
				member.member_join(vo);
			}
			//소셜로그인되게 세션에 로그인정보를 담는다
			session.setAttribute("loginInfo", vo);
			
		}
		
		return "redirect:/";
	}
	
	//아이디 중복확인 요청
	@ResponseBody
	@RequestMapping("/idCheck")
	public boolean idCheck(String id) {
		//비지니스 로직 - 화면에서 입력한 아이디가 db에 존재하는 지 확인.
		// 0 - 아이디 없음  1 - 아이디 있음 중복됨.
		return member.member_idCheck(id) == 0 ? false : true  ;
			
	}
	
	
	
	
	private String jsonValue(JSONObject json , String key) {
		return json.has(key) ? json.getString(key) : "";
	}
	private String jsonValue(JSONObject json , String key,String defaultValue) {
		return json.has(key) ? json.getString(key) : defaultValue;
	}
	
}
