package co.kr.smart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import common.CommonService;
import notice.NoticeService;
import notice.NoticeVO;

@Controller
public class NoticeController {
	@Autowired private CommonService common;
	private NoticeService service; 
	public NoticeController(NoticeService notice) {
		this.service = notice;
	}
	
	@ResponseBody
	@RequestMapping(value="/download.no",produces="text/html; charset=utf-8")
	public String download(int id,String url, HttpServletRequest request, HttpServletResponse response) throws Exception {
		NoticeVO vo = service.notice_info(id);
		//다운로드 메소드는 공통으로 뺀다.
		boolean download = common.fileDownload(vo.getFilename(), vo.getFilepath(), request, response);
		//Exception을 던지는 메소드로 만들었었음.
		//또 그냥 던져봄
		
		if(!download) {
			//첨부된 파일이 실제 물리적으로 존재하지 않는 경우
			StringBuffer msg = new StringBuffer("<script>");
			msg.append("alert('다운로드할 파일이 없습니다!'); location='");
			msg.append(url).append("'");
			//info에서 url도 jquery를 이용해서 보내주었음.
			
			msg.append("</script>");
		
			return msg.toString();
		} else
			
			return null;
		
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
		
		//화면에서 사용할 수 있도록 enter 키값을 담아둔다.
		model.addAttribute("crlf","\r\n");
		
		
		
		
		service.notice_readcnt_update(id);
		model.addAttribute("vo",service.notice_info(id));
		
		return "notice/info";
	}
	
	@RequestMapping("/new.no")
	public String write() {
		
		return "notice/write";
		
	}
	
	@RequestMapping("/insert.no")
	public String insert (NoticeVO vo, MultipartFile file, HttpServletRequest request) {
		
		//첨부파일이 있는 경우
		if( ! file.isEmpty() ) {
			vo.setFilename( file.getOriginalFilename() );
			vo.setFilepath( common.fileUplaod("notice", file, request) );
			
		}
		service.notice_insert(vo);
		
		
		return "redirect:list.no";
		
	}
	
	
	@RequestMapping("/modify.no")
	public String modify (int id, Model model) {
		model.addAttribute("vo",service.notice_info(id));
		return "notice/modify";
	}

	@RequestMapping("/update.no")
	public String update (NoticeVO vo) {
		service.notice_update(vo);
		return "redirect:info.no?id="+vo.getId();
	}
	
	@RequestMapping("/delete.no")
	public String delete (int id) {
		service.notice_delete(id);
		return "redirect:list.no";
	}
	
}
