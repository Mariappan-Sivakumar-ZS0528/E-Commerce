package com.app.shopping.ecommerce.util;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateUtilTest {

    DateUtil dateUtil = new DateUtil();
    Calendar calendar = Calendar.getInstance();
    @Test
    public void testGetStartOfDay() {
        Date startOfDay = dateUtil.getStartOfDay();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date expectedStartOfDay = calendar.getTime();

        assertEquals(expectedStartOfDay, startOfDay);
    }

    @Test
    public void testGetEndOfDay() {
        Date endOfDay = dateUtil.getEndOfDay();

        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        Date expectedEndOfDay = calendar.getTime();

        assertEquals(expectedEndOfDay, endOfDay);
    }
}
