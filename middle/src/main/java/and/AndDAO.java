package and;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import customer.CustomerVO;
import member.MemberVO;

@Repository
public class AndDAO {
	private SqlSession sql; 
	public AndDAO(@Qualifier("hanul") SqlSession sql) {
		this.sql = sql;
	}
	
	public int needOne() {
		
		return sql.selectOne("and.one");
	}

	public List<CustomerVO> customer_select() {
		
		return sql.selectList("and.customer");

	}
	public MemberVO member_login(HashMap<String, String> map) {
		return sql.selectOne("me.login",map);
	}
	
	public int insert(MemberVO vo) {
		return sql.insert("cu.insert",vo);
	}

}
