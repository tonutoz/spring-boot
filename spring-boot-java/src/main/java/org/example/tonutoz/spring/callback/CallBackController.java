package org.example.tonutoz.spring.callback;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
@Slf4j
public class CallBackController {

  @GetMapping("/async/call-back-hell/solution")
  public void callBackHellSolution() throws InterruptedException {
    CompletableFuture
        .runAsync(() -> log.info("runAsync"))//한번 실행할 코드를 명시하는 함수
        .thenRunAsync(() -> log.info("thenRunAsync"))
        .thenRunAsync(() -> log.info("thenThenRunAsync"));
    log.info("exit");

    ForkJoinPool.commonPool().shutdown();
    ForkJoinPool.commonPool().awaitTermination(10, TimeUnit.SECONDS);
  }

  @GetMapping("/async/call-back-hell/solution/chain/exception")
  public void callBackHellSolutionChainException() throws InterruptedException {
    CompletableFuture
        .supplyAsync(() -> {
          log.info("supplyAsync");
          return 1;
        })
        .thenApply(s -> {
          log.info("thenApplyAsync");
          //if(true) throw new RuntimeException();
          return s + 1;
        })
        .thenApply(s2 -> {
          log.info("thenThenApplyAsync");
          return s2 + 100;
        })
        .exceptionally(e -> -10)
        .thenAccept(s3 -> log.info("result : {}", s3));
    log.info("exit");

    ForkJoinPool.commonPool().shutdown();
    ForkJoinPool.commonPool().awaitTermination(10, TimeUnit.SECONDS);
  }

  <T> CompletableFuture<T> toCF(ListenableFuture<T> lf){
    CompletableFuture<T> cf = new CompletableFuture<>();
    lf.addCallback(s -> cf.complete(s), e -> cf.completeExceptionally(e));
    return cf;
  }


}
