package cz.fi.muni.pa165.persistance.interfaces;

import cz.fi.muni.pa165.entities.Manager;
import java.util.List;

/**
 * DAO for Manager entity
 *
 * @author Jan Kaláb
 */
public interface ManagerDAO {
    /**
     * Find Manager by its id
     *
     * @param id
     * @return Manager or {@code null}, if there is no Manager with requested id
     */
    Manager findById(long id);

    /**
     * Find all Managers
     * @return List of all Managers
     */
    List<Manager> findAll();

    /**
     * Stores new manager
     * @param manager to be created
     */
    void create(Manager manager);

    /**
     * Updates existing Manager
     * @param manager to be updated
     */
    void update(Manager manager);

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

}
