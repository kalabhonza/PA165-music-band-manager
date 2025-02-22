package cz.fi.muni.pa165.service.service.manager;

import cz.fi.muni.pa165.api.dto.band.BandDTO;
import cz.fi.muni.pa165.api.dto.manager.ManagerDTO;
import cz.fi.muni.pa165.entities.Band;
import cz.fi.muni.pa165.entities.Manager;
import java.util.List;

/**
 * @author Jan Kaláb
 */
public interface ManagerService {
    /**
     * Find Manager by its id
     *
     * @param id manager id
     * @return Manager or {@code null}, if there is no Manager with requested id
     */
    Manager findById(long id);

    /**
     *
     * @param bandId
     * @return
     */
    Band getManagerBand(Long bandId);

    /**
     *
     * @param managerId
     * @param bandID
     */
    void setManagerBand(Long managerId, Long bandID);

    /**
     * Find all Managers
     * @return List of all Managers
     */
    List<Manager> findAll();

    /**
     * Stores new manager
     * @param manager to be created
     */
    Long create(Manager manager);

    /**
     * Updates existing Manager
     * @param manager to be updated
     */
    Manager update(Manager manager);

    /**
     *
     * @param musicianID
     * @param bandID
     */
    void setOffer(Long musicianID, Long bandID);

    /**
     * Removes Manager
     * @param manager to be deleted
     */
    void remove(Manager manager);

    /**
     * Find Manager by its userName
     * @param userName of the Manager
     * @return Manager or {@code null}, if there is no Manager with given userName
     */
    Manager findByUserName(String userName);

    /**
     * Find Manager by its name
     * @param name of the Manager
     * @return Manager or {@code null}, if there is no Manager with given name
     */
    List<Manager> findByName(String name);

    /**
     *
     * @param username
     * @param password
     * @return
     */
    Manager login(String username, String password);
}
