package com.ikram.pfe.config;

import com.ikram.pfe.services.UserService;
import com.ikram.pfe.utils.JwtUtils;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

@Service
@RequiredArgsConstructor
public class TokenAuthorizationFilter extends OncePerRequestFilter {

  private final JwtUtils jwtUtils;
  private final UserService service;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    final String authHeader = request.getHeader("Authorization");
    String userMail = null;
    String jwt = null;
    if (authHeader != null && authHeader.startsWith("Bearer ")) {
      jwt = authHeader.substring(7);
      userMail = jwtUtils.extractUsername(jwt);
    }
    if (userMail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = service.loadUserByUsername(userMail);
      if (jwtUtils.validateToken(jwt, userDetails)) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
            userDetails, null, userDetails.getAuthorities()
        );
        usernamePasswordAuthenticationToken.setDetails(
            new WebAuthenticationDetailsSource().buildDetails(request)
        );
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
      }
    }
    filterChain.doFilter(request, response);
  }
}
