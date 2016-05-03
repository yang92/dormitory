package com.dormitory.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface DorHolMngDao {

	/* 관리자가 지정한 휴일 조회 */
	public Boolean selectHolMngByDate(Map<String, Object> paramMap);

	/* 휴일 목록 조회 */
	public List<Map<String, Object>> selectHolMng(Map<String, Object> paramMap);

	/* 휴일 등록 */
	public boolean insertDorHolMng(Map<String, Object> paramMap);

	/* 휴일 수정 */
	public boolean updateDorHolMng(Map<String, Object> paramMap);

	/* 휴일 삭제 */
	public boolean deleteDorHolMng(Map<String, Object> paramMap);

	/* 휴일 사용안함 수정 */
	public boolean updateDorHolMngByUseYn(Map<String, Object> paramMap);
}
