package cz.fi.muni.pa165.persistance.interfaces;

import cz.fi.muni.pa165.entities.Concert;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Aleš Paroulek
 */
public interface ConcertDAO {

    /**
     * Finds concert by its id
     * @param id Concert's id
     * @return Concert with given id or null if it doesn't exist
     */
    Concert findById(long id);

    /**
     * Finds all concerts
     * @return All concerts in database
     */
    List<Concert> findAll();

    /**
     * Finds all concert of given date
     * @param date Date of which concerts will be found
     * @return Concerts of given date
     */
    List<Concert> findAllByDate(LocalDate date);

    /**
     * Creates a new concert
     * @param concert Concert to be created
     * @return id of created concert
     */
    Long create(Concert concert);

    /**
     * Updates given concert
     * @param concert Concert to be updated
     * @return updated concert
     */
    Concert update(Concert concert);

    /**
     * Removes given concert
     * @param concert Concert to be removed
     */
    void remove(Concert concert);

}
