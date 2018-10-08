package edu.keenank.advancedjava;

/**
 * Enum to represent intervals
 * Hourly, Daily, and Weekly
 */

public enum IntervalEnum {

    /**
     * An enum for Hourly
     */
    HOURLY("HOURLY", 1),

    /**
     * An enum for each hour of a day
     */
    DAILY("DAILY", 24),

    /**
     * An enum for each hour in a week
     */
    WEEKLY("WEEKLY", 168);

    private final String symbol;
    private final int hours;

    /**
     * Constructs a new {@code IntervalEnum} instance
     *
     * @param symbol a {@code String} representation of an {@code enum} (HOURLY, DAILY, WEEKLY)
     */
    IntervalEnum(String symbol, int hours) {
        this.symbol = symbol;
        this.hours = hours;
    }

    /**
     * @return the amount of hours for the {@code enum}
     */
    public int amount() {
        return hours;
    }

    /**
     * @return a String representation of the {@code enum}
     */
    @Override
    public String toString() {
        return this.symbol;
    }

}
