package me.lee_sh1673.book.web.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import me.lee_sh1673.book.domain.posts.Posts;

@Getter
public class PostsListResponseDto {

    private final Long id;

    private final String title;

    private final String author;

    private final LocalDateTime modifiedDate;

    public PostsListResponseDto(final Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.modifiedDate = entity.getModifiedTime();
    }
}
