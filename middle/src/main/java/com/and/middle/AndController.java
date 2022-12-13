package com.and.middle;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import and.AndDAO;
import customer.CustomerVO;


@RestController
public class AndController {
	
	private AndDAO dao;
	public AndController(AndDAO dao) {
		this.dao = dao;
	}
	
	
	//@ResponseBody
	@RequestMapping("/and")
	public int test() {
		
		return 1;
	}
	//value="/and",produces="text/html;charset=utf-8"
	// 나중에 문제 생길 여지가 있기떄문에 String으로 바꿔주시는게 좋아요 감사합니다!
	
	 @RequestMapping(value = "/cus",produces="text/html;charset=utf-8")
	 public String customer() {
		 return new Gson().toJson(dao.customer_select());
	}
	 
	
	
	 @RequestMapping(value= "/andVo", produces = "text/html;charset=utf-8")
	 public String andVo() {
	  
		 List<CustomerVO> list = dao.customer_select();
		 for (CustomerVO v : list) {
			System.out.println(v.getEmail());
		}
		 
		 return new Gson().toJson(list.get(1));
	 }
}
