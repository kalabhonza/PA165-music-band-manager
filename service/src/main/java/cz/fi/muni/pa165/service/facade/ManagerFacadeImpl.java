package cz.fi.muni.pa165.service.facade;

import cz.fi.muni.pa165.api.dto.ManagerDTO;
import cz.fi.muni.pa165.api.facade.ManagerFacade;
import cz.fi.muni.pa165.entities.Manager;
import cz.fi.muni.pa165.service.ManagerService;
import cz.fi.muni.pa165.service.mapping.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * @author Jan Kaláb
 */
public class ManagerFacadeImpl implements ManagerFacade {

    private ManagerService managerService;
    private BeanMapper beanMapper;

    @Autowired
    public ManagerFacadeImpl(ManagerService managerService, BeanMapper beanMapper) {
        this.managerService = managerService;
        this.beanMapper = beanMapper;
    }

    @Override
    public ManagerDTO findById(long id) {
        Manager manager = managerService.findById(id);
        return beanMapper.mapTo(manager, ManagerDTO.class);
    }

    @Override
    public List<ManagerDTO> findAll() {
        List<Manager> manager = managerService.findAll();
        return beanMapper.mapTo(manager, ManagerDTO.class);
    }

    @Override
    public Long create(ManagerDTO manager) {
        Manager createdManager = beanMapper.mapTo(manager, Manager.class);
        this.managerService.create(createdManager);
        return createdManager.getId();
    }

    @Override
    public ManagerDTO update(ManagerDTO manager) {
        Manager updatedManager = beanMapper.mapTo(manager, Manager.class);
        updatedManager = managerService.update(updatedManager);
        return beanMapper.mapTo(updatedManager, ManagerDTO.class);
    }

    @Override
    public void remove(ManagerDTO manager) {
        Manager deletedManager = beanMapper.mapTo(manager, Manager.class);
        managerService.remove(deletedManager);
    }

    @Override
    public ManagerDTO findByUserName(String userName) {
        Manager manager = managerService.findByUserName(userName);
        return beanMapper.mapTo(manager, ManagerDTO.class);
    }

    @Override
    public List<ManagerDTO> findByName(String name) {
        List<Manager> manager = managerService.findByName(name);
        return beanMapper.mapTo(manager, ManagerDTO.class);
    }
}
