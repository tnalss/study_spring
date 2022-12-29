package co.kr.smart;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import board.BoardCommentVO;
import board.BoardFileVO;
import board.BoardPageVO;
import board.BoardService;
import board.BoardVO;
import common.CommonService;

@Controller
public class BoardController {
	@Autowired private BoardService board;
	@Autowired private CommonService common;
	
	@RequestMapping("/list.bo")
	public String list(HttpSession session, Model model, BoardPageVO page) {
		
		session.setAttribute("category", "bo");

		model.addAttribute("page", board.board_list(page));
		
		
		return "board/list";
	}
	
	@RequestMapping("/new.bo")
	public String board() {
		return "board/new";
	}
	
	private void attachedFile(BoardVO vo, MultipartFile file[], HttpServletRequest request) {
		List<BoardFileVO> files = null;
		for( MultipartFile attached : file ) {
			if(attached.isEmpty()) continue;
			if(files==null) files = new ArrayList<BoardFileVO>();
			
		BoardFileVO fileVO	= new BoardFileVO();
		fileVO.setFilename( attached.getOriginalFilename() );
		fileVO.setFilepath( common.fileUplaod("board", attached, request));
		files.add(fileVO);
		
		}
		vo.setFileList(files);
	}
	
	@RequestMapping("/insert.bo")
	public String insert(BoardVO vo, MultipartFile file[], HttpServletRequest request) {
		//첨부파일이 있는 경우
	if(file.length>1) {
		attachedFile(vo,file,request);
		
	}
		
			board.board_insert(vo);
		return "redirect:/list.bo";
	}
	
	
	@RequestMapping("/info.bo")
	public String info(int id, BoardPageVO page,Model model ) {
		
		board.board_read(id);
		model.addAttribute("vo",board.board_info(id));
		model.addAttribute("page",page);
		return "board/info";
	}
	
//@ResponseBody
//	@RequestMapping(value = "/download.bo" , produces="text/html; charset=utf-8")
	@RequestMapping("/download.bo")
	public String download(int file, String url,int id, Model model,
			BoardPageVO page, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 선택한 파일정보를 DB에서 조회해온다.
		BoardFileVO vo = board.board_file_info(file);
		
		// 서버시스템의 물리적위치의 파일을 복사해서 다운로드한다.
		boolean download = common.fileDownload(vo.getFilename(),vo.getFilepath(), request, response);
		
		/*
		 * responsebody일때
		if(download) {
			return null;
		} else {
			StringBuffer msg = new StringBuffer();
			msg.append("<script>");
			msg.append("alert('삭제할파일이 없습니다'); location='").append(url).append("?id=").append(id).append("'");
			msg.append("</script>");
			return msg.toString();
		}
	*/
		model.addAttribute("url","info.bo");
		model.addAttribute("page",page);
		model.addAttribute("id",id);
		model.addAttribute("download",true);
		return "board/redirect";
	}
	
	@RequestMapping("/remove.bo")
	public String remove(int id , HttpServletRequest request, BoardPageVO page, Model model) {

		
		//첨부파일 정보를 db에서 조회해와서 물리적인 파일도 삭제
		List<BoardFileVO> files = board.board_info(id).getFileList();
		
		//해당 글의 정보를 db에서 삭제가 정말 되었을경우
		if( board.board_delete(id) ==1 ) {
			//db에서는 on delete cascade에 의해 첨부파일 정보가 삭제되지만 물리적인 파일은
			//존재하므로 물리적인 파일도 삭제되도록 한다.
			for(BoardFileVO file: files) {
				common.fileDelete(file.getFilepath(), request);
			}
		}
		
		model.addAttribute("url","list.bo");
		model.addAttribute("download",false);
		model.addAttribute("page",page);
		
		return "board/redirect";
	}
	
	@RequestMapping("/modify.bo")
	public String modify(Model model, int id, BoardPageVO page) {
		//선택한 글의 정보를 db에서 조회해와서 수정화면에 출력
		
		model.addAttribute("vo", board.board_info(id));
		model.addAttribute("page",page);
		
		return "board/modify";
	}
	

	@RequestMapping("/update.bo")
	public String update(BoardPageVO page, BoardVO vo, Model model, String removed, HttpServletRequest request, MultipartFile[] file) {
		
		//새로 추가선택, 변경선택한 파일이 있는 경우
		if(file.length>1) {
			attachedFile(vo,file,request);
			
		}
		
		//화면에서 입력한 정보로 변경저장
		if ( board.board_update(vo) ==1 ) {
			//삭제할 첨부파일이 있는경우
			if( !removed.isEmpty() ) {
				//물리적파일 삭제하려면 db에서 삭제하기 전에 filepath를 조회
				List<BoardFileVO> files = board.board_info(vo.getId()).getFileList();
				
				//DB에서 삭제대상 데이터행을 삭제 
				if ( board.board_file_delete(removed)>0 ) {
				//물리적인 파일도 삭제
					for (BoardFileVO f : files) {
						if(removed.contains(String.valueOf(f.getId())))
							common.fileDelete(f.getFilepath(),request );
					}
				}
			 
			}
		}
		model.addAttribute("id",vo.getId());
		model.addAttribute("url","info.bo");
		model.addAttribute("page",page);
		model.addAttribute("download",false);
		model.addAttribute("crlf","\r\n");
		model.addAttribute("lf","\n");
		
		//응답화면 연결
		return "board/redirect";
	}
	
	// 방명록 댓글저장처리 요청
	@ResponseBody
	@RequestMapping("/board/comment/insert")
	public boolean comment_regist(BoardCommentVO vo) {
		//화면에서 입력한 정보를 db에 저장
		return board.board_comment_insert(vo) == 1 ? true:false;
		
	}
	
	//방명록 댓글 목록화면 요청
	@RequestMapping("/board/comment/list/{id}")
	public String comment_list(@PathVariable int id,Model model) {
		//db에서 댓글목록을 조회해와서 목록화면에 출력 -> Model에 담는다.
		model.addAttribute("list", board.board_comment_list(id));
		return "board/comment/comment_list";
	}
}
