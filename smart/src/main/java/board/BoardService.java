package board;

import java.util.List;

public interface BoardService {
	//crud
	//C방명록 신규저장
	int board_insert(BoardVO vo);
	//R방명록 목록조회 (페이지 처리)
	BoardPageVO board_list(BoardPageVO page);
	//R방명록 선택 글 조회
	BoardVO board_info(int id);
	
	//방명록 글조회수 처리
	int board_read(int id);
	
	//U방명록 글 변경저장
	int board_update(BoardVO vo);
	//D방명록 글 삭제
	int board_delete(int id);
	
	
	//방명록에 처뭅된 각 파일 정보 조회
	BoardFileVO board_file_info(int id);
	
	
	//첨부파일 목록 삭제
	int board_file_delete( String removed );
	
	
	
	// 댓글신규저장
	int board_comment_insert(BoardCommentVO vo);
	// 댓글목록조회
	List<BoardCommentVO> board_comment_list(int board_id);
	// 댓글번경저장
	int board_comment_update(BoardCommentVO vo);
	
	// 댓글삭제(댓글의 아이디)
	int board_comment_delete(int id);
	
	
}
