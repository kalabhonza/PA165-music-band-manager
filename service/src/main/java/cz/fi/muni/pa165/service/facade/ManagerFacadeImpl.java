package cz.fi.muni.pa165.service.facade;

import cz.fi.muni.pa165.api.dto.band.BandDTO;
import cz.fi.muni.pa165.api.dto.manager.ManagerCreateDTO;
import cz.fi.muni.pa165.api.dto.manager.ManagerDTO;
import cz.fi.muni.pa165.api.dto.manager.ManagerUpdateDTO;
import cz.fi.muni.pa165.api.facade.ManagerFacade;
import cz.fi.muni.pa165.entities.Band;
import cz.fi.muni.pa165.entities.Manager;
import cz.fi.muni.pa165.service.mapping.mapstruct.BandMapperImpl;
import cz.fi.muni.pa165.service.service.manager.ManagerService;
import cz.fi.muni.pa165.service.mapping.mapstruct.ManagerMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Jan Kaláb
 */
@Service
@Transactional
public class ManagerFacadeImpl implements ManagerFacade {

    private ManagerService managerService;
    private ManagerMapperImpl managerMapper;
    private BandMapperImpl bandMapper;

    @Autowired
    public ManagerFacadeImpl(ManagerService managerService, ManagerMapperImpl managerMapper, BandMapperImpl bandMapper) {
        this.managerService = managerService;
        this.managerMapper = managerMapper;
        this.bandMapper = bandMapper;
    }

    @Override
    public ManagerDTO findById(long id) {
        Manager manager = managerService.findById(id);
        return managerMapper.mapToManagerDTO(manager);
    }

    @Override
    public List<ManagerDTO> findAll() {
        List<Manager> manager = managerService.findAll();
        return managerMapper.mapToListDTO(manager);
    }

    @Override
    public BandDTO getManagerBand(Long bandId) {
        Band band = managerService.getManagerBand(bandId);
        return bandMapper.mapToBandDTO(band);
    }

    @Override
    public Long create(ManagerCreateDTO manager) {
        Manager createdManager = managerMapper.mapToEntity(manager);
        return this.managerService.create(createdManager);
    }

    @Override
    public ManagerDTO update(ManagerUpdateDTO manager) {
        Manager updatedManager = managerMapper.mapToEntity(manager);
        updatedManager = managerService.update(updatedManager);
        return managerMapper.mapToManagerDTO(updatedManager);
    }

    @Override
    public void remove(ManagerDTO manager) {
        Manager deletedManager = managerMapper.mapToEntity(manager);
        managerService.remove(deletedManager);
    }

    @Override
    public ManagerDTO findByUserName(String userName) {
        Manager manager = managerService.findByUserName(userName);
        return managerMapper.mapToManagerDTO(manager);
    }

    @Override
    public List<ManagerDTO> findByName(String name) {
        List<Manager> manager = managerService.findByName(name);
        return managerMapper.mapToListDTO(manager);
    }

    @Override
    public ManagerDTO login(String username, String password) {
        Manager manager = managerService.login(username, password);
        return managerMapper.mapToManagerDTO(manager);
    }
}
