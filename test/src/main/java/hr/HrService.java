package hr;

import java.util.List;

public interface HrService {
	List<EmployeeVO> employee_list();
	EmployeeVO employee_info(int employee_id);
	void employee_delete(int employee_id);
	void employee_update(EmployeeVO vo);
	void employee_insert(EmployeeVO vo);
}
