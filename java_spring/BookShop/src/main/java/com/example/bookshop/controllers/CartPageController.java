package com.example.bookshop.controllers;

import com.example.bookshop.data.struct.book.Book;
import com.example.bookshop.data.struct.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.logging.Logger;

@Controller
@RequestMapping("/cart")
public class CartPageController {

    private final Logger logger = Logger.getLogger(CartPageController.class.getName());
    private final BookService bookService;

    @Autowired
    public CartPageController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String handlerCartRequest(Model model) {
        logger.info("cart page requested");
        int userId = 47;
        List<Book> bookCart = bookService.getCartBooksByUser(userId);
        int totalCost = 0;
        int oldCost = 0;
        for (Book book : bookCart) {
            oldCost += book.getPrice();
            totalCost += book.getDiscount() > 0 ? book.getDiscountPrice() : book.getPrice();
        }
        model.addAttribute("bookCart", bookCart);
        model.addAttribute("totalCost", totalCost);
        model.addAttribute("oldCost", oldCost);

        return "cart";
    }
//    @GetMapping
//    public String handlerCartRequest(@CookieValue(name = "cartContents", required = false) String cartContents,
//                                     Model model) {
//        if (cartContents == null || cartContents.equals("")) {
//            model.addAttribute("isCartEmpty", true);
//            logger.info("cart page requested, cart is empty");
//        } else {
//            model.addAttribute("isCartEmpty", false);
//            cartContents = cartContents.startsWith("/") ? cartContents.substring(1) : cartContents;
//            cartContents = cartContents.endsWith("/") ? cartContents.substring(0, cartContents.length() - 1) : cartContents;
//            String[] cookieSlugs = cartContents.split("/");
//            List<Book> booksFromCookieList = bookService.findBooksBySlugs(cookieSlugs);
//            logger.info("cart page requested. Books returned: " + booksFromCookieList);
//            model.addAttribute("bookCart", booksFromCookieList);
//        }
//        return "cart";
//    }
}
