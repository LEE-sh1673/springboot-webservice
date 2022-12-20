package me.lee_sh1673.book.web;

import me.lee_sh1673.book.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(
        @RequestParam("name") final String name,
        @RequestParam("amount") final int amount) {

        return new HelloResponseDto(name, amount);
    }
}
