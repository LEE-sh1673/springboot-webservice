package me.lee_sh1673.tdd.expirydatecalculator;

import java.time.LocalDate;

public class PayData {

    private LocalDate billingDate;

    private int payAmount;

    private PayData() {}

    public LocalDate getBillingDate() {
        return billingDate;
    }

    public int getPayAmount() {
        return payAmount;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final PayData data = new PayData();

        public Builder billingDate(final LocalDate billingDate) {
            data.billingDate = billingDate;
            return this;
        }

        public Builder payAmount(final int payAmount) {
            data.payAmount = payAmount;
            return this;
        }

        public PayData build() {
            return data;
        }
    }
}
