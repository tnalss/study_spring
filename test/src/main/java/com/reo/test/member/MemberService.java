package com.reo.test.member;

import java.util.HashMap;
import java.util.List;

public interface MemberService {

	MemberVO member_login(HashMap<String,String> map);

	List<MemberVO> member_list();
	void member_password_update(MemberVO vo);
	
	String member_salt(String userid);

	int member_idCheck(String userid);
	void member_myInfo_update(MemberVO vo);
	void member_join(MemberVO vo);
}
