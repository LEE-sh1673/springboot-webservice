package me.lee_sh1673.book.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.lee_sh1673.book.domain.posts.Posts;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {

    private String title;

    private String content;

    private String author;

    @Builder
    public PostsSaveRequestDto(final String title,
        final String content, final String author) {

        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
            .title(title)
            .content(content)
            .author(author)
            .build();
    }
}
