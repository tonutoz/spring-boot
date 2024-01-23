package io.whatap.assignment.cmm.aop;

import java.util.function.Function;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StopWatch;

@RequiredArgsConstructor
@Getter
public enum ExcutionTimeType {

  MS("ms", "밀리세컨드", StopWatch::getTotalTimeMillis),
  SECOND("sec", "세컨드", s -> Double.valueOf(s.getTotalTimeSeconds()).longValue()),
  ;
  private final String code;

  private final String desc;

  private final Function<StopWatch, Long> time;

}
