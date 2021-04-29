package cz.fi.muni.pa165.api.facade;

import cz.fi.muni.pa165.api.dto.band.BandCreateDTO;
import cz.fi.muni.pa165.api.dto.band.BandDTO;
import cz.fi.muni.pa165.api.dto.manager.ManagerDTO;
import cz.fi.muni.pa165.api.dto.band.BandUpdateDTO;

import java.util.List;

/**
 * Interface for Band facade
 *
 * @author Igor Ignác
 */
public interface BandFacade {
    /**
     * Create new band
     * @param band band to be created
     * @return id of created band
     */
    Long createBand(BandCreateDTO band);

    /**
     * Updates band
     * @param band band to be updated
     * @return updated band
     */
    BandDTO updateBand(BandUpdateDTO band);

    /**
     * Find band by id
     * @param id id of band
     * @return Band with corresponding id
     */
    BandDTO findBandById(Long id);

    /**
     * Find band by name
     * @param name name of band
     * @return Band with corresponding name
     */
    List<BandDTO> findBandByName(String name);

    /**
     * Search database for band matching given Manager
     * @param manager band's manager
     * @return found band or null
     */
    BandDTO findBandByManager(ManagerDTO manager);

    /**
     * Returns all bands
     * @return all bands
     */
    List<BandDTO> findAllBands();

    /**
     * Deletes band
     * @param band band to be deleted
     */
    void deleteBand(BandDTO band);
}
