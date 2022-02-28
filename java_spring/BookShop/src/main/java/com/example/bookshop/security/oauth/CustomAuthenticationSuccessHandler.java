package com.example.bookshop.security.oauth;

import com.example.bookshop.security.BookstoreUserDetails;
import com.example.bookshop.security.BookstoreUserDetailsService;
import com.example.bookshop.security.jwt.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final BookstoreUserDetailsService bookstoreUserDetailsService;
    private final JWTUtil jwtUtil;
    private final String REDIRECT_URL = "/my";
    private final Logger logger = Logger.getLogger(CustomAuthenticationSuccessHandler.class.getName());

    @Autowired
    public CustomAuthenticationSuccessHandler(BookstoreUserDetailsService bookstoreUserDetailsService, JWTUtil jwtUtil) {
        this.bookstoreUserDetailsService = bookstoreUserDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        logger.info("onAuthenticationSuccess called.");
        if (response.isCommitted()) {
            return;
        }
        DefaultOidcUser oidcUser = (DefaultOidcUser) authentication.getPrincipal();
        Map<String, Object> attributes = oidcUser.getAttributes();
        String email = (String) attributes.get("email");
        logger.info("email: " + email);
        BookstoreUserDetails userDetails =
                (BookstoreUserDetails) bookstoreUserDetailsService.loadUserByUsername(email);
        String jwtToken = jwtUtil.generateToken(userDetails);
        Cookie cookie = new Cookie("token", jwtToken);
        cookie.setPath("/");
        response.addCookie(cookie);
        getRedirectStrategy().sendRedirect(request, response, REDIRECT_URL);
        super.onAuthenticationSuccess(request, response, authentication);
    }

}
