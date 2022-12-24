package com.reo.test.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("customer")
public class CustomerServiceimpl implements CustomerService {
	@Autowired private CustomerDAO dao;
	
	@Override
	public void customer_insert(CustomerVO vo) {
		dao.customer_insert(vo);
	}

	@Override
	public List<CustomerVO> customer_list() {
		
		return dao.customer_list();
	}

	@Override
	public void customer_update(CustomerVO vo) {
		dao.customer_update(vo);
	}

	@Override
	public void customer_delete(int id) {
		dao.customer_delete(id);
	}

	@Override
	public CustomerVO customer_info(int id) {
		return dao.customer_info(id);
	}

}
