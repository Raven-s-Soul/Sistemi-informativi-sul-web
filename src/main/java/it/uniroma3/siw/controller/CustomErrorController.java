package it.uniroma3.siw.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
@Controller
public class CustomErrorController implements ErrorController {

    @GetMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object statusCode = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        int status = statusCode != null ? Integer.parseInt(statusCode.toString()) : 500;

        String message;
        switch (status) {
            case 404:
                message = "Page not found.";
                break;
            case 403:
                message = "Access denied.";
                break;
            case 500:
                message = "Internal server error.";
                break;
            default:
                message = "Unexpected error.";
        }

        model.addAttribute("status", status);
        model.addAttribute("message", message);
        return "error/customError";
    }
    
    /*
    @GetMapping("/error")
    public ResponseEntity<Map<String, Object>> handleError(HttpServletRequest request) {
        Object statusCode = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        int status = statusCode != null ? Integer.parseInt(statusCode.toString()) : 500;

        Map<String, Object> error = new HashMap<>();
        error.put("status", status);
        error.put("message", "Custom error handling message.");

        return new ResponseEntity<>(error, HttpStatus.valueOf(status));
    }
    */
}