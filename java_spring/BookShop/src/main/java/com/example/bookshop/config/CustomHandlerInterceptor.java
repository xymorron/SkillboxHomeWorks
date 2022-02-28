package com.example.bookshop.config;

import com.example.bookshop.data.struct.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

@Component
public class CustomHandlerInterceptor implements HandlerInterceptor {

    private final BookService bookService;
    private final Logger logger = Logger.getLogger(CustomHandlerInterceptor.class.getName());

    @Autowired
    public CustomHandlerInterceptor(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        if (!uri.contains("/assets"))
            logger.info(">> PreHandle Interceptor called for uri: \"" + uri
                    + "\" with body: " + request.getParameterMap().keySet() + request.getParameterMap().entrySet()
                    + " Chosen handler: " + handler);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
        String uri = request.getRequestURI();
        if (!uri.contains("/assets"))
            logger.info("<< PostHandle Interceptor called for uri: \"" + uri + "\". Chosen handler: " + handler);
        if (modelAndView != null) {
            int userId = 47;
            int cartItemCount = bookService.getCartBooksByUser(userId).size();
            int keptItemCount = bookService.getKeptBooksByUser(userId).size();
            modelAndView.addObject("cartItemCount", cartItemCount);
            modelAndView.addObject("keptItemCount", keptItemCount);
        }
    }
}
