package edu.keenank.advancedjava.model;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Models the database stock symbol table
 */
@Entity
@Table(name="stock_symbols", catalog="stocks")
public class DatabaseStockSymbol {

    private int id;
    private String symbol;

    /**
     * Constructs a {@code StockSymbol}
     */
    public DatabaseStockSymbol() {}

    /**
     * Constructs a valid {@code StockSymbol}
     * @param symbol
     */
    public DatabaseStockSymbol(String symbol) {
        setSymbol(symbol);
    }

    /**
     * @return the id field of this {@code StockSymbol}
     */
    @Id
    @Column(name = "id",  nullable = false, insertable = true, updatable = true)
    @GeneratedValue
    public int getId() {
        return id;
    }

    /**
     * Sets the id field of this {@code StockSymbol}
     * @param id an int value
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the symbol field of this {@code StockSymbol}
     */
    @Basic
    @Column(name = "symbol", nullable = false, insertable = true, updatable = true, length = 4)
    public String getSymbol() {
        return symbol;
    }

    /**
     * Sets the symbol field of this {@code StockSymbol}
     * @param symbol a String
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DatabaseStockSymbol)) return false;

        DatabaseStockSymbol stockSymbol = (DatabaseStockSymbol) o;

        return this.id == stockSymbol.id
                && this.symbol.equals(stockSymbol.symbol);
    }

    /**
     * Sets the hash code
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + id;
        result = 31 * result + (symbol != null ? symbol.hashCode() : 0);
        return result;
    }

    /**
     * Returns the information as a string
     */
    @Override
    public String toString() {
        return "symbol=" + symbol;
    }
}
