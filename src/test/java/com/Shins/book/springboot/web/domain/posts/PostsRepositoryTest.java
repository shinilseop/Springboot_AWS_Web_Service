package com.Shins.book.springboot.web.domain.posts;

import com.Shins.book.springboot.domain.posts.Posts;
import com.Shins.book.springboot.domain.posts.PostsRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
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
}
