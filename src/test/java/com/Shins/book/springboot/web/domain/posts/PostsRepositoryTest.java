package com.Shins.book.springboot.web.domain.posts;

import com.Shins.book.springboot.domain.posts.Posts;
import com.Shins.book.springboot.domain.posts.PostsRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @AfterEach
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("shinilseop12@gmail.com")
                .build());

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);
        System.out.println("@@@ID::"+posts.getId());
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void postsMakeTest() {
        String title = "title";
        String content = "content";
        Posts posts1 = Posts.builder()
                .title(title+"1")
                .content(content+"1")
                .author("shinilseop12@gmail.com")
                .build();
        Posts posts2 = Posts.builder()
                .title(title+"2")
                .content(content+"2")
                .author("shinilseop12@gmail.com")
                .build();
        Posts posts3 = Posts.builder()
                .title(title+"3")
                .content(content+"3")
                .author("shinilseop12@gmail.com")
                .build();

        System.out.println("POSTS 1 ::: " + posts1.getId());
        System.out.println("POSTS 2 ::: " + posts2.getId());
        System.out.println("POSTS 3 ::: " + posts3.getId());
    }

    @Test
    public void BaseTimeEntity_등록() {
        //given
        LocalDateTime now = LocalDateTime.of(2019, 6, 4, 0, 0, 0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>> createDate=" + posts.getCreatedDate() + ", modifiedDate=" + posts.getModifiedDate());
        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}
