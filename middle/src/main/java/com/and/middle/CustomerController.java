package com.and.middle;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import customer.CustomerVO;

@RestController
public class CustomerController {
 @Autowired @Qualifier("hanul") SqlSession sql;
	
	@RequestMapping(value="/list.cu",produces="text/html;charset=utf-8")
	public String select(String no) {
		List<CustomerVO> list =   sql.selectList("cu.select");

		return new Gson().toJson(list);
	}
	
	@RequestMapping(value="/delete.cu",produces="text/html;charset=utf-8")
	public String delete(int id) {
	
		if (sql.delete("cu.delete",id)>0) return "성공";

		return "실패";
	}
}
