package facade;

import com.github.dozermapper.core.inject.Inject;
import cz.fi.muni.pa165.api.dto.*;
import cz.fi.muni.pa165.entities.Band;
import cz.fi.muni.pa165.entities.Concert;
import cz.fi.muni.pa165.entities.Tour;
import cz.fi.muni.pa165.persistance.interfaces.ConcertDAO;
import cz.fi.muni.pa165.service.MusicianService;
import cz.fi.muni.pa165.service.TourService;
import cz.fi.muni.pa165.service.facade.MusicianFacadeImpl;
import cz.fi.muni.pa165.service.facade.TourFacadeImpl;
import cz.fi.muni.pa165.service.mapping.BeanMapper;
import cz.fi.muni.pa165.service.mapping.BeanMapperImpl;
import org.junit.Before;
import org.junit.Rule;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ContextConfiguration(classes = BeanMapperImpl.class)
public class TourFacadeTest {
    @Mock
    private TourService tourService;

    @Mock
    private BeanMapper beanMapper;

    @Inject
    @InjectMocks
    private TourFacadeImpl tourFacade;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    private TourCreateDTO tourCreateDTO;

    private TourDTO tourDTO;

    private ConcertDTO concertDTO;

    private Tour tour;

    private Concert concert;

    private Concert concert2;

    private ConcertDTO concertDTO2;

    private List<Concert> allConcert;

    private List<ConcertDTO> allConcertDTO;

    @Before
    public void setUp(){
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

        this.concertDTO = new ConcertDTO();
        this.concertDTO.setName("ACDC Prague");
        this.concertDTO.setId(2L);
        this.concertDTO.setDate(LocalDate.of(2022, 2, 2));

        this.concertDTO2 = new ConcertDTO();
        this.concertDTO2.setName("ACDC Berlin");
        this.concertDTO2.setId(3L);
        this.concertDTO2.setDate(LocalDate.of(2022, 2, 5));

        this.allConcertDTO = new ArrayList<>();
        this.allConcertDTO.add(concertDTO);
        this.allConcertDTO.add(concertDTO2);

        this.tourDTO = new TourDTO();
        this.tourDTO.setId(1L);
        this.tourDTO.setName("ACDC tour 2021");
        this.tourDTO.setConcerts(new HashSet<>(this.allConcertDTO));

        

    }
}
