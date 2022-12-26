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
}
