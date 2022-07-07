package com.gft.store.config;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.gft.store.services.AuthService;
import com.gft.store.services.UserService;
import com.gft.store.services.filter.AuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserService userService;

    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager getAuthenticationManager(HttpSecurity http)
            throws Exception {

        AuthenticationManagerBuilder authenticationManagerBuilder = http
                .getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(encoder());
        // authenticationManagerBuilder.inMemoryAuthentication().withUser("user").password(encoder().encode("123")).roles("USER");
        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();
        return authenticationManager;
    }

    // @Bean
    // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    // http.authorizeRequests()
    // .antMatchers(HttpMethod.POST, "/auth").permitAll()
    // .anyRequest().authenticated()
    // .and().csrf().disable()
    // .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    // .and()
    // .authenticationManager(getAuthenticationManager(http))
    // .addFilterBefore(new AuthFilter(new AuthService(), userService),
    // UsernamePasswordAuthenticationFilter.class)
    // .httpBasic();

    // return http.build();
    // }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers(HttpMethod.POST, "/auth").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .permitAll()
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").permitAll()
                .and()
                .authenticationManager(getAuthenticationManager(http))
                .httpBasic();

        http.csrf().disable();
        http.headers().frameOptions().disable();

        return http.build();
    }

}
