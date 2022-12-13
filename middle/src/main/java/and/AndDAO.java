package and;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import customer.CustomerVO;

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
}
