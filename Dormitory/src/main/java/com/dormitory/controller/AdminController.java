package com.dormitory.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dormitory.dto.DorHolMngDto;
import com.dormitory.dto.DorPerMngDto;
import com.dormitory.dto.DorReqDto;
import com.dormitory.service.AdminService;
import com.dormitory.service.ReqService;

@Controller
public class AdminController {
	@Autowired
	private AdminService adminService;

	@Autowired
	private ReqService reqService;

	@RequestMapping("/adminMain.admin")
	public ModelAndView adminMain(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("adminMain");
		return mav;
	}

	/* 휴일 관리 */
	@RequestMapping(value = "/holiday.admin", method = RequestMethod.GET)
	public ModelAndView holiday(HttpServletRequest request,	@ModelAttribute("dorHolMngDto") DorHolMngDto dorHolMngDto) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("list", adminService.getHolidayList(dorHolMngDto));
		mav.setViewName("holiday");
		return mav;
	}

	/* 휴일 등록 */
	@RequestMapping(value = "/holiday.admin", method = RequestMethod.POST)
	public ModelAndView holidayReg(HttpServletRequest request,
			@ModelAttribute("dorHolMngDto") DorHolMngDto dorHolMngDto) {
		ModelAndView mav = new ModelAndView();
		adminService.setHoliday(dorHolMngDto);
		mav.setViewName("redirect:/holiday.admin");
		return mav;
	}

	/* 휴일 수정 */
	@RequestMapping("/holidayChange.admin")
	public ModelAndView holidayChange(HttpServletRequest request,
			@ModelAttribute("dorHolMngDto") DorHolMngDto dorHolMngDto) {
		ModelAndView mav = new ModelAndView();

		boolean result = adminService.changeHoliday(dorHolMngDto);
		mav.addObject("result", result);
		mav.setViewName("JSON");
		return mav;
	}

	/* 휴일 삭제 */
	@RequestMapping("/holidayDelete.admin")
	public ModelAndView holidayDelete(HttpServletRequest request,
			@ModelAttribute("dorHolMngDto") DorHolMngDto dorHolMngDto) {
		ModelAndView mav = new ModelAndView();

		boolean result = adminService.deleteHoliday(dorHolMngDto);
		mav.addObject("result", result);
		mav.setViewName("JSON");
		return mav;
	}

	/* 외박 일수 관리 화면으로 이동 */
	@RequestMapping(value = "/permission.admin", method = RequestMethod.GET)
	public ModelAndView permissionForm(HttpServletRequest request,
			@ModelAttribute("dorPerMngDto") DorPerMngDto dorPerMngDto) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("list", adminService.getPermissionList(dorPerMngDto));

		mav.setViewName("permission");
		return mav;
	}

	/* 외박 일수 등록 */
	@RequestMapping(value = "/permission.admin", method = RequestMethod.POST)
	public ModelAndView permission(HttpServletRequest request,
			@ModelAttribute("dorPerMngDto") DorPerMngDto dorPerMngDto) {
		ModelAndView mav = new ModelAndView();

		adminService.setPermission(dorPerMngDto);

		mav.setViewName("redirect:/permission.admin");
		return mav;
	}

	/* 외박 일수 변경 */
	@RequestMapping(value = "/permissionChange.admin")
	public ModelAndView permissionChange(HttpServletRequest request,
			@ModelAttribute("dorPerMngDto") DorPerMngDto dorPerMngDto) {
		ModelAndView mav = new ModelAndView();

		boolean result = adminService.changePermission(dorPerMngDto);
		mav.addObject("result", result);

		mav.setViewName("JSON");
		return mav;
	}

	/* 외박 일수 삭제 */
	@RequestMapping(value = "/permissionDelete.admin")
	public ModelAndView permissionDelete(HttpServletRequest request,
			@ModelAttribute("dorPerMngDto") DorPerMngDto dorPerMngDto) {
		ModelAndView mav = new ModelAndView();

		boolean result = adminService.deletePermission(dorPerMngDto);
		mav.addObject("result", result);

		mav.setViewName("JSON");
		return mav;
	}

	/* 외박 신청 내역 관리 화면으로 이동 */
	@RequestMapping(value = "/adminReq.admin", method = RequestMethod.GET)
	public ModelAndView adminReqForm(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		String id = "";

		List<Map<String, Object>> list = reqService.state(id);
		mav.addObject("list", list);
		mav.setViewName("adminReq");
		return mav;
	}

	/* 외박 신청 내역 관리 승인, 반려 */
	@RequestMapping(value = "/adminReq.admin", method = RequestMethod.POST)
	public ModelAndView adminReq(HttpServletRequest request,
			@ModelAttribute("dorReqDto") DorReqDto dorReqDto) {
		ModelAndView mav = new ModelAndView();
		reqService.changeReq(dorReqDto);
		mav.setViewName("redirect:/adminReq.admin");
		return mav;
	}
}
