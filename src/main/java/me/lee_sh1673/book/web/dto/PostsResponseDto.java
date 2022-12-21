package me.lee_sh1673.book.web.dto;

import lombok.Getter;
import me.lee_sh1673.book.domain.posts.Posts;

@Getter
public class PostsResponseDto {

    private Long id;

    private String title;

    private String content;

    private String author;

    public PostsResponseDto(final Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
