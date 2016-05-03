package com.dormitory.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LoggingAspect {
//	private Log log = LogFactory.getLog(this.getClass());
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Pointcut("bean(memberController)")
	public void memberPointCut() {}
	
	@Pointcut("bean(reqController)")
	public void reqPointCut() {}
	
	@Around("memberPointCut() || reqPointCut()")
	public Object trace(
			ProceedingJoinPoint joinPoint) throws Throwable {
		String packageName = joinPoint.getSignature().getDeclaringTypeName();
		String methodName = joinPoint.getSignature().getName();
		
		long start = System.currentTimeMillis();
		logger.debug(packageName + " / " + methodName + " :: start");
		Object result = joinPoint.proceed();
		long end = System.currentTimeMillis();
		logger.debug(packageName + " / " + methodName + " :: end");
		logger.debug("time => " + (end - start));
		return result;
	}
	
}









