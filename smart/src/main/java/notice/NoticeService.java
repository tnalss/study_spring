package notice;

import java.util.List;

public interface NoticeService {
	int notice_insert(NoticeVO vo);
	List<NoticeVO> notice_list();
	NoticeVO notice_info(int id);
	int notice_update(NoticeVO vo);
	int notice_delete(int id);
	
	int notice_readcnt_update(int id);
}
