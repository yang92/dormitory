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

import com.dormitory.dto.DorReqDto;
import com.dormitory.dto.MemberDto;
import com.dormitory.service.ReqService;

@Controller
public class ReqController {
	@Autowired
	private ReqService reqService;

	/* 외박 신청 화면 이동 */
	@RequestMapping(value = "/req.do", method = RequestMethod.GET)
	public ModelAndView reqForm(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("req");
		return mav;
	}

	/* 외박 신청 처리 */
	@RequestMapping(value = "/req.do", method = RequestMethod.POST)
	public ModelAndView req(HttpServletRequest request,	@ModelAttribute("dorReqDto") DorReqDto dorReqDto) {
		ModelAndView mav = new ModelAndView();

		reqService.req(dorReqDto);
		mav.setViewName("redirect:/state.do");
		return mav;
	}

	/* 외박 신청 확인 */
	@RequestMapping("/reqCheck.do")
	public ModelAndView reqCheck(HttpServletRequest request,
			@ModelAttribute("dorReqDto") DorReqDto dorReqDto) {
		ModelAndView mav = new ModelAndView();

		Map<String, Object> resultMap = reqService.reqCheck(dorReqDto);
		mav.addAllObjects(resultMap);
		mav.setViewName("JSON");
		return mav;
	}

	/* 외박 신청 현황 */
	@RequestMapping("/state.do")
	public ModelAndView state(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		MemberDto memberDto = (MemberDto) request.getSession().getAttribute("member");
		String id = memberDto.getId();

		// 신청 현황 목록
		List<Map<String, Object>> list = reqService.state(id);

		// 외박 가능일수, 사용일수
		Map<String, Object> dateState = reqService.dateState(memberDto);

		mav.addObject("list", list);
		mav.addObject("state", dateState);
		mav.setViewName("state");
		return mav;
	}
}
