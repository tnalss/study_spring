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
}
