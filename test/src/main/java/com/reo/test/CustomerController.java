package com.reo.test;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.reo.test.customer.CustomerService;
import com.reo.test.customer.CustomerVO;

@Controller
public class CustomerController {
	@Autowired private CustomerService customer;
	//여기에써진 서비스의 customer를 impl 클래스에서 @service("customer") 해줘야됨!!
		
	@RequestMapping("/list.cu")
	public String customer_list(Model model, HttpSession session) {
		session.setAttribute("category", "cu");
		
		List<CustomerVO> list = customer.customer_list();
		model.addAttribute("list",list);
		return "customer/list";
	}
	
	@RequestMapping("/info.cu")
	public String customer_info(int id, Model model) {
		CustomerVO vo = customer.customer_info(id);
		model.addAttribute("vo",vo);
		return "customer/info";
	}
	
	@RequestMapping("/delete.cu")
	public String customer_delete(int id) {
		customer.customer_delete(id);
		return "redirect:list.cu";
	}
	
	@RequestMapping("/modify.cu")
	public String customer_modify(int id, Model model) {
		CustomerVO vo = customer.customer_info(id);
		model.addAttribute(vo);
		
		return "customer/modify";
	}
}
