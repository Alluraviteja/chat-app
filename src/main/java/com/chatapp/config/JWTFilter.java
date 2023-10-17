package com.chatapp.config;

import java.io.IOException;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.GenericFilterBean;

import com.chatapp.serviceImpl.TokenService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class JWTFilter extends GenericFilterBean {

    private TokenService tokenService;

    JWTFilter() {
        this.tokenService = new TokenService();
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String token = request.getHeader("Authorization");

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.sendError(HttpServletResponse.SC_OK, "success");
            return;
        }

        if (allowRequestWithoutToken(request)) {
            response.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(req, res);
        } else {
            if (token != null && !tokenService.isTokenValid(token)) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            }if(token == null){
            	response.setStatus(HttpServletResponse.SC_OK);
                filterChain.doFilter(req, res);
            }
            else {
                //ObjectId userId = new ObjectId(tokenService.getUserIdFromToken(token));
            	Object userId = tokenService.getUserIdFromToken(token);
                request.setAttribute("userId", userId);
                filterChain.doFilter(req, res);

            }
        }

    }

    public boolean allowRequestWithoutToken(HttpServletRequest request) {
    	
    	if (request.getRequestURI().contains("/api/auth/**") 
    			|| request.getRequestURI().contains("/api/user/login")
                || request.getRequestURI().contains("/api/user/signup") 
                || request.getRequestURI().contains("/api/user/setPassword")) {
            return true;
        }
        return false;
    }
}