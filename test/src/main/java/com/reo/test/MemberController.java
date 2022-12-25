package com.reo.test;

import java.util.HashMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.reo.test.common.CommonService;
import com.reo.test.member.MemberService;
import com.reo.test.member.MemberVO;

@Controller
public class MemberController {
	@Autowired
	private MemberService member;
	@Autowired
	private CommonService common;

	@ResponseBody
	@RequestMapping("/smartLogin.mb")
	public boolean smart_login(String id, String pw, HttpSession session) {
		String salt = member.member_salt(id);
		pw = common.getEncrypt(salt, pw);

		HashMap<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("pw", pw);
		MemberVO vo = member.member_login(map);
		session.setAttribute("loginInfo", vo);
		if (vo != null)
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

	private String NaverID = "jc0TvUqPpVAcscvLYPgz";
	private String NaverPW = "C7BBbocmZB";

	@RequestMapping("/naverLogin.mb")
	public String naver_login(HttpSession session, HttpServletRequest request) {
		// https://nid.naver.com/oauth2.0/authorize?response_type=code
		// &client_id=CLIENT_ID
		// &state=STATE_STRING
		// &redirect_uri=CALLBACK_URL
		String state = UUID.randomUUID().toString();
		session.setAttribute("state", state);
		StringBuffer url = new StringBuffer();
		url.append("https://nid.naver.com/oauth2.0/authorize?response_type=code");
		url.append("&client_id=").append(NaverID);
		url.append("&state=").append(state);
		url.append("&redirect_uri=").append(common.appURL(request));
		url.append("/navercallback.mb");

		return "redirect:" + url.toString();
	}

	@RequestMapping("/navercallback.mb")
	public String naver_callback(String code, String state, HttpSession session) {
		String Sstate = (String) session.getAttribute("state");
		// code가 null이거나
		// 세션의 state인 Sstate와 파라미터에 들어있는 state가 같지 않은 경우 보내버림
		if (code == null || !state.equals(Sstate))
			return "redirect:/";

		// 세션이 일치하고 코드 값이 있는 경우(로그인성공)에는 accesstoken을 받아와야함.
		// https://nid.naver.com/oauth2.0/token?grant_type=authorization_code
		// &client_id=
		// &client_secret=
		// &code=EIc5bFrl4RibFls1&state=9kgsGTfH4j7IyAkg
		StringBuffer url = new StringBuffer("https://nid.naver.com/oauth2.0/token?grant_type=authorization_code");
		url.append("&client_id=").append(NaverID);
		url.append("&client_secret=").append(NaverPW);
		url.append("&code=").append(code);
		url.append("&state=").append(state);

		String response = common.requestAPI(url.toString());
		JSONObject json = new JSONObject(response);
		String token = json.getString("access_token");
		String type = json.getString("token_type");

		// curl -XGET "https://openapi.naver.com/v1/nid/me" \
		// -H "Authorization: {token_type} {access_token}
		response = common.requestAPI("https://openapi.naver.com/v1/nid/me", type + " " + token);

		json = new JSONObject(response);

		if (json.getString("resultcode").equals("00")) {

			MemberVO vo = new MemberVO();
			vo.setSocial("N"); // 네이버 로그인이므로N 카카오는 K로 할 예정

			// 우리가 원하는 프로필 정보는 "response"에 있었다.
			json = json.getJSONObject("response");
			vo.setUserid(json.getString("id"));
			vo.setName(jsonValue("nickname", json));
			if (vo.getName().isEmpty())
				vo.setName(jsonValue("name", json, "..."));
			vo.setEmail(jsonValue("email", json));
			vo.setGender(jsonValue("gender", json, "M").equals("M") ? "남" : "여");
			vo.setProfile(jsonValue("profile_image", json));
			vo.setPhone(jsonValue("mobile", json));

			if (member.member_idCheck(vo.getUserid()) == 1) {
				member.member_myInfo_update(vo);
			} else {
				member.member_join(vo);
			}
			// 세션에 로그인정보를 담는다
			session.setAttribute("loginInfo", vo);

		}
		return "redirect:/";
	}

	private String jsonValue(String key, JSONObject json) {
		return json.has(key) ? json.getString(key) : "";
	}

	private String jsonValue(String key, JSONObject json, String defaultValue) {
		return json.has(key) ? json.getString(key) : defaultValue;
	}

	private String kakaoID = "8aaf02d56e30aa6fd895baa3b52265d2";

	@RequestMapping("/kakaoLogin.mb")
	public String kakao_login(HttpServletRequest request) {
		// https://kauth.kakao.com/oauth/authorize?response_type=code
		// &client_id=${REST_API_KEY}
		// &redirect_uri=${REDIRECT_URI}
		StringBuffer url = new StringBuffer("https://kauth.kakao.com/oauth/authorize?response_type=code");
		url.append("&client_id=").append(kakaoID);
		url.append("&redirect_uri=").append(common.appURL(request)).append("/kakaocallback.mb");
		return "redirect:" + url.toString();
	}

	@RequestMapping("/kakaocallback.mb")
	public String kakaoCallback(String code, HttpSession session) {
		if (code != null) {
			// 접근토큰 발급 요청
			StringBuffer url = new StringBuffer("https://kauth.kakao.com/oauth/token?grant_type=authorization_code");
			url.append("&client_id=").append(kakaoID);
			url.append("&code=").append(code);
			String tokens = common.requestAPI(url.toString());

			JSONObject json = new JSONObject(tokens);
			String type = json.getString("token_type");
			String token = json.getString("access_token");
			url = new StringBuffer("https://kapi.kakao.com/v2/user/me");
			String profile = common.requestAPI(url.toString(), type + " " + token);

			json = new JSONObject(profile);
			if (!json.isEmpty()) {

				MemberVO vo = new MemberVO();
				vo.setSocial("K");
				vo.setUserid(json.get("id").toString()); 

				json = json.getJSONObject("kakao_account");
				vo.setName(jsonValue("name", json, "noname"));
				vo.setEmail(jsonValue("email", json));
				vo.setGender(jsonValue("gender", json, "male").equals("male") ? "남" : "여");
				vo.setPhone(jsonValue("phone_number", json));

				if (json.has("profile")) {
					json = json.getJSONObject("profile");
					if(vo.getName().equals("noname")) vo.setName(jsonValue("nickname",json,"noname"));
					vo.setProfile(jsonValue("profile_image_url", json));
				}

				if (member.member_idCheck(vo.getUserid()) == 1) { 
					member.member_myInfo_update(vo);
				} else { 
					member.member_join(vo);
				}
				
				session.setAttribute("loginInfo", vo);
			}
		}

		return "redirect:/";
	}
}
