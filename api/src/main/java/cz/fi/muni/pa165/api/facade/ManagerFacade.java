package cz.fi.muni.pa165.api.facade;

import cz.fi.muni.pa165.api.dto.band.BandDTO;
import cz.fi.muni.pa165.api.dto.manager.ManagerCreateDTO;
import cz.fi.muni.pa165.api.dto.manager.ManagerDTO;
import cz.fi.muni.pa165.api.dto.manager.ManagerUpdateDTO;

import java.util.List;

/**
 * @author Jan Kaláb
 */
public interface ManagerFacade {
    /**
     * Find Manager by its id
     *
     * @param id manager id
     * @return Manager or {@code null}, if there is no Manager with requested id
     */
    ManagerDTO findById(long id);

    /**
     * Find all Managers
     * @return List of all Managers
     */
    List<ManagerDTO> findAll();

    /**
     *
     * @param bandId
     * @return
     */
    BandDTO getManagerBand(Long bandId);

    /**
     *
     * @param manager
     * @param bandID
     */
    void setManagerBand(ManagerDTO manager, Long bandID);

    /**
     * Stores new manager
     * @param manager to be created
     * @return id of manager
     */
    Long create(ManagerCreateDTO manager);

    /**
     * Updates existing Manager
     * @param manager to be updated
     * @return created manager
     */
    ManagerDTO update(ManagerUpdateDTO manager);

    /**
     *
     */
    void setOffer(Long musicianID, Long bandID);

    /**
     * Removes Manager
     * @param manager to be deleted
     */
    void remove(ManagerDTO manager);

    /**
     * Find Manager by its userName
     * @param userName of the Manager
     * @return Manager or {@code null}, if there is no Manager with given userName
     */
    ManagerDTO findByUserName(String userName);

    /**
     * Find Manager by its name
     * @param name of the Manager
     * @return Manager or {@code null}, if there is no Manager with given name
     */
    List<ManagerDTO> findByName(String name);

    /**
     * Login of manager to system
     * @param username
     * @param password
     * @return
     */
    ManagerDTO login(String username, String password);
}
