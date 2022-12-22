package com.reo.test.customer;

import java.util.List;

public interface CustomerService {

	//CRUD
	void customer_insert(CustomerVO vo);
	List<CustomerVO> customer_list();
	void customer_update(CustomerVO vo);
	void customer_delete(int id);
	
	CustomerVO customer_info(int id);
	
}
