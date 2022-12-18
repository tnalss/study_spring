package com.reo.test;

import java.util.List;

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
	public String customer_list(Model model) {
		List<CustomerVO> list = customer.customer_list();
		model.addAttribute("list",list);
		return "customer/list";
	}
}
