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
	public int notice_insert(NoticeVO vo) {
		return sql.insert("notice.notice_insert",vo);

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
	public int notice_update(NoticeVO vo) {
		return sql.update("notice.notice_update",vo);
		
	}

	@Override
	public int notice_delete(int id) {
		return sql.delete("notice.notice_delete",id);

	}


	@Override
	public int notice_readcnt_update(int id) {
		return sql.update("notice.notice_readcnt_update",id);
	}


	@Override
	public NoticePageVO notice_list(NoticePageVO page) {
		//총 페이지 수를 위한 쿼리 날림
		
		//검색할수있또록 page도 같이 날려줌
		page.setTotalList( sql.selectOne("notice.count",page) );
		//매퍼 쿼리작성
		//pageVO에서 계산처리가 완료되었음.
		
		// 현재 페이지에 출력할 10건의 공지글 조회
		page.setList( sql.selectList("notice.list",page) ) ;
		//쿼리문 작성을 위해 매퍼로 ㄱㄱ
		//페이지에 10건의 정보를 담고 page를 반환
		//컨트롤러로 다시 ㄱㄱ
		
		return page;
	}


	@Override
	public int notice_reply_insert(NoticeVO vo) {
		
		return sql.insert("notice.reply_insert",vo);
	}

	
	

}
