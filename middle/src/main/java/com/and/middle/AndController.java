package com.and.middle;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.google.gson.Gson;

import common.CommonService;
import customer.CustomerVO;
import member.MemberVO;

@RestController
public class AndController {
	@Autowired
	private CommonService common;
	
	//private AndDAO dao;
//
//	public AndController(AndDAO dao) {
//		this.dao = dao;
//	}

	// @ResponseBody
	@RequestMapping("/and")
	public int test() {

		return 1;
	}
	// value="/and",produces="text/html;charset=utf-8"
	// 나중에 문제 생길 여지가 있기떄문에 String으로 바꿔주시는게 좋아요 감사합니다!

//	@RequestMapping(value = "/cus", produces = "text/html;charset=utf-8")
//	public String customer() {
//		return new Gson().toJson(dao.customer_select());
//	}
//
//	@RequestMapping(value = "/memberOne", produces = "text/html;charset=utf-8")
//	public String memberOne() {
//		List<CustomerVO> list = dao.customer_select();
//		return new Gson().toJson(list.get(0));
//	}
//
//	@RequestMapping(value = "/andVo", produces = "text/html;charset=utf-8")
//	public String andVo() {
//		List<CustomerVO> list = dao.customer_select();
//		for (CustomerVO v : list) {
//			System.out.println(v.getEmail());
//		}
//		return new Gson().toJson(list.get(1));
//	}

//	@RequestMapping(value = "/login", produces = "text/html;charset=utf-8")
//	public String login(String email, String pw) {
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", email);
//		map.put("pw", pw);
//		MemberVO vo = dao.member_login(map);
//
//		if (vo != null) {
//			System.out.println(email + " : " + pw);
//			return new Gson().toJson(vo);
//		} else {
//			System.out.println(email + " : " + pw);
//			return "실패";
//		}
//	}

	@RequestMapping("/social.me")
	public void kakao_login(String email) {
		System.out.println(email);

	}

	@Autowired
	@Qualifier("hanul")
	private SqlSession sql;
	
	// 안드로이드에서 보낸거 받는 처리
	@RequestMapping("/join.me")
	public String join(String param, HttpServletRequest req) {
		MemberVO vo = new Gson().fromJson(param, MemberVO.class);
		MultipartRequest mReq = (MultipartRequest) req;
		MultipartFile file = mReq.getFile("file");
		if (file != null) {
			System.out.println(file.getOriginalFilename());
			System.out.println(file.getName());
			String path =common.fileUplaod("and", file, req);
			System.out.println(path);
			vo.setFile_path(path);
		}
		int result=  sql.insert("cu.insert", vo ) ; ////dao.insert(vo);
		
		return new Gson().toJson(result+"");

	}
}
