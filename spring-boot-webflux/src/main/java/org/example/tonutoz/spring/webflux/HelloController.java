package org.example.tonutoz.spring.webflux;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class HelloController {

  @GetMapping("/reactive")
  Flux<String> sayHello() {
    return Flux.just("say","hello");
  }

  @GetMapping("/stream")
  Flux<Map<String, Integer>> stream() {
    Stream<Integer> stream = Stream.iterate(0, i -> i + 1); // Java8의 무한Stream
    return Flux.fromStream(stream.limit(30))
        .map(i -> Collections.singletonMap("value", i));
  }

  @PostMapping("/echo")
  Mono<String> echo(@RequestBody Mono<String> body) {
    System.out.println(body);
    return body.map(String::toUpperCase);
  }
}
