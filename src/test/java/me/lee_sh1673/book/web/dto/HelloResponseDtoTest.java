package me.lee_sh1673.book.web.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class HelloResponseDtoTest {

    @Test
    void 롬북_기능_테스트() {
        // given
        String name = "test";
        int amount = 1_000;

        // when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        // then
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}