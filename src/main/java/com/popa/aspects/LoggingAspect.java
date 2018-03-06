package com.popa.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	private Logger logger = Logger.getLogger(getClass().getName());
	
	/**this is the advice - my pointcut will be applied when executing all the methods that returns void in
	 the package com.popa, that starts with set and receive only one argument
	 */
	@Before("execution(void com.popa..*.set*(*))")
	public void callSetters(JoinPoint joinPoint) {
		String method = joinPoint.getSignature().getName();
		String arg = joinPoint.getArgs()[0].toString();
		logger.info("Called " + method + "with args "+ arg + " on " + joinPoint.getTarget());
	}
}
