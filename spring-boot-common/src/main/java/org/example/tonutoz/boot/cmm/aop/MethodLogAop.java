package io.whatap.assignment.cmm.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class MethodLogAop {

  @Around("@annotation(methodLogWriter)")
  public Object calculateExecutionTime(ProceedingJoinPoint pjp, MethodLogWriter methodLogWriter)
      throws Throwable {

    String className = pjp.getTarget().getClass().getName();
    String methodName = pjp.getSignature().getName();
    String task = className + "." + methodName;

    log.info("[Execution] " + task + "--> START");
    Object result = pjp.proceed();

    log.info("[Execution] " + task + "--> END");

    return result;
  }

}
