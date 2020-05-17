package com.greeting.controller;

import com.greeting.service.GreetingService;
import com.greeting.util.Account;
import com.greeting.util.AccountType;
import com.greeting.util.GreetingConstants;
import org.apache.commons.lang3.EnumUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(GreetingEndpoint.class)
@ActiveProfiles("test")
public class GreetingEndpointTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GreetingService greetingService;

    @Test
    public void shouldReturnGreetingMessageForPersonalAccount() throws Exception {
        int userId = 123;
        when(greetingService.getGreetingById(anyString(), any(Integer.class)))
            .thenReturn(GreetingConstants.GREETING_USER_ID_MESSAGE + userId);

        mockMvc.perform(
                get("/greeting/account/personal/id/" + userId)
                .contentType(APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").value(GreetingConstants.GREETING_USER_ID_MESSAGE + userId));
    }

    @Test
    public void shouldReturnBadRequestForPersonalAccount() throws Exception {
        int userId = -123;
        when(greetingService.getGreetingById(anyString(), any(Integer.class)))
            .thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST,
                userId + " is not valid. Must be a positive value"));

        mockMvc.perform(
            get("/greeting/account/personal/id/" + userId)
                .contentType(APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(status().reason(userId + " is not valid. Must be a positive value"));
    }

    @Test
    public void shouldReturnBadRequestForBusinessAccountAndId() throws Exception {
        int userId = 123;
        when(greetingService.getGreetingById(anyString(), any(Integer.class)))
            .thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Business and " + userId + " combination not supported."));

        mockMvc.perform(
            get("/greeting/account/business/id/" + userId)
                .contentType(APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(status().reason("Business and " + userId + " combination not supported."));
    }

    @Test
    public void shouldReturnBadRequestForInvalidAccount() throws Exception {
        int userId = 123;
        when(greetingService.getGreetingById(anyString(), any(Integer.class)))
            .thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Invalid is not valid account. " +
                    "Valid accounts are " + EnumUtils.getEnumList(Account.class)));

        mockMvc.perform(
            get("/greeting/account/Invalid/id/" + userId)
                .contentType(APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(status().reason("Invalid is not valid account. " +
                "Valid accounts are " + EnumUtils.getEnumList(Account.class)));
    }

    @Test
    public void shouldReturnGreetingMessageForBusinessAccount() throws Exception {
        String type = "small";
        when(greetingService.getGreetingByType(anyString(), anyString()))
            .thenReturn(GreetingConstants.WELCOME_BUSINESS_USER_MESSAGE);

        mockMvc.perform(
            get("/greeting/account/business/type/small")
                .contentType(APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").value(GreetingConstants.WELCOME_BUSINESS_USER_MESSAGE));
    }

    @Test
    public void shouldReturnBadRequestForPersonalAccountAndType() throws Exception {
        String type = "small";
        when(greetingService.getGreetingByType(anyString(), anyString()))
            .thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "personal and small combination not supported."));

        mockMvc.perform(
            get("/greeting/account/personal/type/small")
                .contentType(APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(status().reason("personal and small combination not supported."));
    }

    @Test
    public void shouldReturnBadRequestForBusinessAccountAndInvalidType() throws Exception {
        when(greetingService.getGreetingByType(anyString(), anyString()))
            .thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "wrongType is not valid account type. " +
                    "Valid account types are " + EnumUtils.getEnumList(AccountType.class)));

        mockMvc.perform(
            get("/greeting/account/personal/type/wrongType")
                .contentType(APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(status().reason("wrongType is not valid account type. " +
                "Valid account types are " + EnumUtils.getEnumList(AccountType.class)));
    }

    @Test
    public void shouldReturnBadRequestForBusinessAccountAndBigType() throws Exception {
        when(greetingService.getGreetingByType(anyString(), anyString()))
            .thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "business and big combination not supported."));

        mockMvc.perform(
            get("/greeting/account/personal/type/wrongType")
                .contentType(APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(status().reason("business and big combination not supported."));
    }
}
