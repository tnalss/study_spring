package co.kr.smart;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import notice.NoticeService;
import notice.NoticeVO;

@Controller
public class NoticeController {

	private NoticeService service; 
	public NoticeController(NoticeService notice) {
		this.service = notice;
	}
	
	@RequestMapping("/list.no")
	public String list(HttpSession session , Model model) {
		// 세션에 카테고리 담음.
		session.setAttribute("category","no");
		
		// 비지니스 로직 - db에서 공지글목록을 조회
		// model에 attribute로 담는다.
		model.addAttribute("list",service.notice_list());
		//응답화면 연결
		return "notice/list";
	}
	
	@RequestMapping("/info.no")
	public String info(int id,Model model) {
		service.notice_readcnt_update(id);
		model.addAttribute("vo",service.notice_info(id));
		
		return "notice/info";
	}
	
	@RequestMapping("/new.no")
	public String write() {
		
		return "notice/write";
		
	}
	
	@RequestMapping("/insert.no")
	public String insert (NoticeVO vo) {
		service.notice_insert(vo);
		return "redirect:list.no";
		
	}
	
	
	@RequestMapping("modify.no")
	public String modify (int id, Model model) {
		model.addAttribute("vo",service.notice_info(id));
		return "notice/modify";
	}

	@RequestMapping("update.no")
	public String update (NoticeVO vo) {
		service.notice_update(vo);
		return "redirect:info.no?id="+vo.getId();
	}
	
	@RequestMapping("delete.no")
	public String delete (int id) {
		service.notice_delete(id);
		return "redirect:list.no";
	}
	
}
