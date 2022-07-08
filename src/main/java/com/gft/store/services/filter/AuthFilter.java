package com.gft.store.services.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import com.gft.store.models.entities.UserModel;
import com.gft.store.services.AuthService;
import com.gft.store.services.UserService;

public class AuthFilter extends OncePerRequestFilter {

    private AuthService authService;

    private UserService userService;

    public AuthFilter(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");
        String token = null;

        if (header != null && header.startsWith("Bearer ")) {
            token = header.substring(7, header.length());
        }

        if (authService.checkToken(token)) {
            Long userId = authService.returnUserId(token);
            UserModel user = userService.getById(userId);
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword(),
                            user.getAuthorities()));
        }

        filterChain.doFilter(request, response);
    }

}
