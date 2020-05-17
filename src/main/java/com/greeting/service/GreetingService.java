package com.greeting.service;

import com.greeting.util.Account;
import com.greeting.util.AccountType;
import com.greeting.util.GreetingConstants;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class GreetingService {
    /**
     * Get greeting message by id
     *
     * @param account account Valid values: [Business, Personal]
     * @param id      Id of the account Allowed: Positive integers
     * @return Greeting message containing user id
     */
    public String getGreetingById(String account, int id) {
        if (isValidAccount(account) && isValidateAccountId(id)
            && EnumUtils.getEnumIgnoreCase(Account.class, account).equals(Account.Personal)) {

            return GreetingConstants.GREETING_USER_ID_MESSAGE + id;
        }

        throw new ResponseStatusException(
            HttpStatus.NOT_IMPLEMENTED, "The path is not yet implemented.");
    }

    /**
     * Get greeting message by type
     *
     * @param account       account based on user type. Valid values: [Business, Personal]
     * @param accountType   Type of account based on size. Valid Values: [Big, Small]
     * @return Greeting message for business user
     */
    public String getGreetingByType(String account, String accountType) {
        if (isValidAccount(account) && isValidateAccountType(accountType) &&
            EnumUtils.getEnumIgnoreCase(Account.class, account).equals(Account.Business) &&
            EnumUtils.getEnumIgnoreCase(AccountType.class, accountType).equals(AccountType.Big)) {

            return GreetingConstants.WELCOME_BUSINESS_USER_MESSAGE;
        }

        throw new ResponseStatusException(
            HttpStatus.NOT_IMPLEMENTED, "The path is not yet implemented.");
    }

    private boolean isValidAccount(String account) {
        if (!EnumUtils.isValidEnumIgnoreCase(Account.class, account)) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, account + " is not a valid account. " +
                "Valid accounts are " + EnumUtils.getEnumList(Account.class));
        }

        return true;
    }

    private boolean isValidateAccountId(int id) {
        if (id <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, id + " is not valid. Must be a positive integer");
        }
        return true;
    }

    private boolean isValidateAccountType(String accountType) {
        if (!EnumUtils.isValidEnumIgnoreCase(AccountType.class, accountType)) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, accountType + " is not a valid account type. " +
                "Valid account types are " + EnumUtils.getEnumList(AccountType.class));
        }

        return true;
    }
}
