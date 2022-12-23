package notice;

import java.util.List;

import org.springframework.stereotype.Service;

@Service("notice")
public class NoticeServiceimpl implements NoticeService {

	private NoticeDAO dao;
	
	public NoticeServiceimpl(NoticeDAO dao) {
		this.dao = dao;
	}
	@Override
	public int notice_insert(NoticeVO vo) {
	
		return  dao.notice_insert(vo);
	}

	@Override
	public List<NoticeVO> notice_list() {
		return dao.notice_list();
	}

	@Override
	public NoticeVO notice_info(int id) {
		
		return dao.notice_info(id);
	}

	@Override
	public int notice_update(NoticeVO vo) {
		
		return dao.notice_update(vo);
	}

	@Override
	public int notice_delete(int id) {
		
		return dao.notice_delete(id);
	}
	@Override
	public int notice_readcnt_update(int id) {

		return dao.notice_readcnt_update(id);
	}
	@Override
	public NoticePageVO notice_list(NoticePageVO page) {
		
		return dao.notice_list(page);
	}
	@Override
	public int notice_reply_insert(NoticeVO vo) {
		
		return dao.notice_reply_insert(vo);
	}
	

}
