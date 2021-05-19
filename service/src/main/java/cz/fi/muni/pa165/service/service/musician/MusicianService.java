package cz.fi.muni.pa165.service.service.musician;

import cz.fi.muni.pa165.entities.Band;
import cz.fi.muni.pa165.entities.Musician;

import java.util.List;

/**
 * @author Aleš Paroulek
 */
public interface MusicianService {
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
     * @param band Band of which musicians will be found
     * @return Musicians of given band
     */
    List<Musician> findAllByBand(Band band);

    /**
     * Creates a new musician
     * @param musician Musician to be created
     * @return id of created musician
     */
    Long create(Musician musician);

    /**
     * Updates a musician
     * @param musician Musician to be updated
     * @return Updated musician
     */
    Musician update(Musician musician);

    /**
     * Removes musician
     * @param musician Musician to be removed
     */
    void remove(Musician musician);

    /**
     *
     * @param username
     * @param password
     * @return
     */
    Musician login(String username, String password);
}
