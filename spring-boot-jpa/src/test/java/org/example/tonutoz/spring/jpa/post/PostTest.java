package org.example.tonutoz.spring.jpa.post;

import static java.lang.String.format;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.IntStream;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
public class PostTest {

  @Autowired
  private EntityManager em;

  @Autowired
  private PostRepository postRepository;

  @Autowired
  private CommentRepository commentRepository;

  @BeforeAll
  public static void setUp() {
    System.out.println("zzzzzzzzzzz");

  }


  @Test
  @DisplayName("N + 1 발생 테스트")
  void test() {
    saveSampleData(); // 10개의 post와, 각각의 post마다 3개씩 댓글 저장
    em.flush();
    em.clear();

    System.out.println("------------ 영속성 컨텍스트 비우기 -----------");



    System.out.println("------------ POST 전체 조회 요청 ------------");
    List<Post> posts = postRepository.findAll();
    System.out.println("------------ POST 전체 조회 완료. [1번의 쿼리 발생]------------");


    System.out.println("------------ POST 제목 & 내용 조회 요청 ------------");
    posts.forEach(it -> System.out.printf("POST 아이디:[%s] 제목: [%s], POST 내용: [%s] %n ", it.getId(),it.getTitle(), it.getContent()));
    System.out.println("------------ POST 제목 & 내용 조회 완료. [추가적인 쿼리 발생하지 않음]------------");


    System.out.println("------------ POST에 달린 comment 내용 조회 요청 [조회된 POST의 개수(N=10) 만큼 추가적인 쿼리 발생]------------");
    for (Post post : posts) {
      System.out.println("post id=" + post.getId());
      post.getComments().forEach(comment -> {
        System.out.println("POST 제목: [%s], COMMENT 내용: [%s]".formatted(comment.getPost().getTitle(),
            comment.getContent()));
      });
    }
    System.out.println("------------ POST에 달린 comment 내용 조회 완료 ------------\n\n");
  }

  private void saveSampleData() {
    final var postTitleFormat = "[%d] post-title";
    final var postContentFormat = "[%d] post-content";
    final var commentContentFormat = "[%d] comment-content";

    IntStream.rangeClosed(1, 10).forEach(i -> {
      Post post = Post.builder()
          .title(format(postTitleFormat, i))
          .content(format(postContentFormat, i))
          .build();

     List<Comment> comments =  IntStream.rangeClosed(1, 3).mapToObj(j -> {
       return Comment.builder().post(post).content(format(commentContentFormat, j)).build();
     }).toList();

      postRepository.save(post);
      commentRepository.saveAll(comments);
    });
  }

}
