package pickle_time.pickle_time.global.auth.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import pickle_time.pickle_time.Member.Member;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public record PrincipalDetails(
        Member member,
        Map<String, Object> attributes,
        String attributeKey
) implements OAuth2User, UserDetails {
    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return Long.toString(member.getId());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(
                new SimpleGrantedAuthority("USER") // 추후 User 의 Role 추가하기.
        );
    }

    @Override
    public String getName() {
        return attributes.get(attributeKey).toString();
    }
}
