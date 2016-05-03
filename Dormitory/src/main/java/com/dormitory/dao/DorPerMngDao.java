package com.dormitory.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface DorPerMngDao {

	/* 학년별 외박 사용 가능 일수 조회 */
	public Integer selectDorPerMngByGrade(Map<String, Object> paramMap);

	/* 학년별 외박 사용 가능 일수 목록 조회 */
	public List<Map<String, Object>> selectDorPerMng(Map<String, Object> paramMap);

	/* 외박 사용 가능 일수 등록 */
	public boolean insertDorPerMng(Map<String, Object> paramMap);

	/* 등록된 학년 외박 사용 유무 변경 */
	public boolean updateDorPerMngByUseYn(Map<String, Object> paramMap);

	/* 등록된 학년 외박 변경 */
	public boolean updateDorPerMng(Map<String, Object> paramMap);

	/* 등록된 학년 외박 삭제 */
	public boolean deleteDorPerMng(Map<String, Object> paramMap);
}
