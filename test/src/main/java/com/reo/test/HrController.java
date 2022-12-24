package com.reo.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import hr.EmployeeVO;
import hr.HrService;

@Controller
public class HrController {
	@Autowired
	private HrService hr;
	
	@RequestMapping("/list.hr")
	public String employee_list(Model model) {
		List<EmployeeVO> list = hr.employee_list();
		model.addAttribute("list",list);
		return "hr/list";
	}
	
	@RequestMapping("/info.hr")
	public String employee_info(int id,Model model) {
		EmployeeVO vo = hr.employee_info(id);
		model.addAttribute("vo",vo);
		return "hr/info";
	}
	
	@RequestMapping("/delete.hr")
	public String employee_delete(int id) {
		hr.employee_delete(id);
		return "redirect:list.hr";
	}
	
	@RequestMapping("/modify.hr")
	public String employee_modify(int id, Model model) {
		EmployeeVO vo = hr.employee_info(id);
		model.addAttribute("vo",vo);
		return "hr/modify";
	}
}
