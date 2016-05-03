package com.dormitory.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface DorReqDao {

	/* 현재까지 외박 사용 일수 조회 */
	public int selectDorReqByUseDate(Map<String, Object> paramMap);

	/* 외박 신청 현황 조회 */
	public List<Map<String, Object>> selectDorReqById(Map<String, Object> paramMap);

	/* 외박 신청 */
	public boolean insertDorReq(Map<String, Object> paramMap);

	/* 외박 승인, 반려 */
	public boolean updateDorReq(Map<String, Object> paramMap);
}
