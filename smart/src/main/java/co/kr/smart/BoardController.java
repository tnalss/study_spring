package co.kr.smart;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

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
	
}
