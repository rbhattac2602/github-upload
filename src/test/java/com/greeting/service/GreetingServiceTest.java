package com.greeting.service;

import com.greeting.util.Account;
import com.greeting.util.AccountType;
import com.greeting.util.GreetingConstants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.server.ResponseStatusException;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class GreetingServiceTest {
    @InjectMocks
    private GreetingService greetingService = new GreetingService();

    @Test
    public void testGreetingByPersonalAccountWithId() {
        int userId = 123;
        String greetingMessage = greetingService.getGreetingById(Account.Personal.name(), userId);
        assertEquals(greetingMessage, GreetingConstants.GREETING_USER_ID_MESSAGE + userId);
    }

    @Test(expected = ResponseStatusException.class)
    public void testGreetingByPersonalAccountWithInvalidId() {
        greetingService.getGreetingById(Account.Personal.name(), -123);
    }

    @Test(expected = ResponseStatusException.class)
    public void testGreetingByInvalidAccountWithInvalidId() {
        int userId = -123;
        greetingService.getGreetingById("Invalid", userId);
    }

    @Test(expected = ResponseStatusException.class)
    public void testGreetingByBusinessAccountWithId() {
        greetingService.getGreetingById(Account.Business.name(), 123);
    }

    @Test
    public void testGreetingByBusinessAccountWithType() {
        String greetingMessage = greetingService.getGreetingByType(Account.Business.name(), AccountType.Big.name());
        assertEquals(greetingMessage, GreetingConstants.WELCOME_BUSINESS_USER_MESSAGE);
    }

    @Test(expected = ResponseStatusException.class)
    public void testGreetingByPersonalAccountWithType() {
        greetingService.getGreetingByType(Account.Personal.name(), AccountType.Small.name());
    }

    @Test(expected = ResponseStatusException.class)
    public void testGreetingByPersonalAccountWithTypeBig() {
        greetingService.getGreetingByType(Account.Business.name(), AccountType.Small.name());
    }
}
