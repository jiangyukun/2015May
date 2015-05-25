package me.jiangyu.may.web.filter;

import me.jiangyu.may.service.security.DaoUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by jiangyukun on 2015/5/25.
 */
public class BasicAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private DaoUserDetailsService daoUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        List<GrantedAuthority> authorityList = new LinkedList<>();
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ADMIN");
        authorityList.add(authority);
        Authentication authentication = new UsernamePasswordAuthenticationToken("", "", authorityList);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}
