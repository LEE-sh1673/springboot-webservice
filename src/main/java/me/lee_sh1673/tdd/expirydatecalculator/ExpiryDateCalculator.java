package me.lee_sh1673.tdd.expirydatecalculator;

import java.time.LocalDate;

public class ExpiryDateCalculator {

    public LocalDate calculateExpiryDate(final PayData payData) {
        return payData.getBillingDate().plusMonths(1);
    }
}
