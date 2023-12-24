package com.example.Assignmnet.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "USERDATA")
public class UserData implements UserDetails {

    @Id
    @Column(name = "EMAIL", unique = true)
    String email;
    @Column(name = "FIRSTNAME")
    String firstName;
    @Column(name = "LASTNAME")
    String lastName;
    @Column(name = "PASSWORD")
    String password;

//    @Column(name = "roles")
//    private String roles ;

    @Enumerated(EnumType.STRING)
    private Role roles;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(roles.name()));
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

    @Override
    public String getPassword(){
        return password;
    }
}
