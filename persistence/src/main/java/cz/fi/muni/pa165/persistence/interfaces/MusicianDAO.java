package cz.fi.muni.pa165.persistence.interfaces;

import cz.fi.muni.pa165.entities.Band;
import cz.fi.muni.pa165.entities.Musician;

import java.util.List;

/**
 * @author Aleš Paroulek
 */
public interface MusicianDAO {

    /**
     * Finds musician by its id
     * @param id Musician's id
     * @return Musician with given id or null if it doesn't exist
     */
    Musician findById(long id);

    /**
     * Finds musician by its username
     * @param userName Username of a musician
     * @return Musician with given username or null if it doesn't exist
     */
    Musician findByUserName(String userName);

    /**
     * Finds all musicians
     * @return All musicians in database
     */
    List<Musician> findAll();

    /**
     * Finds all musicians of given band
     * @param bandId Band of which musicians will be found
     * @return Musicians of given band
     */
    List<Musician> findAllByBand(Long bandId);

    /**
     * Finds all musicians who don't have a band
     * @return musicians without a band
     */
    List<Musician> findAllWithoutBand();

    /**
     * Creates a new musician
     * @param musician Musician to be created
     * @return id of created musician
     */
    Long create(Musician musician);

    /**
     * Updates musician
     * @param musician Musician to be updated
     * @return updated musician
     */
    Musician update(Musician musician);

    /**
     * Removes musician
     * @param musician Musician to be removed
     */
    void remove(Musician musician);
}
