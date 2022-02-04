package com.example.bookshop.controllers;

import com.example.bookshop.errs.EmptySearchException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.logging.Logger;

@ControllerAdvice
public class GlobalExceptionHandlerController {
    private final Logger logger = Logger.getLogger(GlobalExceptionHandlerController.class.getName());

    @ExceptionHandler(EmptySearchException.class)
    public String handlerEmptySearchException(EmptySearchException e, RedirectAttributes redirectAttributes) {
        logger.warning(e.getLocalizedMessage());
        redirectAttributes.addFlashAttribute("searchError", e);
        return "redirect:/";
    }
}
