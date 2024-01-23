package io.whatap.assignment.cmm.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Slf4j
public class ExecutionTimeAop {

  @Around("@annotation(executionTimeChecker)")
  public Object calculateExecutionTime(ProceedingJoinPoint pjp,
      ExecutionTimeChecker executionTimeChecker) throws Throwable {
    StopWatch sw = new StopWatch();
    sw.start();

    Object result = pjp.proceed();

    sw.stop();
    ExcutionTimeType type = executionTimeChecker.type();
    long executionTime = type.getTime().apply(sw);

    String className = pjp.getTarget().getClass().getName();
    String methodName = pjp.getSignature().getName();
    String task = className + "." + methodName;

    log.info("[ExecutionTime] " + task + "-->" + executionTime + "(" + type.getCode() + ")");

    return result;
  }

}
