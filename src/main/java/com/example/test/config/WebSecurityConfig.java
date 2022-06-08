package com.example.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()                               //доруск всез пользователей к этим страницам
               // .anyRequest().authenticated()                                         //к остальным страницам только через авторизацию
                .antMatchers("/user", "/admin").hasRole("ADMIN")           //доступ через роли
                                                        //.hasAuthority("ADMIN")     //доступ через права доступа
                .antMatchers("/user").hasRole("USER")
                .antMatchers("/home").authenticated()                     //доступ только через авторизацию
                .and()
        .formLogin()
                .loginPage("/login")                                                  //используем свою форму
               // .successForwardUrl("/home")
                .permitAll()
                .and()
        .logout().permitAll().logoutSuccessUrl("/home");                                               //послу выхода переходим на страницу
    }


    //без базы данных
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build();
        UserDetails admin =
                User.withDefaultPasswordEncoder()
                        .username("admin")
                        .password("password")
                        .roles("ADMIN")
                        .build();
        return new InMemoryUserDetailsManager(user,admin);
    }
}
