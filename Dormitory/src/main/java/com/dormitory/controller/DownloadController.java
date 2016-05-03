package com.dormitory.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dormitory.service.ReqService;

@Controller
public class DownloadController {

	@Autowired
	private ReqService reqService;
	
	@RequestMapping("/excelDownload.do")
	public ModelAndView download(HttpServletRequest request) {
		String id = "";
		List<Map<String, Object>> list = reqService.state(id);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", list);
		mav.setViewName("excelDownload");

		return mav;
	}
	
}







