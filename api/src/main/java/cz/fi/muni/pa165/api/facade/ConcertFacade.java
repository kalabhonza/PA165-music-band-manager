package cz.fi.muni.pa165.api.facade;

import cz.fi.muni.pa165.api.dto.ConcertCreateDTO;
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
     * @return id of created concert
     */
    Long create(ConcertCreateDTO concert);

    /**
     * Updates concert
     * @param concert Concert to be updated
     * @return updated concert
     */
    ConcertDTO update(ConcertDTO concert);

    /**
     * Removes given concert
     * @param concert Concert to be removed
     */
    void remove(ConcertDTO concert);
}
