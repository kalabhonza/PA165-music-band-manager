package service;

import cz.fi.muni.pa165.entities.Concert;
import cz.fi.muni.pa165.persistence.interfaces.ConcertDAO;
import cz.fi.muni.pa165.service.service.concert.ConcertService;
import cz.fi.muni.pa165.service.service.concert.ConcertServiceImpl;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.testng.Assert.assertEquals;

public class ConcertServiceTest {
    private ConcertService concertService;
    private Concert concert;
    private List<Concert> concertList = new ArrayList<>();

    @Mock
    private ConcertDAO concertDAO;

    @BeforeMethod
    private void init(){
        MockitoAnnotations.initMocks(this);
        concertService = new ConcertServiceImpl(concertDAO);

        concert = new Concert();
        concert.setId(1L);
        concert.setName("Wacken");
        concert.setDate(LocalDate.of(2023,6,6));
        concertList.add(0,concert);
    }

    @Test
    public void create() {
        Long id = concertService.create(concert);
        then(concertDAO).should().create(concert);
    }

    @Test
    public void findById() {
        given(concertDAO.findById(concert.getId())).willReturn(concert);
        Concert concert1 = concertService.findById(concert.getId());
        assertEquals(concert1, concert);
        then(concertDAO).should().findById(concert.getId());
    }

    @Test
    public void findByName() {
        given(concertDAO.findByName(concert.getName())).willReturn(concertList);
        List<Concert> concertList1 = concertService.findByName(concert.getName());
        assertEquals(concertList1, concertList);
        then(concertDAO).should().findByName(concert.getName());
    }

    @Test
    public void findAll() {
        given(concertDAO.findAll()).willReturn(concertList);
        List<Concert> concertList1 = concertService.findAll();
        assertEquals(concertList1, concertList);
        then(concertDAO).should().findAll();
    }

    @Test
    public void update() {
        given(concertDAO.update(concert)).willReturn(concert);
        Concert concert1 = concertService.update(concert);
        assertEquals(concert1, concert);
        then(concertDAO).should().update(concert);
    }

    @Test
    public void delete() {
        concertService.remove(concert);
        then(concertDAO).should().remove(concert);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void findByNonExistingId() {
        given(concertDAO.findById(anyLong())).willReturn(null);
        concertService.findById(10L);
    }

    @Test
    public void findByNonExistingUserName() {
        given(concertDAO.findByName(anyString())).willReturn(null);
        concertService.findByName("RANDOM NAME");
    }

    @Test()
    public void findByNonExistingName() {
        given(concertDAO.findByName(anyString())).willReturn(Collections.emptyList());
        concertService.findByName("MY CONCERT");
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void updateNonExisting() {
        Concert concert1 = new Concert(20L,"Praha",LocalDate.of(2020,1,1));
        given(concertDAO.update(concert1)).willReturn(null);
        concertService.update(concert1);
    }
}
