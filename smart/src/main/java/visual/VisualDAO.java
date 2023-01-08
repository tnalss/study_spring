package visual;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public class VisualDAO implements VisualService {
	@Autowired @Qualifier("hr")
	private SqlSession sql;
	
	@Override
	public List<HashMap<String, Object>> department() {
		return sql.selectList("visual.department");
	}

	@Override
	public List<HashMap<String, Object>> hirement_year() {
		return sql.selectList("visual.hirement_year");
	}

	@Override
	public List<HashMap<String, Object>> hirement_month() {
		return sql.selectList("visual.hirement_month");
	}

	@Override
	public List<HashMap<String, Object>> hirement_top3_year() {
		
		return sql.selectList("visual.hirement_top3_year");
	}

	@Override
	public List<HashMap<String, Object>> hirement_top3_month() {
		
		return sql.selectList("visual.hirement_top3_month");
	}

	@Override
	public List<HashMap<String, Object>> hirement_year(HashMap<String, Object> map) {
		return sql.selectList("visual.hirement_year",map);
	}

	@Override
	public List<HashMap<String, Object>> hirement_top3_year(HashMap<String, Object> map) {
		//'2001' y2001, '2002' y2002, '2003' y2003, '2004' y2004, '2005' y2005
    //, '2006' y2006, '2007' y2007, '2008' y2008, '2009' y2009, '2010' y2010
		String range="";
		int begin = Integer.parseInt( map.get("begin").toString() );
		int end = Integer.parseInt( map.get("end").toString() );
		for(int year = begin ; year <= end ; year++) {
			range += (range.isEmpty()?"":", " )+ "'"+year +"' y"+year;
		}
		map.put("range", range);
		return sql.selectList("visual.hirement_top3_year",map);
	}

}
