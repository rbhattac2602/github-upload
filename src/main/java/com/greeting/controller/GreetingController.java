package com.greeting.controller;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/greeting")
interface GreetingController {

    @GetMapping("account/{account}/id/{accountId}")
    String getGreetingById(@PathVariable("account") @NotNull String account, @PathVariable("accountId") @NotNull @Validated Integer id);

    @GetMapping("account/{account}/type/{accountType}")
    String getGreetingByType(@PathVariable("account") @NotNull String account, @PathVariable("accountType") @NotNull String accountType);
}
