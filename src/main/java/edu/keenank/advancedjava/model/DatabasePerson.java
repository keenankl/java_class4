package edu.keenank.advancedjava.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Models the database person table
 */
@Entity
@Table(name="person")
public class DatabasePerson {

    private int id;
    private String firstName;
    private String lastName;

    /**
     * Constructs a {@code Person} that needs to be initialized
     */
    public DatabasePerson() {}

    /**
     * Constructs a valid {@code Person}
     * @param firstName sets the first name of the object
     * @param lastName sets the last name of the object
     */
    public DatabasePerson(String firstName, String lastName) {
        setFirstName(firstName);
        setLastName(lastName);
    }

    /**
     * Gets the unique ID for this {@code Person} instance
     * @return an integer value representing the unique ID for this Person
     */
    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    @GeneratedValue
    public int getId() {
        return id;
    }

    /**
     * Sets the unique ID for this {@code Person} instance.
     * @param id an integer value
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the first name of this {@code Person} instance
     */
    @Basic
    @Column(name = "first_name", nullable = false, insertable = true, updatable = true, length = 256)
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of this {@code Person} instance
     * @param firstName a String value
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the last name of this {@code Person}
     */
    @Basic
    @Column(name = "last_name", nullable = false, insertable = true, updatable = true, length = 256)
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of this {@code Person} instance
     * @param lastName a String value
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DatabasePerson)) return false;

        DatabasePerson person = (DatabasePerson) o;

        return this.id == person.id
                && this.firstName.equals(person.firstName)
                && this.lastName.equals(person.lastName);
    }

    /**
     * Sets the hash code
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }

    /**
     * Returns the information as a string
     */
    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
