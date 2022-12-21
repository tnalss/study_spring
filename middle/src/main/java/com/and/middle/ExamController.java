package com.and.middle;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import and.ExamVO;

@RestController
public class ExamController {

	@RequestMapping(value = "/test1")
	public void test1() {

		System.out.println("요청들어옴");
	}
	
	@RequestMapping("/test2")
	public void test2(String str, int intval, Double dval) {
		
		System.out.println(str);
		System.out.println(intval);
		System.out.println(dval);
	}
	
	@RequestMapping("/test3")
	public String test3() {
		return "CSM";
	}

	@RequestMapping("/test4")
	public String test4() {
		ExamVO vo = new ExamVO(1,"test",123.45);
		vo.setiVal(1);
		vo.setsVal("test");
		vo.setdVal(123.45);
		
		return new Gson().toJson(vo);
	}
	
	@RequestMapping(value="/test5",produces="text/html;charset=utf-8")
	public String test5(String no) {
		
		
		
		int temp;

		try {
			temp = Integer.parseInt(no);
		} catch(Exception e ) {
			return "오류";
		}

		ArrayList<ExamVO> list = new ArrayList<>();
		
		for(int i=1; i <= temp ; i++) {
			list.add(new ExamVO(i,"test",123.45));
			
		}
		return new Gson().toJson(list);
	}
}
