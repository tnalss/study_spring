package co.kr.smart;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import board.BoardPageVO;
import board.BoardService;
import board.BoardVO;

@Controller
public class BoardController {
	@Autowired private BoardService board;
		
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
	
	@RequestMapping("/insert.bo")
	public String insert(BoardVO vo) {
			board.board_insert(vo);
		return "redirect:/list.bo";
	}
}
