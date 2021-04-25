package cz.fi.muni.pa165.api.facade;

import cz.fi.muni.pa165.api.dto.ConcertDTO;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Aleš Paroulek
 */
public interface ConcertFacade {
    /**
     * Finds concert by its id
     * @param id Concert's id
     * @return Concert with given id or null if it doesn't exist
     */
    ConcertDTO findById(long id);

    /**
     * Finds all concerts
     * @return All concerts in database
     */
    List<ConcertDTO> findAll();

    /**
     * Finds all concert of given date
     * @param date Date of which concerts will be found
     * @return Concerts of given date
     */
    List<ConcertDTO> findAllByDate(LocalDate date);

    /**
     * Creates a new concert
     * @param concert Concert to be created
     */
    void create(ConcertDTO concert);

    /**
     * Sets name of given concert
     * @param concert Concert which name will be changed
     * @param name New name of the concert
     */
    void setName(ConcertDTO concert, String name);

    /**
     * Sets date of given concert
     * @param concert Concert which date will be changed
     * @param date New date of the concert
     */
    void setDate(ConcertDTO concert, LocalDate date);

    /**
     * Removes given concert
     * @param concert Concert to be removed
     */
    void remove(ConcertDTO concert);
}
