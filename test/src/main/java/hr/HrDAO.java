package hr;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
@Repository
public class HrDAO implements HrService {
	@Autowired @Qualifier("hr")
	
	private SqlSession sql;
	
	@Override
	public List<EmployeeVO> employee_list() {
		return sql.selectList("hr.emp_list");
	}

	@Override
	public EmployeeVO employee_info(int employee_id) {
		return sql.selectOne("hr.emp_info",employee_id);
	}

	@Override
	public void employee_delete(int employee_id) {
		sql.delete("hr.emp_delete",employee_id);

	}

	@Override
	public void employee_update(EmployeeVO vo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void employee_insert(EmployeeVO vo) {
		// TODO Auto-generated method stub

	}

}
