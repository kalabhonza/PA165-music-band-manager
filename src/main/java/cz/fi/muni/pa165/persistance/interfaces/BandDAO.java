package cz.fi.muni.pa165.persistance.interfaces;

import cz.fi.muni.pa165.entities.Band;
import cz.fi.muni.pa165.entities.Manager;

import java.util.List;

/**
 * @author Igor Ignác
 */
public interface BandDAO {

    /**
     * Saves band into database
     * @param band band object which should be saved
     */
    void create(Band band);

    /**
     * Updates band in database
     * @param band band which should be updated
     * @return updated band
     */
    Band update(Band band);

    /**
     * Search database for band matching given ID
     * @param id band's ID
     * @return found band or null
     */
    Band findBandById(Long id);

    /**
     * Search database for band matching given name
     * @param name band's name
     * @return found band or null
     */
    Band findBandByName(String name);

    /**
     * Search database for band matching given Manager
     * @param manager band's manager
     * @return found band or null
     */
    Band findBandByManager(Manager manager);

    /**
     * Returns all bands in database
     * @return all bands in database
     */
    List<Band> findAll();

    /**
     * Deletes band from database
     * @param band band to be deleted
     */
    void deleteBand(Band band);
}
