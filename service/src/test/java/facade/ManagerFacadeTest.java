package facade;

import cz.fi.muni.pa165.api.dto.ManagerCreateDTO;
import cz.fi.muni.pa165.api.dto.ManagerDTO;
import cz.fi.muni.pa165.api.dto.ManagerUpdateDTO;
import cz.fi.muni.pa165.api.dto.band.BandDTO;
import cz.fi.muni.pa165.api.facade.ManagerFacade;
import cz.fi.muni.pa165.entities.Band;
import cz.fi.muni.pa165.entities.Manager;
import cz.fi.muni.pa165.service.ManagerService;
import cz.fi.muni.pa165.service.facade.ManagerFacadeImpl;
import cz.fi.muni.pa165.service.mapping.modelmapper.BeanMapper;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static cz.fi.muni.pa165.enums.Style.ALTERNATIVE;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.testng.Assert.assertEquals;


/**
 * Tests for Manager Facade
 *
 * @author Igor Ignác
 */
public class ManagerFacadeTest {

    private ManagerFacade managerFacade;
    @Mock
    private ManagerService managerService;
    @Mock
    private BeanMapper beanMapper;

    private Manager manager;
    private List<Manager> managers;
    private ManagerDTO managerDTO;
    private List<ManagerDTO> managerDTOS;
    private ManagerCreateDTO managerCreateDTO;
    private ManagerUpdateDTO managerUpdateDTO;

    @BeforeMethod
    public void init() {
        MockitoAnnotations.openMocks(this);
        managerFacade = new ManagerFacadeImpl(managerService, beanMapper);

        Band band = new Band(2L, "Herders", ALTERNATIVE);
        manager = new Manager(1L, "John Doe", "johan123", "passwd", band);

        managerDTO = new ManagerDTO();
        managerDTO.setId(manager.getId());
        managerDTO.setName(manager.getName());
        managerDTO.setUserName(manager.getUserName());
        managerDTO.setPassword(manager.getPassword());
        managerDTO.setBand(beanMapper.mapTo(manager.getBand(), BandDTO.class));

        managerCreateDTO = new ManagerCreateDTO();
        managerCreateDTO.setId(manager.getId());
        managerCreateDTO.setName(manager.getName());
        managerCreateDTO.setUserName(manager.getUserName());
        managerCreateDTO.setPassword(manager.getPassword());
        managerCreateDTO.setBand(beanMapper.mapTo(manager.getBand(), BandDTO.class));

        managerUpdateDTO = new ManagerUpdateDTO();
        managerUpdateDTO.setId(manager.getId());
        managerUpdateDTO.setName(manager.getName());
        managerUpdateDTO.setUserName(manager.getUserName());
        managerUpdateDTO.setPassword(manager.getPassword());
        managerUpdateDTO.setBand(beanMapper.mapTo(manager.getBand(), BandDTO.class));
    }

    @Test
    public void createManager() {
        given(beanMapper.mapTo(managerCreateDTO, Manager.class)).willReturn(manager);
        managerFacade.create(managerCreateDTO);
        then(managerService).should().create(manager);
    }

    @Test
    public void findById() {
        given(managerService.findById(manager.getId())).willReturn(manager);
        given(beanMapper.mapTo(manager, ManagerDTO.class)).willReturn(managerDTO);
        ManagerDTO res = managerFacade.findById(manager.getId());
        assertEquals(managerDTO, res);
        then(managerService).should().findById(manager.getId());
    }

    @Test
    public void findByName() {
        given(managerService.findByName(manager.getName())).willReturn(managers);
        given(beanMapper.mapTo(managers, ManagerDTO.class)).willReturn(managerDTOS);
        List<ManagerDTO> res = managerFacade.findByName(manager.getName());
        assertEquals(managerDTOS, res);
        then(managerService).should().findByName(manager.getName());
    }

    @Test
    public void findByUserName() {
        given(managerService.findByUserName(manager.getName())).willReturn(manager);
        given(beanMapper.mapTo(manager, ManagerDTO.class)).willReturn(managerDTO);
        ManagerDTO res = managerFacade.findByUserName(manager.getName());
        assertEquals(managerDTO, res);
        then(managerService).should().findByUserName(manager.getName());
    }

    @Test
    public void findAll() {
        given(managerService.findAll()).willReturn(managers);
        given(beanMapper.mapTo(managers, ManagerDTO.class)).willReturn(managerDTOS);
        List<ManagerDTO> res = managerFacade.findAll();
        assertEquals(managerDTOS, res);
        then(managerService).should().findAll();
    }

    @Test
    public void updateManager() {
        given(beanMapper.mapTo(managerUpdateDTO, Manager.class)).willReturn(manager);
        managerFacade.update(managerUpdateDTO);
        then(managerService).should().update(manager);
    }

    @Test
    public void removeManager() {
        managerFacade.remove(managerDTO);
        then(managerService).should().remove(manager);
    }

}
