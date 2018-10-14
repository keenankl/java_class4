package edu.keenank.advancedjava.services;

import edu.keenank.advancedjava.model.DatabasePerson;
import edu.keenank.advancedjava.model.DatabaseStockSymbol;

import java.util.List;

/**
 * This interface describes an API for getting person data.
 */
public interface PersonService {
    /**
     * Get a list of all people
     * @return a list of Person instances
     * @throws PersonServiceException if a service cannot perform the requested operation
     */
    List<DatabasePerson> getPersons() throws PersonServiceException;

    /**
     * Add a new person or update the data of an existing person
     * @param person a person object to either update or create
     * @throws PersonServiceException if a service cannot perform the requested operation
     */
    void addOrUpdatePerson(DatabasePerson person) throws PersonServiceException;

    /**
     * Get a list of all of the stocks a person is interested in
     * @return a list of Strings representing stock symbols
     * @throws PersonServiceException if a service cannot perform the requested operation
     */
    List<DatabaseStockSymbol> getStockSymbols(DatabasePerson person) throws PersonServiceException;

    /**
     * Assign a stcok to a person
     * @param stockSymbol  The stockSymbol to assign
     * @param person The person to whom the stockSymbol is assigned
     * @throws PersonServiceException if a service cannot perform the requested operation
     */
    public void addStockToPerson(DatabaseStockSymbol stockSymbol, DatabasePerson person) throws PersonServiceException;
}
