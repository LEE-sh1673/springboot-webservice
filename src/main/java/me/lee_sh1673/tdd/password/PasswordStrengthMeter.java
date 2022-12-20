package me.lee_sh1673.tdd.password;

import java.util.function.IntPredicate;

public class PasswordStrengthMeter {

    public PasswordStrength meter(final String password) {

        if (meetsInvalidInputCriteria(password)) {
            return PasswordStrength.INVALID;
        }
        int metCounts = getMetCriteriaCounts(password);

        if (meetsWeakCriteria(metCounts)) {
            return PasswordStrength.WEAK;
        }
        if (meetsNormalCriteria(metCounts)) {
            return PasswordStrength.NORMAL;
        }
        return PasswordStrength.STRONG;
    }

    private static boolean meetsInvalidInputCriteria(final String password) {
        return password == null || password.isEmpty();
    }

    private int getMetCriteriaCounts(final String password) {
        int metCounts = 0;

        if (meetsLengthCriteria(password)) {
            metCounts++;
        }
        if (meetsContainingNumberCriteria(password)) {
            metCounts++;
        }
        if (meetsContainingUppercaseCriteria(password)) {
            metCounts++;
        }
        return metCounts;
    }

    private static boolean meetsLengthCriteria(final String password) {
        return password.length() >= 8;
    }

    private static boolean meetsContainingNumberCriteria(final String password) {
        return meetsContaining(password, Character::isDigit);
    }

    private boolean meetsContainingUppercaseCriteria(final String password) {
        return meetsContaining(password, Character::isUpperCase);
    }

    private static boolean meetsContaining(final String password, final IntPredicate condition) {
        for (char ch : password.toCharArray()) {
            if (condition.test(ch)) {
                return true;
            }
        }
        return false;
    }

    private boolean meetsWeakCriteria(final int metCounts) {
        return metCounts <= 1;
    }

    private static boolean meetsNormalCriteria(final int metCounts) {
        return metCounts == 2;
    }
}
