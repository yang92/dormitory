package com.dormitory.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import com.dormitory.dto.MemberDto;

@Aspect
public class LoginCheckAspect {

	@Pointcut("bean(memberController)")
	public void memberPointCut() {
	}

	@Pointcut("bean(reqController)")
	public void reqPointCut() {
	}

	@Around("memberPointCut() || reqPointCut()")
	public Object trace(ProceedingJoinPoint joinPoint) throws Throwable {

		HttpServletRequest request = null;
		for (Object obj : joinPoint.getArgs()) {
			if (obj instanceof HttpServletRequest) {
				request = (HttpServletRequest) obj;
			}
		}
		MemberDto sMemberDto = (MemberDto) request.getSession().getAttribute("member");
		String id = "";
		if (sMemberDto != null) {
			id = sMemberDto.getId();
		}

		boolean isPass = false;
		if (id != null && !id.equals("")) {
			isPass = true;
		} else {
			String[] allowUrl = { "/join.do", "/login.do" };
			String connUrl = request.getRequestURI();

			for (String url : allowUrl) {
				if (connUrl.startsWith(url)) {
					isPass = true;
				}
			}
		}

		if (!isPass) {
			throw new RuntimeException("auth");
		}

		Object result = joinPoint.proceed();
		return result;
	}
}
