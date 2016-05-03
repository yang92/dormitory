package com.dormitory.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dormitory.dao.MemberDao;
import com.dormitory.dto.MemberDto;

@Service
public class MemberService {
	@Autowired
	private MemberDao memberDao;

	/* 회원가입 */
	public boolean join(MemberDto memberDto) {
		// Map<String, Object> paramMap = new HashMap<String, Object>();
		// paramMap.put("id", memberDto.getId());
		// paramMap.put("pw", memberDto.getPw());
		// paramMap.put("name", memberDto.getName());
		// paramMap.put("grade", memberDto.getGrade());

		return memberDao.insertMember(memberDto);
	}

	/* 로그인 */
	public int login(MemberDto memberDto) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", memberDto.getId());
		List<Map<String, Object>> result = memberDao.selectMember(paramMap);
		int resultInt = 0;
		if (result != null && result.size() > 0) {
			if (memberDto.getPw().equals(result.get(0).get("PW"))) {
				// PW 맞는 경우
				resultInt = 1;
				memberDto.setName((String) result.get(0).get("NAME"));
				memberDto.setGrade((String) result.get(0).get("GRADE"));
			} else {
				// PW 틀린 경우
				resultInt = 0;
			}
		} else {
			resultInt = 2; // ID 없음
		}
		return resultInt;
	}
}
