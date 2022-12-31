package me.lee_sh1673.book.config.auth.dto;

import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import me.lee_sh1673.book.domain.user.Role;
import me.lee_sh1673.book.domain.user.User;

@Getter
public class OAuthAttributes {

    private final Map<String, Object> attributes;

    private final String nameAttributeKey;

    private final String name;

    private final String email;

    private final String picture;

    @Builder
    public OAuthAttributes(final Map<String, Object> attributes,
        final String nameAttributeKey, final String name,
        final String email, final String picture) {

        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    public static OAuthAttributes of(final String registrationId,
        final String userNameAttributeName,
        final Map<String, Object> attributes) {

        if ("naver".equals(registrationId)) {
            return ofNaver("id", attributes);
        }

        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(final String userNameAttributeName,
        final Map<String, Object> attributes) {

        return OAuthAttributes.builder()
            .name((String) attributes.get("name"))
            .email((String) attributes.get("email"))
            .picture((String) attributes.get("picture"))
            .attributes(attributes)
            .nameAttributeKey(userNameAttributeName)
            .build();
    }

    private static OAuthAttributes ofNaver(final String userNameAttributeName,
        final Map<String, Object> attributes) {

        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuthAttributes.builder()
            .name((String) response.get("name"))
            .email((String) response.get("email"))
            .picture((String) response.get("picture"))
            .attributes(response)
            .nameAttributeKey(userNameAttributeName)
            .build();
    }

    public User toEntity() {
        return User.builder()
            .name(name)
            .email(email)
            .picture(picture)
            .role(Role.GUEST)
            .build();
    }
}
