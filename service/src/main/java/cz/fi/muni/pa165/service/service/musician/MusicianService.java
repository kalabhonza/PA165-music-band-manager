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
     * Accepts offer for a musician to join a band
     * @param musician musician that will accept the offer
     * @param band band to join
     * @return Updated musician
     */
    Musician acceptOffer(Musician musician, Band band);

    /**
     * Removes offer
     * @param band band to be removed
     * @param musician musician which will have his offer removed
     */
    void declineOffer(Musician musician, Band band);

    /**
     *
     * @param username
     * @param password
     * @return
     */
    Musician login(String username, String password);
}
