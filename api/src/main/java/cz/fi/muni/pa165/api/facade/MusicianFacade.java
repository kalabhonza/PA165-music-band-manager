package cz.fi.muni.pa165.api.facade;

import cz.fi.muni.pa165.api.dto.band.BandDTO;
import cz.fi.muni.pa165.api.dto.musician.MusicianCreateDTO;
import cz.fi.muni.pa165.api.dto.musician.MusicianDTO;
import cz.fi.muni.pa165.api.dto.musician.MusicianUpdateDTO;

import java.util.List;

/**
 * @author Aleš Paroulek
 */
public interface MusicianFacade {
    /**
     * Finds musician by its id
     * @param id Musician's id
     * @return Musician with given id or null if it doesn't exist
     */
    MusicianDTO findById(long id);

    /**
     * Finds musician by its username
     * @param userName Username of a musician
     * @return Musician with given username or null if it doesn't exist
     */
    MusicianDTO findByUserName(String userName);

    /**
     * Finds all musicians
     * @return All musicians in database
     */
    List<MusicianDTO> findAll();

    /**
     * Finds all musicians of given band
     * @param bandId Band of which musicians will be found
     * @return Musicians of given band
     */
    List<MusicianDTO> findAllByBand(Long bandId);

    /**
     * Finds all musicians who don't have a band
     * @return musicians without a band
     */
    List<MusicianDTO> findAllWithoutBand();

    /**
     * Creates a new musician
     * @param musician Musician to be created
     * @return id of created musician
     */
    Long create(MusicianCreateDTO musician);

    /**
     * Updates musician
     * @param musician Musician to be updated
     * @return updated musician
     */
    MusicianDTO update(MusicianUpdateDTO musician);

    /**
     * Removes musician
     * @param musician Musician to be removed
     */
    void remove(MusicianDTO musician);

    /**
     * Accepts offer for a musician to join a band
     * @param musician musician that will accept the offer
     * @param band band to join
     * @return Updated musician
     */
    MusicianDTO acceptOffer(MusicianDTO musician, BandDTO band);

    /**
     * Decline offer for a musician to join a band
     * @param musician musician that will decline the offer
     * @param band band not to join
     */
    void declineOffer(MusicianDTO musician, BandDTO band);

    /**
     * Login musician into the system
     * @param username musician username
     * @param password musician password
     * @return musician object
     */
    MusicianDTO login(String username, String password);

    /**
     * Logout user out of the system
     */
    void logout();
}
