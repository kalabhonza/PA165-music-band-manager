package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.entities.Band;
import cz.fi.muni.pa165.entities.Manager;

import java.util.List;

/**
 *
 * @author Igor Ignac
 */
public interface BandService {
    /**
     * Create new band
     * @param band band to be created
     * @return band id
     */
    Long createBand(Band band);

    /**
     * Updates band
     * @param band band to be updated
     * @return updated band
     */
    Band updateBand(Band band);

    /**
     * Find band by id
     * @param id id of band
     * @return Band with corresponding id
     */
    Band findBandById(Long id);

    /**
     * Find band by name
     * @param name name of band
     * @return Band with corresponding name
     */
    List<Band> findBandByName(String name);

    /**
     * Search database for band matching given Manager
     * @param manager band's manager
     * @return found band or null
     */
    Band findBandByManager(Manager manager);

    /**
     * Returns all bands
     * @return all bands
     */
    List<Band> findAllBands();

    /**
     * Deletes band
     * @param band band to be deleted
     */
    void deleteBand(Band band);
}
