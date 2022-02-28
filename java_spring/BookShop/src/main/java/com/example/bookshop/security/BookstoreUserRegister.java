package com.example.bookshop.security;

import com.example.bookshop.data.ApiResponseStatus;
import com.example.bookshop.data.struct.repository.UserRepository;
import com.example.bookshop.data.struct.user.UserEntity;
import com.example.bookshop.security.jwt.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.logging.Logger;

@Service
public class BookstoreUserRegister {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final BookstoreUserDetailsService bookstoreUserDetailsService;
    private final JWTUtil jwtUtil;
    private final Logger logger = Logger.getLogger(BookstoreUserRegister.class.getName());

    @Autowired
    public BookstoreUserRegister(UserRepository userRepository, PasswordEncoder passwordEncoder,
                                 AuthenticationManager authenticationManager,
                                 BookstoreUserDetailsService bookstoreUserDetailsService, JWTUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.bookstoreUserDetailsService = bookstoreUserDetailsService;
        this.jwtUtil = jwtUtil;
    }


    public void registerNewUser(RegistrationForm registrationForm) {
        if (userRepository.getUserEntityByEmail(registrationForm.getEmail()) == null) {
            UserEntity user = new UserEntity();
            user.setName(registrationForm.getName());
            user.setEmail(registrationForm.getEmail());
            user.setRegTime(LocalDateTime.now());
            user.setPassword(passwordEncoder.encode(registrationForm.getPass()));
            user.setHash(String.valueOf(user.getEmail().hashCode()));
            userRepository.save(user);
        }
    }

    public ApiResponseStatus login(ContactConfirmationPayload payload) {
        String userName = payload.getContact();
        String userPassword = payload.getCode();
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userName, userPassword);
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        ApiResponseStatus responseStatus = new ApiResponseStatus();
        responseStatus.setResult(true);
        return responseStatus;
    }

    public String jwtlogin(ContactConfirmationPayload payload) {
        String userName = payload.getContact();
        String userPassword = payload.getCode();
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userName, userPassword);
        authenticationManager.authenticate(authenticationToken);
        BookstoreUserDetails userDetails =
                (BookstoreUserDetails) bookstoreUserDetailsService.loadUserByUsername(userName);
        String jwToken = jwtUtil.generateToken(userDetails);
        return jwToken;
    }


    public UserEntity getCurrentUser() {
        logger.info(SecurityContextHolder.getContext().getAuthentication().getPrincipal().getClass().getName());
        logger.info(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        String userEmail = extractUserEmail();
        UserEntity user = userRepository.getUserEntityByEmail(userEmail);
        return user;
    }

    private String extractUserEmail() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = "";
        if (principal instanceof BookstoreUserDetails) {
            email = getEmail((BookstoreUserDetails) principal);
        } else if (principal instanceof DefaultOidcUser) {
            email = getEmail((DefaultOidcUser) principal);
        }
        return email;
    }

    private String getEmail(BookstoreUserDetails userDetails) {
        return userDetails.getUser().getEmail();
    }

    private String getEmail(DefaultOidcUser oidcUser) {
        return oidcUser.getEmail();
    }


}
