package visual;

import java.util.HashMap;
import java.util.List;

public interface VisualService {
	// 부서별 사원수 조회
	List<HashMap<String,Object>> department();
	
	// 채용인원수 (년도별/월별) 조회
	List<HashMap<String,Object>> hirement_year();
	List<HashMap<String,Object>> hirement_year(HashMap<String,Object> map);
	List<HashMap<String,Object>> hirement_month();
	
	// 상위 3위 채용 부서의 채용인원수 (년도별/월별) 조회
	List<HashMap<String,Object>> hirement_top3_year();
	List<HashMap<String,Object>> hirement_top3_year(HashMap<String,Object> map);
	List<HashMap<String,Object>> hirement_top3_month();
	
	
}
