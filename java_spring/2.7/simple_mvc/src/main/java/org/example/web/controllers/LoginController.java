package org.example.web.controllers;

import org.apache.log4j.Logger;
import org.example.app.services.LoginService;
import org.example.app.exeptions.BookShelfLoginException;
import org.example.web.dto.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    private Logger logger = Logger.getLogger(LoginController.class);
    private LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping
    public String login(Model model) {
        logger.info("GET /login returns login_page.html");
        model.addAttribute("loginForm", new LoginForm());
        return "login_page";
    }

    @PostMapping("/auth")
    public String authenticate(LoginForm loginForm) throws BookShelfLoginException {
        if (loginService.authenticate(loginForm)) {
            logger.info("Login OK redirect to book shelf");
            return "redirect:/books/shelf";
        } else {
            logger.info("Login FAIL redirect back to login");
            throw new BookShelfLoginException("invalid username or password");
        }
    }
    @ExceptionHandler(BookShelfLoginException.class)
    public String handlerError(Model model, BookShelfLoginException exception) {
        model.addAttribute("errorMessage", exception.getMessage());
        return "errors/404";
    }

}
