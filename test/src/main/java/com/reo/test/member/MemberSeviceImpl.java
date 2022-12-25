package com.reo.test.member;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("member")
public class MemberSeviceImpl implements MemberService {
	
	@Autowired private MemberDAO dao;
	
	@Override
	public MemberVO member_login(HashMap<String, String> map) {
		return dao.member_login(map);
	}

	@Override
	public List<MemberVO> member_list() {
		return dao.member_list();
	}

	@Override
	public void member_password_update(MemberVO vo) {
		dao.member_password_update(vo);
	}

	@Override
	public String member_salt(String userid) {
		return dao.member_salt(userid);
	}

	@Override
	public int member_idCheck(String userid) {
		return dao.member_idCheck(userid);
	}

	@Override
	public void member_myInfo_update(MemberVO vo) {
		dao.member_myInfo_update(vo);
	}

	@Override
	public void member_join(MemberVO vo) {
		dao.member_join(vo);
	}
	
	


}
