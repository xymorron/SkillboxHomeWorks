package com.example.bookshop.security;

import com.example.bookshop.security.jwt.JWTUtil;
import com.example.bookshop.security.jwt.blacklist.JWTBlackListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.logging.Logger;

@Service
public class CustomLogoutHandler implements LogoutHandler {

    private final JWTBlackListService jwtBlackListService;
    private final Logger logger = Logger.getLogger(CustomLogoutHandler.class.getName());
    private final JWTUtil jwtUtil;

    @Autowired
    public CustomLogoutHandler(JWTBlackListService jwtBlackListService, JWTUtil jwtUtil) {
        this.jwtBlackListService = jwtBlackListService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response,
                       Authentication authentication) {
        logger.info("CustomLogoutHandler run");
        HttpSession session = request.getSession();
        SecurityContextHolder.clearContext();
        if (session != null) {
            session.invalidate();
        }

        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("token")) {
                String token = cookie.getValue();
                if (!jwtUtil.isTokenExpired(token)) {
                    jwtBlackListService.addToBlackList(token);
                }
                cookie.setMaxAge(0);
            }
        }
    }
}