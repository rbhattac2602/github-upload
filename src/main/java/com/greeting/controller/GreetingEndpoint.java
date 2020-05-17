package com.greeting.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import com.greeting.service.GreetingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
public class GreetingEndpoint implements GreetingController {

    @Autowired
    private GreetingService greetingService;

    @Override
    public String getGreetingById(@NotNull String account, @NotNull Integer id) {
        return greetingService.getGreetingById(account, id);
    }

    @Override
    public String getGreetingByType(@NotNull String account, @NotNull String accountType) {
        return greetingService.getGreetingByType(account, accountType);
    }

    @ExceptionHandler(NumberFormatException.class)
    public void handleNumberFormatException(NumberFormatException e,
                                            HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), "Invalid value: " + e.getMessage());
    }
}
