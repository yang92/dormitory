package com.dormitory.service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dormitory.dao.DorHolMngDao;
import com.dormitory.dao.DorPerMngDao;
import com.dormitory.dao.DorReqDao;
import com.dormitory.dto.DorReqDto;
import com.dormitory.dto.MemberDto;
import com.dormitory.util.DateUtil;

@Service
public class ReqService {
	@Autowired
	private DorPerMngDao dorPerMngDao;

	@Autowired
	private DorHolMngDao dorHolMngDao;

	@Autowired
	private DorReqDao dorReqDao;

	/* 외박 신청 확인 */
	public Map<String, Object> reqCheck(DorReqDto dorReqDto) {
		String startDate = dorReqDto.getStartDate();
		startDate = startDate.replace("-", "");
		String endDate = dorReqDto.getEndDate();
		endDate = endDate.replace("-", "");

		int resultCode = 0; // 오류 코드
		String resultStr = ""; // 오류 메세지

		Map<String, Object> resultMap = new HashMap<String, Object>();

		// 시작일자가 종료일자보다 큰 경우
		if (Integer.parseInt(startDate) > Integer.parseInt(endDate)) {
			resultCode = 200;
			resultStr = "신청불가\n시작일자가 종료일자보다 큼";
		} else {
			long diffDate = 0L;
			try {
				diffDate = DateUtil.diffOfDate(startDate, endDate);
				if (diffDate > 2) {
					resultCode = 300;
					resultStr = "신청불가\n2박 3일을 초과할 수 없음";
				} else {
					Map<String, Object> paramMap = new HashMap<String, Object>();
					paramMap.put("grade", dorReqDto.getGrade());
					paramMap.put("id", dorReqDto.getId());
					paramMap.put("startDate", startDate);
					paramMap.put("endDate", endDate);
					// 학년별 외박 신청 가능 일자 조회
					Integer posDate = dorPerMngDao.selectDorPerMngByGrade(paramMap);
					// 학년별 외박 신청 가늘 일자가 없을 때
					// 디폴트 가능 일자 = 10일
					if ( posDate == null ){
						posDate = 10;
					}
					
					// 현재까지 외박 사용 일자 조회
					int useDate = dorReqDao.selectDorReqByUseDate(paramMap);

					// 현재까지 사용일자 + 신청일자 > 가능일자
					if (useDate + diffDate > posDate) {
						resultCode = 400;
						resultStr = "신청불가\n" + "신청가능 일수 초과";
					} else {
						// 관리자 지정한 휴일 조회
						Boolean holidayCheck = dorHolMngDao.selectHolMngByDate(paramMap);
						// StartDate 와 EndDate 사이에 관리자 지정한 휴일이 없을 때
						// 디폴트 값 False
						if (holidayCheck == null ){
							holidayCheck = false;
						}
						
						if (!holidayCheck && diffDate > 1) {
							resultCode = 500;
							resultStr = "휴일 포함 2박 3일까지 가능";
						} else { // 정상 신청
							resultCode = 100;
							resultStr = "외박 신청 완료";
						}
					}
				}

			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		resultMap.put("resultCode", resultCode);
		resultMap.put("resultStr", resultStr);

		return resultMap;
	}

	/* 외박 신청 현황 조회 */
	public List<Map<String, Object>> state(String id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);

		List<Map<String, Object>> list = dorReqDao.selectDorReqById(paramMap);
		return list;
	}

	/* 외박 신청 */
	public boolean req(DorReqDto dorReqDto) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", dorReqDto.getId());
		paramMap.put("startDate", dorReqDto.getStartDate().replace("-", ""));
		paramMap.put("endDate", dorReqDto.getEndDate().replace("-", ""));
		return dorReqDao.insertDorReq(paramMap);
	}

	/* 외박 승인, 반려 */
	public boolean changeReq(DorReqDto dorReqDto) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("state", dorReqDto.getState());
		paramMap.put("exp", dorReqDto.getExp());
		paramMap.put("seqNo", dorReqDto.getSeqNo());

		return dorReqDao.updateDorReq(paramMap);
	}

	/* 관리자가 지정한 일수, 사용일수 조회 */
	public Map<String, Object> dateState(MemberDto memberDto) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", memberDto.getId());
		paramMap.put("grade", memberDto.getGrade());

		// 학년별 외박 신청 가능 일자 조회
		Integer posDate = dorPerMngDao.selectDorPerMngByGrade(paramMap);
		if ( posDate == null ){
			posDate = 10;
		}
		
		// 현재까지 외박 사용 일자 조회
		int useDate = dorReqDao.selectDorReqByUseDate(paramMap);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("posDate", posDate);
		result.put("useDate", useDate);
		return result;
	}
}
