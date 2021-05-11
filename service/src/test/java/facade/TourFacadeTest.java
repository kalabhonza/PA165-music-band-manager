package facade;

import cz.fi.muni.pa165.api.dto.concert.ConcertDTO;
import cz.fi.muni.pa165.api.dto.tour.TourCreateDTO;
import cz.fi.muni.pa165.api.dto.tour.TourDTO;
import cz.fi.muni.pa165.api.dto.tour.TourUpdateDTO;
import cz.fi.muni.pa165.api.facade.TourFacade;
import cz.fi.muni.pa165.entities.Concert;
import cz.fi.muni.pa165.entities.Tour;
import cz.fi.muni.pa165.service.service.tour.TourService;
import cz.fi.muni.pa165.service.facade.TourFacadeImpl;
import cz.fi.muni.pa165.service.mapping.mapstruct.ConcertMapperImpl;
import cz.fi.muni.pa165.service.mapping.mapstruct.TourMapperImpl;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

public class TourFacadeTest {
    private TourFacade tourFacade;
    @Mock
    private TourService tourService;
    @Mock
    private TourMapperImpl tourMapper;
    @Mock
    private ConcertMapperImpl concertMapper;

    private TourCreateDTO tourCreateDTO;

    private TourDTO tourDTO;

    private List<TourDTO> allToursDTO;

    private ConcertDTO concertDTO;

    private Tour tour;

    private List<Tour> allTours;

    private Concert concert;

    private Concert concert2;

    private ConcertDTO concertDTO2;

    private List<Concert> allConcert;

    private List<ConcertDTO> allConcertDTO;

    private TourUpdateDTO tourUpdateDTO;

    @BeforeMethod
    public void init(){

        MockitoAnnotations.initMocks(this);
        tourFacade = new TourFacadeImpl(tourService, tourMapper);

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

        this.concertDTO = new ConcertDTO();
        this.concertDTO.setName(concert.getName());
        this.concertDTO.setId(concert.getId());
        this.concertDTO.setDate(concert.getDate());

        this.concertDTO2 = new ConcertDTO();
        this.concertDTO2.setName(concert2.getName());
        this.concertDTO2.setId(concert2.getId());
        this.concertDTO2.setDate(concert2.getDate());

        this.allConcertDTO = new ArrayList<>();
        this.allConcertDTO.add(concertDTO);
        this.allConcertDTO.add(concertDTO2);

        this.tourDTO = new TourDTO();
        this.tourDTO.setId(tour.getId());
        this.tourDTO.setName(tour.getName());
        this.tourDTO.setConcerts(new HashSet<>(this.allConcertDTO));

        this.allToursDTO = new ArrayList<>();
        this.allToursDTO.add(tourDTO);

        this.tourCreateDTO = new TourCreateDTO();
        this.tourCreateDTO.setName(tour.getName());

        this.tourUpdateDTO = new TourUpdateDTO();
        this.tourUpdateDTO.setId(this.tour.getId());
        this.tourUpdateDTO.setName(tour.getName());
        this.tourUpdateDTO.setConcerts(new HashSet<>(allConcertDTO));
    }

    @Test
    public void createTourTest(){
        given(tourMapper.mapToEntity(tourCreateDTO)).willReturn(tour);
        tourFacade.create(tourCreateDTO);
        then(tourService).should().create(tour);
    }

    @Test
    public void updateTourTest(){
        given(tourMapper.mapToEntity(tourUpdateDTO)).willReturn(tour);
        tourFacade.update(tourUpdateDTO);
        then(tourService).should().update(tour);
    }

    @Test
    public void findTourByIdTest(){
        given(tourService.findById(tour.getId())).willReturn(tour);
        given(tourMapper.mapToTourDTO(tour)).willReturn(tourDTO);
        TourDTO result = tourFacade.findById(tour.getId());
        assertEquals(tourDTO, result);
        then(tourService).should().findById(tour.getId());
    }

    @Test
    public void findTourByNameTest(){
        given(tourService.findByName(tour.getName())).willReturn(allTours);
        given(tourMapper.mapToListDTO(allTours)).willReturn(allToursDTO);
        List<TourDTO> result = tourFacade.findByName(tour.getName());
        assertEquals(allToursDTO, result);
        then(tourService).should().findByName(tour.getName());
    }

    @Test
    public void findAllToursTest(){
        given(tourService.findAllTours()).willReturn(allTours);
        given(tourMapper.mapToListDTO(allTours)).willReturn(allToursDTO);
        List<TourDTO> result = tourFacade.findAllTours();
        assertEquals(allToursDTO, result);
        then(tourService).should().findAllTours();
    }

    @Test
    public void removeTourTest(){
        tourFacade.remove(tourDTO);
        then(tourService).should().remove(tourMapper.mapToEntity(tourDTO));
    }

}
