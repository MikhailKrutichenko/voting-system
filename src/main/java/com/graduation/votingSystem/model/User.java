package com.graduation.votingSystem.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User extends AbstractBaseEntity {

    @NotNull
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @NotBlank
    @Size(min = 1, max = 30)
    @Column(name = "name", nullable = false)
    private String name;

    @Email
    @NotBlank
    @Column(name = "email", nullable = false)
    private String email;

    @NotBlank
    @Column(name = "password", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    public User(Role role, String name, String email, String password) {
        this.role = role;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(Integer id, Role role, String name, String email, String password) {
        super(id);
        this.role = role;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Set<GrantedAuthority> grantedAuthorities() {
        return Set.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String toString() {
        return "User{" +
                "roles=" + role +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", id=" + id +
                '}';
    }
}
