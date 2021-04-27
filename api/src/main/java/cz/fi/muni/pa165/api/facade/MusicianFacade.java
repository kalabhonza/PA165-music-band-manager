package cz.fi.muni.pa165.api.facade;

import cz.fi.muni.pa165.api.dto.band.BandDTO;
import cz.fi.muni.pa165.api.dto.MusicianDTO;
import cz.fi.muni.pa165.enums.Instrument;

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
     * @param band Band of which musicians will be found
     * @return Musicians of given band
     */
    List<MusicianDTO> findAllByBand(BandDTO band);

    /**
     * Creates a new musician
     * @param musician Musician to be created
     */
    void create(MusicianDTO musician);

    /**
     * Sets new name to a musician
     * @param musician Musician which name will be changed
     * @param name New name of the musician
     */
    void setName(MusicianDTO musician, String name);

    /**
     * Sets new username to a musician
     * @param musician Musician which username will be changed
     * @param username New username of the musician
     */
    void setUsername(MusicianDTO musician, String username);

    /**
     * Sets new password to a musician
     * @param musician Musician which password will be changed
     * @param password New password of the musician
     */
    void setPassword(MusicianDTO musician, String password);

    /**
     * Sets instruments to a musician
     * @param musician Musician which instruments will be changed
     * @param instruments New instruments of a musician
     */
    void setInstruments(MusicianDTO musician, List<Instrument> instruments);

    /**
     * Adds a band offer to a musician
     * @param musician Musician who will get the new offer
     * @param band Offered band
     */
    void addOffer(MusicianDTO musician, BandDTO band);

    /**
     * Removes a band offer from a musician
     * @param musician Musician who will lose a band offer
     * @param band Offered band which will be removed from musician's offers
     */
    void removeOffer(MusicianDTO musician, BandDTO band);

    /**
     * Sets given band to musician
     * @param musician Musician that will be added into band
     * @param band Band into which will be the musician added
     */
    void acceptOffer(MusicianDTO musician, BandDTO band);

    /**
     * Removes musician
     * @param musician Musician to be removed
     */
    void remove(MusicianDTO musician);
}
