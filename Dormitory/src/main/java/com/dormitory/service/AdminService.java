package com.dormitory.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dormitory.dao.DorHolMngDao;
import com.dormitory.dao.DorPerMngDao;
import com.dormitory.dto.DorHolMngDto;
import com.dormitory.dto.DorPerMngDto;

@Service
public class AdminService {

	@Autowired
	private DorHolMngDao dorHolMngDao;

	@Autowired
	private DorPerMngDao dorPerMngDao;

	/* 휴일 목록 조회 */
	public List<Map<String, Object>> getHolidayList(DorHolMngDto dorHolMngDto) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		return dorHolMngDao.selectHolMng(paramMap);
	}

	/* 휴일 등록 */
	public boolean setHoliday(DorHolMngDto dorHolMngDto) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("hDate", dorHolMngDto.gethDate().replace("-", ""));
		paramMap.put("hExp", dorHolMngDto.gethExp());
		paramMap.put("useYn", dorHolMngDto.getUseYn());

		// 등록된 같은 휴일 사용안함으로 변경
		dorHolMngDao.updateDorHolMngByUseYn(paramMap);

		return dorHolMngDao.insertDorHolMng(paramMap);
	}

	/* 휴일 수정 */
	public boolean changeHoliday(DorHolMngDto dorHolMngDto) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("hDate", dorHolMngDto.gethDate().replace("-", ""));
		paramMap.put("hExp", dorHolMngDto.gethExp());
		paramMap.put("useYn", dorHolMngDto.getUseYn());
		paramMap.put("seqNo", dorHolMngDto.getSeqNo());

		dorHolMngDao.updateDorHolMngByUseYn(paramMap);

		return dorHolMngDao.updateDorHolMng(paramMap);
	}

	/* 휴일 삭제 */
	public boolean deleteHoliday(DorHolMngDto dorHolMngDto) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("seqNo", dorHolMngDto.getSeqNo());

		return dorHolMngDao.deleteDorHolMng(paramMap);
	}

	/* 외박 일수 목록 조회 */
	public List<Map<String, Object>> getPermissionList(DorPerMngDto dorPerMngDto) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		return dorPerMngDao.selectDorPerMng(paramMap);
	}

	/* 외박 일수 등록 */
	public boolean setPermission(DorPerMngDto dorPerMngDto) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("grade", dorPerMngDto.getGrade());
		paramMap.put("posDate", dorPerMngDto.getPosDate().replace("-", ""));
		paramMap.put("useYn", dorPerMngDto.getUseYn());

		dorPerMngDao.updateDorPerMngByUseYn(paramMap);

		return dorPerMngDao.insertDorPerMng(paramMap);
	}

	/* 외박 일수 변경 */
	public boolean changePermission(DorPerMngDto dorPerMngDto) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("grade", dorPerMngDto.getGrade());
		paramMap.put("posDate", dorPerMngDto.getPosDate().replace("-", ""));
		paramMap.put("useYn", dorPerMngDto.getUseYn());
		paramMap.put("seqNo", dorPerMngDto.getSeqNo());

		dorPerMngDao.updateDorPerMngByUseYn(paramMap);

		return dorPerMngDao.updateDorPerMng(paramMap);
	}

	/* 외박 일수 삭제 */
	public boolean deletePermission(DorPerMngDto dorPerMngDto) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("seqNo", dorPerMngDto.getSeqNo());

		return dorPerMngDao.deleteDorPerMng(paramMap);
	}
}
