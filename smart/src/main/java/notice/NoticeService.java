package notice;

import java.util.List;

public interface NoticeService {
	int notice_insert(NoticeVO vo);
	List<NoticeVO> notice_list();
	NoticeVO notice_info(int id);
	int notice_update(NoticeVO vo);
	int notice_delete(int id);
	
	int notice_readcnt_update(int id);
	
	
	//페이지 처리를 위한 공지글 목록 조회
	NoticePageVO notice_list(NoticePageVO page);
	// 메소드 만들고 컨트롤러로 가자.
	
	
	//답글을 위한 메소드
	int notice_reply_insert(NoticeVO vo);
}
