package edu.keenank.advancedjava.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Models the database person stock table
 */
@Entity
@Table(name="person_stocks", catalog="stocks")
public class DatabasePersonStock {

    private int id;
    private DatabasePerson person;
    private DatabaseStockSymbol stockSymbol;

    /**
     * Constructs a {@code DatabasePersonStock}
     */
    public DatabasePersonStock() {}

    /**
     * Constructs a valid {@code DatabasePersonStock} instance
     * @param person the person assigned to the stock symbol
     * @param symbol  the stock symbol assigned to the person
     */
    public DatabasePersonStock(DatabasePerson person, DatabaseStockSymbol symbol) {
        setPerson(person);
        setStockSymbol(symbol);
    }

    /**
     * Gets the unique ID for this {@code DatabasePersonStock}
     * @return an integer value representing the unique ID
     */
    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    @GeneratedValue
    public int getId() {
        return id;
    }

    /**
     * Sets the unique ID for this {@code DatabasePersonStock}
     * @param id an integer value
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns a defensive copy of the mutable {@code DatabasePerson}
     * assigned to the corresponding field of this class
     * @return the person associated with this {@code DatabasePersonStock}
     */
    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id", nullable = false)
    public DatabasePerson getPerson() {
        DatabasePerson person = new DatabasePerson(this.person.getFirstName(), this.person.getLastName());
        person.setId(this.person.getId());
        return person;
    }

    /**
     * Sets the first name of this {@code DatabasePersonStock}
     * @param person a Person instance
     */
    public void setPerson(DatabasePerson person) {
        this.person = person;
    }

    /**
     * Returns a defensive copy of the {@code DatabaseStockSymbol}
     * assigned to the corresponding field of this class
     * @return the stock symbol of this {@code DatabasePersonStock}
     */
    @ManyToOne
    @JoinColumn(name = "symbol_id", referencedColumnName = "id", nullable = false)
    public DatabaseStockSymbol getStockSymbol() {

        DatabaseStockSymbol stockSymbol = new DatabaseStockSymbol(this.stockSymbol.getSymbol());
        stockSymbol.setId(this.stockSymbol.getId());
        return stockSymbol;
    }

    /**
     * Sets the stock symbol of this {@code DatabasePersonStock}
     * @param stockSymbol a String value
     */
    public void setStockSymbol(DatabaseStockSymbol stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof  DatabasePersonStock)) return false;

        DatabasePersonStock personStock = (DatabasePersonStock) o;

        return this.id == personStock.id
                && this.person.equals(personStock.person)
                && this.stockSymbol.equals(personStock.stockSymbol);
    }

    /**
     * Sets the hash code
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + id;
        result = 31 * result + (person != null ? person.hashCode() : 0);
        result = 31 * result + (stockSymbol != null ? stockSymbol.hashCode() : 0);
        return result;
    }

    /**
     * Returns the information as a string
     */
    @Override
    public String toString() {
        return "PersonStock{" +
                "id=" + id +
                ", person='" + person + '\'' +
                ", stockSymbol='" + stockSymbol + '\'' +
                '}';
    }
}
