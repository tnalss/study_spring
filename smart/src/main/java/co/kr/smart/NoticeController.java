package co.kr.smart;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

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
import notice.NoticePageVO;
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
	
	
	//페이지 처리를 위해 NoticePageVO 값을 파라미터로 받아옴.
	// 처음에 들어온 경우 페이지 정보를 줌
	@RequestMapping("/list.no")
	public String list(HttpSession session , Model model, NoticePageVO page) {
		// 세션에 카테고리 담음.
		session.setAttribute("category","no");
		
		// 비지니스 로직 - db에서 공지글목록을 조회
		// model에 attribute로 담는다.
		model.addAttribute("list",service.notice_list());
		
		
		//1페이지라고 알ㄹ랴줌//list.jsp에서 페이지알려줬음
		page.setCurPage(page.getCurPage());
		model.addAttribute("page", service.notice_list(page));
		//응답화면에서 page속의 값을 출력할 수 있도록 변경!
		
		//응답화면 연결
		return "notice/list";
	}
	
	@RequestMapping("/info.no")
	public String info(int id,Model model,NoticePageVO page) {
		
		//화면에서 사용할 수 있도록 enter 키값을 담아둔다.
		model.addAttribute("crlf","\r\n");
		model.addAttribute("page",page);
		
		
		
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
	public String modify (int id, Model model, NoticePageVO page) {
		model.addAttribute("vo",service.notice_info(id));
		model.addAttribute("page",page);
		return "notice/modify";
	}

	@RequestMapping("/update.no")
	public String update (NoticeVO vo ,MultipartFile file
			,HttpServletRequest request, NoticePageVO page) throws UnsupportedEncodingException {
		
		//수정전 공지글 정보 조회
		NoticeVO before = service.notice_info(vo.getId());	
		
		
		//파일을 첨부하는 경우 : 원래없는데 새로 첨부, 원래 있는데 수정 첨부
		if( ! file.isEmpty() ) {
			vo.setFilename( file.getOriginalFilename() );
			vo.setFilepath( common.fileUplaod("notice", file, request) );
			//sql문도 수정필요
			//수정전 첨부되어있는 파일이 있으면 물리적 파일도 삭제
			common.fileDelete( before.getFilepath() , request );
			
		} else {
		//파일을 첨부하지 않는 경우
			//원래있어서 그대로 두는 경우(원래없는데 첨부하는 경우와 동일), 원래 있었지만 지우는 경우
			
			//jquery이용, input hidden이용 filename파라미터 값을 받아 데이터객체에 담겨있음
			if( vo.getFilename().isEmpty() ) {
				//원래 O -> 삭제 
				common.fileDelete( before.getFilepath() , request );
			} else {
				//원래 O 그대로 두는 경우
				vo.setFilename(before.getFilename());
				vo.setFilepath(before.getFilepath());
			}
		}
		service.notice_update(vo);
		return "redirect:info.no?id="+vo.getId()+"&curPage="
		+page.getCurPage()+"&search="+page.getSearch()+"&keyword="+
		URLEncoder.encode(page.getKeyword(),"utf-8");
	//파라미터에 한글이 있는경우를 위한 인코딩 처리!
	}
	

	
	@RequestMapping("/delete.no")
	public String delete (int id, HttpServletRequest request, NoticePageVO page) throws UnsupportedEncodingException {
		
		NoticeVO vo = service.notice_info(id);
		
		service.notice_delete(id);
		
		common.fileDelete(vo.getFilepath(),request);
		
	
		
		
		return "redirect:list.no?id="+vo.getId()+"&curPage="
				+page.getCurPage()+"&search="+page.getSearch()+"&keyword="+
				URLEncoder.encode(page.getKeyword(),"utf-8");
	}
	
	
	//답글쓰기 화면 요청
	@RequestMapping("/reply.no")
	public String reply(int id, Model model) {
		//해당 글의 원글정보를 조회해와야함.
		NoticeVO vo =  service.notice_info(id);
		model.addAttribute("vo",vo);
		return "notice/reply";
	}
	
	@RequestMapping("/reply_insert.no")
	public String reply_insert(NoticeVO vo) {
		service.notice_reply_insert(vo);
		return "redirect:list.no";
	}
	
}
