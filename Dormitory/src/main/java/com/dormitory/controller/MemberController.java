package com.dormitory.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dormitory.dto.MemberDto;
import com.dormitory.service.MemberService;

@Controller
public class MemberController {
	private Log log = LogFactory.getLog(this.getClass());

	@Autowired
	private MemberService memberService;

	/* 로그인 화면으로 이동 */
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public ModelAndView loginForm(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");

		log.debug("login!!");

		return mav;
	}

	/* 로그인 처리 */
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request,
			@ModelAttribute("member") MemberDto memberDto) {
		ModelAndView mav = new ModelAndView();
		int result = memberService.login(memberDto);
		if (result == 1) {
			mav.setViewName("redirect:/main.do");
			request.getSession().setAttribute("member", memberDto);

		} else {
			mav.setViewName("login");
		}
		return mav;
	}

	/* 회원가입 화면으로 이동 */
	@RequestMapping(value = "/join.do", method = RequestMethod.GET)
	public ModelAndView joinForm(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("join");
		return mav;
	}

	/* 회원가입 처리 */
	@RequestMapping(value = "/join.do", method = RequestMethod.POST)
	public ModelAndView join(HttpServletRequest request,
			@ModelAttribute("member") MemberDto memberDto) {
		// String id = request.getParameter("id");
		// String pw = request.getParameter("pw");
		// String name = request.getParameter("name");
		// String grade = request.getParameter("grade");

		// MemberDto memberDto = new MemberDto();
		// memberDto.setId(id);
		// memberDto.setPw(pw);
		// memberDto.setName(name);
		// memberDto.setGrade(grade);

		boolean result = memberService.join(memberDto);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/login.do");
		return mav;
	}

	/* 메인 화면으로 이동 */
	@RequestMapping("/main.do")
	public ModelAndView main(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("main");
		return mav;
	}
}
