package com.example.test.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
public class MyUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private boolean accountNonExpired = true;

    private boolean accountNonLocked = true;

    private boolean credentialsNonExpired = true;

    private boolean enabled = true;

    @ManyToMany
    @JoinTable
    private Collection<Role> roles;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    //Метод isAccountNonExpired возвращает логический тип, который используется для определения того,
    //    не истек ли срок действия учетной записи, если он не истек, он возвращает true, в противном случае возвращает false;
    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    //Метод isAccountNonLocked используется для определения, разблокирована ли учетная запись;
    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    //isCredentialsNonExpired используется для определения того, не истек ли срок действия учетных данных пользователя,
    //то есть не истек ли срок действия пароля;
    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    //Метод isEnabled используется для определения доступности пользователя.
    //На практике мы можем настроить класс реализации интерфейса UserDetails или напрямую использовать
    //класс реализации интерфейса UserDetails org.springframework.security.core.userdetails.User,
    // предоставленный Spring Security.
    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

}
