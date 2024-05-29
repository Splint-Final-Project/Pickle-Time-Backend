package pickle_time.pickle_time.global.auth.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import pickle_time.pickle_time.User.Users;


import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public record PrincipalDetails(
        Users user,
        Map<String, Object> attributes,
        String attributeKey
) implements OAuth2User, UserDetails {

    public PrincipalDetails(Users user) {
        this(user, Collections.emptyMap(), null);
    }

    public Users getUser() {
        return user;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return Long.toString(user.getId());
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
                new SimpleGrantedAuthority(user.getStatus())
        );
    }

    @Override
    public String getName() {
        return attributes.get(attributeKey).toString();
    }
}
