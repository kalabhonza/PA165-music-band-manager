package service;

import cz.fi.muni.pa165.entities.Concert;
import cz.fi.muni.pa165.entities.Manager;
import cz.fi.muni.pa165.entities.Tour;
import cz.fi.muni.pa165.persistance.interfaces.TourDAO;
import cz.fi.muni.pa165.service.TourService;
import cz.fi.muni.pa165.service.TourServiceImpl;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

public class TourServiceTest {

    private TourService tourService;
    private Tour tour;
    private List<Tour> allTours;
    private List<Concert> allConcert;
    private Concert concert;
    private Concert concert2;

    @Mock
    private TourDAO tourDAO;

    @BeforeMethod
    private void init(){
        MockitoAnnotations.openMocks(this);
        tourService = new TourServiceImpl(tourDAO);

        this.tour = new Tour();
        this.tour.setName("ACDC tour 2021");
        this.tour.setId(1L);

        this.concert = new Concert();
        this.concert.setName("ACDC Prague");
        this.concert.setId(2L);
        this.concert.setDate(LocalDate.of(2022, 2, 2));

        this.concert2 = new Concert();
        this.concert2.setName("ACDC Berlin");
        this.concert2.setId(3L);
        this.concert2.setDate(LocalDate.of(2022, 2, 5));

        this.allConcert = new ArrayList<>();
        this.allConcert.add(this.concert);
        this.allConcert.add(this.concert2);

        this.tour.setConcerts(new HashSet<>(this.allConcert));

        this.allTours = new ArrayList<>();
        this.allTours.add(tour);
    }

    @Test
    public void createTest(){
        Long id = tourService.create(tour);
        then(tourDAO).should().create(tour);
    }

    @Test
    public void updateTest(){
        given(tourDAO.update(tour)).willReturn(tour);
        Tour tour1 = tourService.update(tour);
        assertEquals(tour1, tour);
        then(tourDAO).should().update(tour);
    }

    @Test
    public void findById() {
        given(tourDAO.findById(tour.getId())).willReturn(tour);
        Tour tour1 = tourService.findById(tour.getId());
        Assert.assertEquals(tour1, tour);
        then(tourDAO).should().findById(tour.getId());
    }

    @Test
    public void findAllTest() {
        given(tourDAO.findAllTours()).willReturn(allTours);
        List<Tour> tour1 = tourService.findAllTours();
        Assert.assertEquals(tour1, allTours);
        then(tourDAO).should().findAllTours();
    }

    @Test
    public void findTourByNameTest() {
        given(tourDAO.findByName(tour.getName())).willReturn(allTours);
        List<Tour> tour1 = tourService.findByName(tour.getName());
        Assert.assertEquals(tour1, allTours);
        then(tourDAO).should().findByName(tour.getName());
    }

    @Test
    public void deleteTest() {
        tourService.remove(tour);
        then(tourDAO).should().remove(tour);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void findByNonExistingIdTest() {
        given(tourDAO.findById(anyLong())).willReturn(null);
        tourService.findById(10L);
    }

    @Test()
    public void findByNonExistingName() {
        given(tourDAO.findByName(anyString())).willReturn(Collections.emptyList());
        tourService.findByName("Peter");
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void updateNonExistingTourTest() {
        Tour tour1 = new Tour(99L, "Nirvana", null);
        given(tourDAO.update(tour1)).willReturn(null);
        tourService.update(tour1);
    }
}
