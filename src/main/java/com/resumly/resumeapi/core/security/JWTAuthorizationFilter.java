package com.resumly.resumeapi.core.security;

import com.resumly.resumeapi.modules.user.domain.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private TokenProvider tokenProvider;
    private final UserDetailsServiceImpl userDetailsService;
    private final BCryptPasswordEncoder passwordEncoder;

    public JWTAuthorizationFilter(AuthenticationManager authManager, TokenProvider tokenProvider, UserDetailsServiceImpl userDetailsService, BCryptPasswordEncoder passwordEncoder) {
        super(authManager);
        this.tokenProvider = tokenProvider;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(SecurityConstants.HEADER_STRING);

        if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(SecurityConstants.HEADER_STRING);
        if (token != null) {
            // parse the token.
            User user = tokenProvider.parseToken(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());

            if (!passwordEncoder.matches(user.getPassword(), userDetails.getPassword())) {
                return null;
            }

            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword(), new ArrayList<>());
            }
            return null;
        }
        return null;
    }
}
