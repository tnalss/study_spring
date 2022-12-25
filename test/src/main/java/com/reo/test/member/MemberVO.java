package com.reo.test.member;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberVO {
	private String userid,userpw,name,gender,
			email,phone,post,address,admin,social,profile,salt;
	private Date birth;
}
