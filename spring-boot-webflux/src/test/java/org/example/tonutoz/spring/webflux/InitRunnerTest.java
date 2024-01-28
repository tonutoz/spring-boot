package org.example.tonutoz.spring.webflux;

import java.time.LocalDate;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class InitRunnerTest {


  @Test
  @DisplayName("랜덤 타이틀 생성")
  public void generateRandomTitle() {

    var titles = new String[]{"일기", "글써봅니다", "다들봐라", "이거이상한데", "뭐지", "오랫만에 왔습니다."};
    var random = new Random();

    for (int i = 0; i < 10; i++) {
      int randomIndex = random.nextInt(titles.length);
      String randomTitle = titles[randomIndex];
      System.out.println("Random Title: " + LocalDate.now() + randomTitle);
    }
  }

  @Test
  @DisplayName("랜덤 컨텐트 생성")
  public void generateRandomContent() {
    var contents = new String[]{"""
    잘못된 내용이 있어서 정정합니다.
    다시쓸게요
    """, """
    글써봅니다 내용이좀 있어서요.
    하이하이하이
    """, """
    다들봐라 이건좀 아니지 않냐 
    뭔데뭔데뭔데
    """, """
    이거이상한데
    한번 봐주세요 stack over flow
    """, """
    잘못된 다른 테스트 코드 : 다른 테스트 코드에 오류가 있는 경우 @SpringBootTest 어노테이션을 사용한 테스트가 실패할 수 있습니다. 
    다른 테스트 코드를 확인해 보세요.
    """, """
    위 방법을 모두 시도해보았는데도 문제가 해결되지 않는다면, 자세한 오류 메세지 또는 스택 추적 결과를 
    제공해주시면 좀 더 도움을 드릴 수 있습니다.
    """};

    var random = new Random();
    for (int i = 0; i < 10; i++) {
      int randomIndex = random.nextInt(contents.length);
      String randomContent = contents[randomIndex];
      System.out.println("Random content: " + LocalDate.now() + randomContent);
    }

  }


}
