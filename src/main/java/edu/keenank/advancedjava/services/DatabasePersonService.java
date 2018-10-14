package edu.keenank.advancedjava.services;

import edu.keenank.advancedjava.model.DatabasePerson;
import edu.keenank.advancedjava.model.DatabasePersonStock;
import edu.keenank.advancedjava.model.DatabaseStockSymbol;
import edu.keenank.advancedjava.utl.DatabaseUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is a concrete implementation of PersonService that reads data from a database.
 */
public class DatabasePersonService implements PersonService {

    public static final DatabasePersonService INSTANCE = new DatabasePersonService();

    protected DatabasePersonService() {
    }

    /**
     * Gets a list of all persons stored in the "person" database.
     * @return a list of Person instances
     * @throws PersonServiceException if a service can not perform the requested operation
     */
    public final List<DatabasePerson> getPersons() throws PersonServiceException {
        Session session = DatabaseUtils.getSessionFactory().openSession();
        List<DatabasePerson> persons = null;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(DatabasePerson.class);
            @SuppressWarnings("unchecked") List<DatabasePerson> list = criteria.list();
            persons = list;
        } catch (HibernateException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new PersonServiceException("Could not get Person data. " + e.getMessage(), e);
        } finally {
            if (transaction != null && transaction.isActive()) {
                transaction.commit();
            }
        }
        return persons;
    }

    /**
     * Adds a new person or updates the data of an existing person
     * @param person a person object to either update or create
     * @throws PersonServiceException if a service can not perform the requested operation
     */
    public final void addOrUpdatePerson(DatabasePerson person) throws PersonServiceException {
        Session session = DatabaseUtils.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(person);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new PersonServiceException("Could not add/update Person data. " + e.getMessage(), e);
        } finally {
            if (transaction != null && transaction.isActive()) {
                transaction.commit();
            }
        }
    }

    /**
     * Gets a list of all of the stocks a person is interested in
     * @return a list of Strings representing stock symbols
     * @throws PersonServiceException if a service can not perform the requested operation
     */
    public final List<DatabaseStockSymbol> getStockSymbols(DatabasePerson person) throws PersonServiceException {
        Session session =  DatabaseUtils.getSessionFactory().openSession();
        Transaction transaction = null;
        List<DatabaseStockSymbol> stockSymbols = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(DatabasePersonStock.class);
            criteria.add(Restrictions.eq("person", person));
            @SuppressWarnings("unchecked") List<DatabasePersonStock> list = criteria.list();
            stockSymbols = new ArrayList<DatabaseStockSymbol>();
            for (DatabasePersonStock personStock : list) {
                stockSymbols.add(personStock.getStockSymbol());
            }
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new PersonServiceException("Could not get Stock Symbols for entered Person. " + e.getMessage(), e);
        } finally {
            if (transaction != null && transaction.isActive()) {
                transaction.commit();
            }
        }
        return stockSymbols;
    }

    /**
     * Assigns a stock to a person
     * @param stockSymbol  The stockSymbol to assign
     * @param person The person to whom the stockSymbol is assigned
     * @throws PersonServiceException if a service can not perform the requested operation
     */
    public final void addStockToPerson(DatabaseStockSymbol stockSymbol, DatabasePerson person) throws PersonServiceException {
        Session session =  DatabaseUtils.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(stockSymbol);
            DatabasePersonStock personStock = new DatabasePersonStock();
            personStock.setStockSymbol(stockSymbol);
            personStock.setPerson(person);
            session.saveOrUpdate(stockSymbol);
            session.saveOrUpdate(personStock);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new PersonServiceException("Could not add/update Stock Symbols for entered Person. " + e.getMessage(), e);
        } finally {
            if (transaction != null && transaction.isActive()) {
                transaction.commit();
            }
        }
    }
}
