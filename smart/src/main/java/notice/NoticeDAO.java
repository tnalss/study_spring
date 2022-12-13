package notice;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class NoticeDAO implements NoticeService {
	private SqlSession sql; 
	public NoticeDAO(@Qualifier("hanul") SqlSession sql) {
		this.sql = sql;
	}
	
	
	@Override
	public void notice_insert(NoticeVO vo) {
		sql.insert("notice.notice_insert",vo);
	

	}

	@Override
	public List<NoticeVO> notice_list() {
		
		return sql.selectList("notice.notice_list");
	}

	@Override
	public NoticeVO notice_info(int id) {
		return sql.selectOne("notice.notice_info",id);
	}

	@Override
	public void notice_update(NoticeVO vo) {
		sql.update("notice.notice_update",vo);

	}

	@Override
	public void notice_delete(int id) {
		sql.delete("notice.notice_delete",id);

	}


	@Override
	public void notice_readcnt_update(int id) {
		sql.update("notice.notice_readcnt_update",id);
	}

	
	

}
