package com.dormitory.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dormitory.dto.MemberDto;

@Repository
public interface MemberDao {

	/* 회원 가입 */
	public boolean insertMember(MemberDto memberDto);

	/* 로그인 */
	public List<Map<String, Object>> selectMember(Map<String, Object> paramMap);
}
