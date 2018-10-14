package edu.keenank.advancedjava.model;

import org.joda.time.DateTime;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Models the database stock quote table
 */
@Entity
@Table(name="quotes", catalog="stocks")
public class DatabaseStockQuote extends StockData{
    private int id;
    private Timestamp time;
    private BigDecimal price;
    private DatabaseStockSymbol stockSymbol;

    /**
     * Constructs a {@code DatabaseStockQuote}
     */
    public DatabaseStockQuote() {}

    /**
     * Constructs a valid {@code DatabaseStockQuote}
     * @param time the time to assign
     * @param price  the price to assign
     * @param symbol the stockSymbol to assign
     */
    public DatabaseStockQuote(DateTime time, BigDecimal price, DatabaseStockSymbol symbol) {
        setTime(time);
        setPrice(price);
        setStockSymbol(symbol);
    }

    /**
     * @return the id field of this {@code DatabaseStockQuote}
     */
    @Id
    @Column(name = "id",  nullable = false, insertable = true, updatable = true)
    @GeneratedValue
    public int getId() {
        return id;
    }

    /**
     * Sets the id field of this user to the parameter value
     * @param id an integer value
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the time field of this {@code DatabaseStockQuote}
     */
    @Basic
    @Column(name = "time", nullable = false, insertable = true, updatable = true)
    public Timestamp getTime() {
        return time;
    }

    /**
     * Sets the time field of this user to the parameter value
     * @param time a Timestamp object
     */
    public void setTime(DateTime time) {
        this.time = new Timestamp(time.getMillis());
    }

    /**
     * @return the price field of this {@code DatabaseStockQuote}
     */
    @Basic
    @Column(name = "price", nullable = false, insertable = true, updatable = true, precision = 2)
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets the price field of this {@code DatabaseStockQuote}
     * @param price a BigDecimal object
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Returns a defensive copy of the mutable {@code DatabaseStockSymbol}
     * assigned to the corresponding field of this class
     * @return the stockSymbol field of this {@code DatabaseStockQuote}
     */
    @ManyToOne
    @JoinColumn(name = "symbol_id", referencedColumnName = "id",nullable = false)
    public DatabaseStockSymbol getStockSymbol() {
        DatabaseStockSymbol stockSymbol = new DatabaseStockSymbol(this.stockSymbol.getSymbol());
        stockSymbol.setId(this.stockSymbol.getId());
        return stockSymbol;
    }

    /**
     * Sets the stockSymbol field of this {@code DatabaseStockQuote}
     * @param stockSymbol a DatabaseStockSymbol object
     */
    public void setStockSymbol(DatabaseStockSymbol stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DatabaseStockQuote)) return false;

        DatabaseStockQuote stockQuote = (DatabaseStockQuote) o;

        return this.id == stockQuote.id
                && this.time.equals(stockQuote.time)
                && this.price.equals(stockQuote.price)
                && this.stockSymbol.equals(stockQuote.stockSymbol);
    }

    /**
     * Sets the hash code
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + id;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (stockSymbol != null ? stockSymbol.hashCode() : 0);
        return result;
    }

    /**
     * Returns the information as a string
     */
    @Override
    public String toString() {
        String dateString = simpleDateFormat.format(time);
        return "StockQuote{" +
                "id =" + id +
                ", stockSymbol=" + stockSymbol +
                ", date='" + dateString +
                ", price='" + price + '\'' +
                '}';
    }
}