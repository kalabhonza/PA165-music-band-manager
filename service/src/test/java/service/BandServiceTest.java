package service;

import cz.fi.muni.pa165.entities.Band;
import cz.fi.muni.pa165.entities.Manager;
import cz.fi.muni.pa165.enums.Style;
import cz.fi.muni.pa165.persistence.interfaces.BandDAO;
import cz.fi.muni.pa165.service.service.band.BandService;
import cz.fi.muni.pa165.service.service.band.BandServiceImpl;
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
import static org.testng.Assert.assertEquals;

public class BandServiceTest {

    private BandService bandService;
    private Band band;
    private List<Band> bandList = new ArrayList<>();
    private Manager manager;

    @Mock
    private BandDAO bandDAO;

    @BeforeMethod
    private void init(){
        MockitoAnnotations.initMocks(this);
        bandService = new BandServiceImpl(bandDAO);



        band = new Band();
        band.setId(1L);
        band.setName("1D");
        band.setStyle(Style.POP);
        manager = new Manager(1L,"Frank", "Frank08","xdxdxdxd", band);
        band.setManager(manager);
        bandList.add(0, band);
    }

    @Test
    public void create(){
        Long id = bandService.createBand(band);
        then(bandDAO).should().create(band);
    }

    @Test
    public void findById() {
        given(bandDAO.findBandById(band.getId())).willReturn(band);
        Band band1 = bandService.findBandById(band.getId());
        assertEquals(band1, band);
        then(bandDAO).should().findBandById(band.getId());
    }

    @Test
    public void findByName() {
        given(bandDAO.findBandByName(band.getName())).willReturn(bandList);
        List<Band> bandList1 = bandService.findBandByName(band.getName());
        assertEquals(bandList1, bandList);
        then(bandDAO).should().findBandByName(band.getName());
    }

    @Test
    public void findAll() {
        given(bandDAO.findAll()).willReturn(bandList);
        List<Band> bandList1 = bandService.findAllBands();
        assertEquals(bandList1, bandList);
        then(bandDAO).should().findAll();
    }

    @Test
    public void update() {
        given(bandDAO.update(band)).willReturn(band);
        Band band1 = bandService.updateBand(band);
        assertEquals(band1, band);
        then(bandDAO).should().update(band);
    }

    @Test
    public void delete() {
        bandService.deleteBand(band);
        then(bandDAO).should().deleteBand(band);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void findByNonExistingId() {
        given(bandDAO.findBandById(anyLong())).willReturn(null);
        bandService.findBandById(158L);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void findByNonExistingUserName() {
        given(bandDAO.findBandByName(anyString())).willReturn(null);
        bandService.findBandByName("Random name");
    }

    @Test()
    public void findByNonExistingName() {
        given(bandDAO.findBandByName(anyString())).willReturn(Collections.emptyList());
        bandService.findBandByName("Random band");
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void updateNonExisting() {
        Band band1 = new Band(3L,"Pink Floyd", Style.ROCK);
        given(bandDAO.update(band1)).willReturn(null);
        bandService.updateBand(band1);
    }



}
