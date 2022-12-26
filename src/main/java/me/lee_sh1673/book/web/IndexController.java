package me.lee_sh1673.book.web;

import lombok.RequiredArgsConstructor;
import me.lee_sh1673.book.service.posts.PostsService;
import me.lee_sh1673.book.web.dto.PostsResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(final Model model) {
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";
    }


    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(final @PathVariable Long id, final Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";
    }
}
