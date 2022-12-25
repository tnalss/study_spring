package com.reo.test.member;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO implements MemberService {
	@Autowired @Qualifier("maroo")
	SqlSession sql;
	
	@Override
	public MemberVO member_login(HashMap<String, String> map) {
		
		return sql.selectOne("member.login",map);
	}

	@Override
	public List<MemberVO> member_list() {
		
		return sql.selectList("member.list");
	}

	@Override
	public void member_password_update(MemberVO vo) {
		sql.update("member.pw",vo);
		
	}

	@Override
	public String member_salt(String userid) {
		return sql.selectOne("member.salt",userid);
	}

}
