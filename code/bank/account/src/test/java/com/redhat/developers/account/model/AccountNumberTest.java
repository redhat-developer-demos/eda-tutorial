package com.redhat.developers.account.model;

import com.redhat.developers.account.model.AccountNumber;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AccountNumberTest {

    @Test
    public void formatTo() {
        assertEquals("123-123", String.format("%s", AccountNumber.of(123123)));
        assertEquals("1234-321", String.format("%s", AccountNumber.of(1234321)));
    }

}