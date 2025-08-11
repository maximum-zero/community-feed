package org.maximum0.common;

import java.time.LocalDate;

public class TimeCalculator {
    private TimeCalculator() {}

    public static LocalDate getDateDaysAgo(int daysAgo) {
        LocalDate currentDate = LocalDate.now();
        return currentDate.minusDays(daysAgo);
    }
}
