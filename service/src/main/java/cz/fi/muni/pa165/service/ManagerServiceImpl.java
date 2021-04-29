package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.entities.Manager;
import cz.fi.muni.pa165.persistance.interfaces.ManagerDAO;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService{

    private ManagerDAO managerDAO;

    public ManagerServiceImpl(ManagerDAO managerDAO){
        this.managerDAO = managerDAO;
    }

    @Override
    public Manager findById(long id) {
        Manager manager = managerDAO.findById(id);
        if (manager == null) {
            throw new DataAccessException("Manager with id: " + id + "was not found") {};
        }
        return manager;
    }

    @Override
    public List<Manager> findAll() {
        return managerDAO.findAll();
    }

    @Override
    public Long create(Manager manager) {
        return managerDAO.create(manager);
    }

    @Override
    public Manager update(Manager manager) {
        Manager updatedManager = managerDAO.update(manager);
        if (updatedManager != manager) {
            throw new DataAccessException("Manager with id: " + manager.getId() + "was not updated") {};
        }
        return updatedManager;
    }

    @Override
    public void remove(Manager manager) {
        managerDAO.remove(manager);
        if (managerDAO.findById(manager.getId()) != null) {
            throw new DataAccessException("Manager with id: " + manager.getId() + "was not deleted") {};
        }
    }

    @Override
    public Manager findByUserName(String userName) {
        Manager manager = managerDAO.findByUserName(userName);
        if (manager == null) {
            throw new DataAccessException("Manager with userName: " + userName + "was not found") {};
        }
        return manager;
    }

    @Override
    public List<Manager> findByName(String name) {
        List<Manager> manager = managerDAO.findByName(name);
        if (manager == null || manager.isEmpty()) {
            throw new DataAccessException("Manager with name: " + name + "was not found") {};
        }
        return manager;
    }
}
