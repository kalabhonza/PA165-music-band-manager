package service;

import cz.fi.muni.pa165.entities.Band;
import cz.fi.muni.pa165.entities.Manager;
import cz.fi.muni.pa165.enums.Style;
import cz.fi.muni.pa165.persistence.interfaces.ManagerDAO;
import cz.fi.muni.pa165.service.service.manager.ManagerService;
import cz.fi.muni.pa165.service.service.manager.ManagerServiceImpl;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.testng.Assert.*;

/**
 * Tests for Manager Service
 *
 * @author Igor Ignác
 */
public class ManagerServiceTest {

    private ManagerService managerService;
    private Manager manager;
    private List<Manager> managerList = new ArrayList<>();
    private Band band;

    @Mock
    private ManagerDAO managerDAO;

    @BeforeMethod
    private void init() {
        MockitoAnnotations.initMocks(this);
        managerService = new ManagerServiceImpl(managerDAO);

        band = new Band(2L, "Labeled", Style.ALTERNATIVE);

        manager = new Manager();
        manager.setId(1L);
        manager.setName("John Doe");
        manager.setUserName("johnny10");
        manager.setPassword("passwd");
        manager.setBand(band);

        managerList.add(0, manager);

    }

    @Test
    public void create() {
        Long id = managerService.create(manager);
        then(managerDAO).should().create(manager);
    }

    @Test
    public void findById() {
        given(managerDAO.findById(manager.getId())).willReturn(manager);
        Manager manager1 = managerService.findById(manager.getId());
        assertEquals(manager1, manager);
        then(managerDAO).should().findById(manager.getId());
    }

    @Test
    public void findByName() {
        given(managerDAO.findByName(manager.getName())).willReturn(managerList);
        List<Manager> manager1 = managerService.findByName(manager.getName());
        assertEquals(manager1, managerList);
        then(managerDAO).should().findByName(manager.getName());
    }

    @Test
    public void findAll() {
        given(managerDAO.findAll()).willReturn(managerList);
        List<Manager> manager1 = managerService.findAll();
        assertEquals(manager1, managerList);
        then(managerDAO).should().findAll();
    }

    @Test
    public void update() {
        given(managerDAO.update(manager)).willReturn(manager);
        Manager manager1 = managerService.update(manager);
        assertEquals(manager1, manager);
        then(managerDAO).should().update(manager);
    }

    @Test
    public void delete() {
        managerService.remove(manager);
        then(managerDAO).should().remove(manager);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void findByNonExistingId() {
        given(managerDAO.findById(anyLong())).willReturn(null);
        managerService.findById(10L);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void findByNonExistingUserName() {
        given(managerDAO.findByUserName(anyString())).willReturn(null);
        managerService.findByUserName("Peeeete21");
    }

    @Test()
    public void findByNonExistingName() {
        given(managerDAO.findByName(anyString())).willReturn(Collections.emptyList());
        managerService.findByName("Peter");
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void updateNonExisting() {
        Manager manger1 = new Manager(20L, "Peter", "peete21", "passwd", band);
        given(managerDAO.update(manger1)).willReturn(null);
        managerService.update(manger1);
    }
}
