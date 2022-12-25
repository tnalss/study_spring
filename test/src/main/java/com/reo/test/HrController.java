package com.reo.test;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import hr.DepartmentVO;
import hr.EmployeeVO;
import hr.HrService;
import hr.JobVO;

@Controller
public class HrController {
	@Autowired
	private HrService hr;
	
	@RequestMapping("/list.hr")
	public String employee_list(Model model, HttpSession session) {
		
		session.setAttribute("category", "hr");
		
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
		
		model.addAttribute("departments",hr.department_list());
		
		List<JobVO> jobs = hr.job_list();
		model.addAttribute("jobs",jobs);
		
		return "hr/modify";
	}
	
	@RequestMapping("/update.hr")
	public String employee_update(EmployeeVO vo) {
		
		hr.employee_update(vo);		
		return "redirect:info.hr?id="+vo.getEmployee_id();
	}
	
	@RequestMapping("/new.hr")
	public String employee_new(Model model) {
		List<JobVO> jobs = hr.job_list();
		List<DepartmentVO> departments = hr.department_list();
		model.addAttribute("jobs",jobs);
		model.addAttribute("departments",departments);
		return "hr/new";
	}
	
	@RequestMapping("/insert.hr")
	public String employee_insert(EmployeeVO vo) {
		hr.employee_insert(vo);
		return "redirect:list.hr";
	}
	
}
