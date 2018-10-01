package edu.keenank.advancedjava;

import jdk.nashorn.internal.ir.annotations.Immutable;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

@Immutable
public class IntervalEnumTest {

    @Test
    public void testHourlyInt() {
        assertEquals("The Hourly amount is 1", IntervalEnum.HOURLY.amount(), 1 );
    }

    @Test
    public void testHourlySymbol() {
        assertEquals("The Hourly symbol is \"HOURLY\"", IntervalEnum.HOURLY.toString(), "HOURLY");
    }

    @Test
    public void testDailyInt() {
        assertEquals("The Daily amount is 24", IntervalEnum.DAILY.amount(), 24 );
    }

    @Test
    public void testDailySymbol() {
        assertEquals("The Daily symbol is \"DAILY\"", IntervalEnum.DAILY.toString(), "DAILY");
    }

    @Test
    public void testWeeklyInt() {
        assertEquals("The Weekly amount is 168", IntervalEnum.WEEKLY.amount(), 168 );
    }

    @Test
    public void testWeeklySymbol() {
        assertEquals("The Weekly symbol is \"WEEKLY\"", IntervalEnum.WEEKLY.toString(), "WEEKLY");
    }

}
