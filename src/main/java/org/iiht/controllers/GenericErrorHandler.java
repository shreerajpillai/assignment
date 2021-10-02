package org.iiht.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GenericErrorHandler {
    @ExceptionHandler(Exception.class)
    public String error(Model model) {
        System.out.println("error");
        return "errorPage.jsp";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public String notfound(Model model) {
        System.out.println("not found page");
        return "notFound.jsp";
    }
}
