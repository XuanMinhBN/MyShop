package org.xumin.myshop.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

//@ControllerAdvice
//public class ExceptionHandlerController {
//
//    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
//    public String exception(Exception e) {
//        return "error400";
//    }
//
//    @ExceptionHandler(Exception.class)
//    public String defaultErrorHandler(Exception e) {
//        return e.toString();
//    }
//}
