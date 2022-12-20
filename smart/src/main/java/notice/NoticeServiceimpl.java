package notice;

import java.util.List;

import org.springframework.stereotype.Service;

@Service("notice")
public class NoticeServiceimpl implements NoticeService {

	private NoticeDAO dao;
	private int cnt =0;
	public NoticeServiceimpl(NoticeDAO dao) {
		this.dao = dao;
	}
	@Override
	public int notice_insert(NoticeVO vo) {
		cnt = dao.notice_insert(vo);
		return cnt;
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
		cnt = dao.notice_update(vo);
		return cnt;
	}

	@Override
	public int notice_delete(int id) {
		cnt = dao.notice_delete(id);
		return cnt;
	}
	@Override
	public int notice_readcnt_update(int id) {
		cnt = dao.notice_readcnt_update(id);
		return cnt;
	}
	

}
