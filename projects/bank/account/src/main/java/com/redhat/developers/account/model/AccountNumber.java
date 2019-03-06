package com.redhat.developers.account.model;

import com.google.common.base.MoreObjects;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Formattable;
import java.util.Formatter;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class AccountNumber implements Serializable, Formattable, Comparable<AccountNumber> {

    private static final Comparator<AccountNumber> ACCOUNT_NUMBER_COMPARATOR = Comparator.comparingLong((AccountNumber a) -> a.value);

    private final int value;

    private AccountNumber(int value) {
        this.value = value;
    }

    public static AccountNumber of(Integer value) {
        checkNotNull(value);
        checkArgument(value > 10000);
        return new AccountNumber(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AccountNumber) {
            AccountNumber other = (AccountNumber) obj;
            return this.value == other.value;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("value", value)
                .toString();
    }

    @Override
    public int compareTo(AccountNumber o) {
        return ACCOUNT_NUMBER_COMPARATOR.compare(this, o);
    }

    @Override
    public void formatTo(Formatter formatter, int flags, int width, int precision) {
        formatter.format("%s-%s", value / 1000, value % 1000);
    }

    public Integer toDatabaseValue() {
        return value;
    }

}
