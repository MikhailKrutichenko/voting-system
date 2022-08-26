package com.graduation.votingSystem.security;

import com.graduation.votingSystem.model.Role;
import com.graduation.votingSystem.model.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Data
public class SecurityUser implements UserDetails {

    private final Integer id;
    private final Role roles;
    private final String name;
    private final String email;
    private final String password;
    private final Set<GrantedAuthority> authority;

    public SecurityUser(Integer id, Role roles, String name, String email, String password, Set<GrantedAuthority> authority) {
        this.id = id;
        this.roles = roles;
        this.name = name;
        this.email = email;
        this.password = password;
        this.authority = authority;
    }

    public static UserDetails fromUser(User user) {
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                user.grantedAuthorities());
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
}
