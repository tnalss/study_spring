package notice;

import java.util.List;

public interface NoticeService {
	void notice_insert(NoticeVO vo);
	List<NoticeVO> notice_list();
	NoticeVO notice_info(int id);
	void notice_update(NoticeVO vo);
	void notice_delete(int id);
	
	void notice_readcnt_update(int id);
}
