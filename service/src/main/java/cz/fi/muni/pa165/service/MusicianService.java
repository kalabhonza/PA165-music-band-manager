package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.entities.Band;
import cz.fi.muni.pa165.entities.Musician;
import cz.fi.muni.pa165.enums.Instrument;

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
     */
    void create(Musician musician);

    /**
     * Sets new name to a musician
     * @param musician Musician which name will be changed
     * @param name New name of the musician
     */
    void setName(Musician musician, String name);

    /**
     * Sets new username to a musician
     * @param musician Musician which username will be changed
     * @param username New username of the musician
     */
    void setUsername(Musician musician, String username);

    /**
     * Sets new password to a musician
     * @param musician Musician which password will be changed
     * @param password New password of the musician
     */
    void setPassword(Musician musician, String password);

    /**
     * Sets instruments to a musician
     * @param musician Musician which instruments will be changed
     * @param instruments New instruments of a musician
     */
    void setInstruments(Musician musician, List<Instrument> instruments);

    /**
     * Adds a band offer to a musician
     * @param musician Musician who will get the new offer
     * @param band Offered band
     */
    void addOffer(Musician musician, Band band);

    /**
     * Removes a band offer from a musician
     * @param musician Musician who will lose a band offer
     * @param band Offered band which will be removed from musician's offers
     */
    void removeOffer(Musician musician, Band band);

    /**
     * Sets given band to musician
     * @param musician Musician that will be added into band
     * @param band Band into which will be the musician added
     */
    void acceptOffer(Musician musician, Band band);

    /**
     * Removes musician
     * @param musician Musician to be removed
     */
    void remove(Musician musician);
}
