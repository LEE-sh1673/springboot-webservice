package me.lee_sh1673.book.config.auth.dto;

import java.io.Serializable;
import lombok.Getter;
import me.lee_sh1673.book.domain.user.User;

@Getter
public class SessionUser implements Serializable {

    private String name;

    private String email;

    private String picture;

    public SessionUser(final User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
