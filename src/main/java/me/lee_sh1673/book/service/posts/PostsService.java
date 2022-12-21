package me.lee_sh1673.book.service.posts;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.lee_sh1673.book.domain.posts.Posts;
import me.lee_sh1673.book.domain.posts.PostsRepository;
import me.lee_sh1673.book.web.dto.PostsResponseDto;
import me.lee_sh1673.book.web.dto.PostsSaveRequestDto;
import me.lee_sh1673.book.web.dto.PostsUpdateRequestDto;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(final PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(final Long id, final PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
            .orElseThrow(()
                -> new IllegalArgumentException("해당 개시글이 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(final Long id) {
        Posts entity = postsRepository.findById(id)
            .orElseThrow(()
                -> new IllegalArgumentException("해당 개시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }
}
