package com.example.bookshop.controllers;

import com.example.bookshop.data.ApiResponseStatus;
import com.example.bookshop.errs.BookstoreApiWrongParameterException;
import com.example.bookshop.security.BookstoreUserRegister;
import com.example.bookshop.security.ContactConfirmationPayload;
import com.example.bookshop.security.RegistrationForm;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

@Controller
public class SigninPageController {

    private final BookstoreUserRegister userRegister;
    private final Logger logger = Logger.getLogger(SigninPageController.class.getName());

    @Autowired
    public SigninPageController(BookstoreUserRegister userRegister) {
        this.userRegister = userRegister;
    }

    @GetMapping("/signin")
    public String signinPage() {
        return "signin";
    }

    @GetMapping("/signup")
    public String signupPage(Model model) {
        model.addAttribute("regform", new RegistrationForm());
        return "signup";
    }

    @PostMapping("/reg")
    public String handlerRegistration(RegistrationForm registrationForm, Model model) {
        logger.info("Query registration received. RegForm: " + registrationForm);
        userRegister.registerNewUser(registrationForm);
        model.addAttribute("regOk", true);
        return "redirect:/signin";
    }

    @PostMapping("/api/login")
    @ResponseBody
    public ApiResponseStatus handlerLogin(ContactConfirmationPayload payload, HttpServletResponse response) {
        String jwtToken = userRegister.jwtlogin(payload);
        Cookie cookie = new Cookie("token", jwtToken);
        cookie.setPath("/");
        response.addCookie(cookie);
        ApiResponseStatus responseStatus = new ApiResponseStatus();
        responseStatus.setResult(true);
        return responseStatus;
    }

    @GetMapping("/my")
    public String handlerMy(Authentication authentication) {
        logger.info("myPage requested with authentication: " + authentication.getName());
        return "my";
    }

    @GetMapping("/profile")
    public String handlerProfile(Model model) {
        model.addAttribute("curUser", userRegister.getCurrentUser());
        return "profile";
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponseStatus> handlerBadCredentialsException(Exception exception) {
        return new ResponseEntity<>(
                new ApiResponseStatus(false,"login failed...", exception),
                HttpStatus.OK);
    }

}
