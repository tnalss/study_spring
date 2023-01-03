package co.kr.smart;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import visual.Visualimpl;

@Controller
public class VisualizationController {
	@Autowired private Visualimpl service;
	
	// 시각화 화면 요청
	@RequestMapping("/list.vi")
	public String list(HttpSession session) {
		session.setAttribute("category", "vi");
		return "visual/list";
	}
	
	//부서별 사원수 정보 조호 ㅣ요청
	@ResponseBody
	@RequestMapping("/visual/department")
	public List<HashMap<String, Object>> department() {
		List<HashMap<String,Object>> list = service.department();
		return list;
	}
	
	
	//년도별 채용인원수 정보 조회요청
	@ResponseBody
	@RequestMapping("/visual/hirement/year")
	public Object hirement_year() {
		 List<HashMap<String,Object>> list = service.hirement_year();
		 
		 return list;
	}
	
	//월별 채용인워수 정보 조회 요청
	@ResponseBody
	@RequestMapping("/visual/hirement/month")
	public Object hirement_month() {
		 List<HashMap<String,Object>> list = service.hirement_month();
		 
		 return list;
	}
	
	//상위 3개부서의 년도별 채용인원수 정보 조회요청
	@ResponseBody
	@RequestMapping("visual/hirement/top3/year")
	public Object hirement_top3_year() {
		return service.hirement_top3_year();
	}
	
	//월별
	@ResponseBody
	@RequestMapping("visual/hirement/top3/month")
	public Object hirement_top3_month() {
		return service.hirement_top3_month();
	}
}
