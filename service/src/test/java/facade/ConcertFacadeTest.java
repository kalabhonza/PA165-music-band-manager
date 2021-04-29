package facade;


import cz.fi.muni.pa165.api.dto.concert.ConcertCreateDTO;
import cz.fi.muni.pa165.api.dto.concert.ConcertDTO;
import cz.fi.muni.pa165.api.dto.concert.ConcertUpdateDTO;
import cz.fi.muni.pa165.api.facade.BandFacade;
import cz.fi.muni.pa165.api.facade.ConcertFacade;
import cz.fi.muni.pa165.entities.Band;
import cz.fi.muni.pa165.entities.Concert;
import cz.fi.muni.pa165.enums.Style;
import cz.fi.muni.pa165.service.BandService;
import cz.fi.muni.pa165.service.ConcertService;
import cz.fi.muni.pa165.service.facade.BandFacadeImpl;
import cz.fi.muni.pa165.service.facade.ConcertFacadeImpl;

import cz.fi.muni.pa165.service.mapping.mapstruct.BandMapperImpl;
import cz.fi.muni.pa165.service.mapping.mapstruct.ConcertMapperImpl;



import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import org.testng.annotations.BeforeMethod;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;


public class ConcertFacadeTest {
    private ConcertFacade concertFacade;

    @Mock
    private ConcertService concertService;

    @Mock
    private ConcertMapperImpl concertMapper;


    private ConcertCreateDTO concertCreateDTO;
    private ConcertUpdateDTO concertUpdateDTO;
    private ConcertDTO concertDTO;
    private Concert concert;
    private Concert testConcert1;
    private List<Concert> concerts;
    private List<ConcertDTO> concertDTOS;

    @BeforeMethod
    public void init(){
        MockitoAnnotations.openMocks(this);
        concertFacade = new ConcertFacadeImpl(concertService, concertMapper);

        concert = new Concert(1L, "Brno", LocalDate.of(2020,10,10));


        concertDTO = new ConcertDTO();
        concertDTO.setId(concert.getId());
        concertDTO.setName(concert.getName());
        concertDTO.setDate(concert.getDate());

        concertCreateDTO = new ConcertCreateDTO();
        concertCreateDTO.setName(concert.getName());
        concertCreateDTO.setDate(concert.getDate());
        System.out.println(concertCreateDTO.getName());

        concertUpdateDTO = new ConcertUpdateDTO();
        concertUpdateDTO.setId(concert.getId());
        concertUpdateDTO.setName(concert.getName());
        concertUpdateDTO.setDate(concert.getDate());



    }

    @Test
    public void createConcertTest(){
//        Assert.assertNotEquals(concertCreateDTO,null);
//        String name = concertCreateDTO.getName();
//        Assert.assertNotEquals(name,null);
//
//        given(concertService.create(concert)).willReturn(concert.getId());
        given(concertMapper.mapToEntity(concertCreateDTO)).willReturn(concert);
        concertFacade.create(concertCreateDTO);
        then(concertService).should().create(concert);
    }

    @Test
    public void updateConcertTests() {

        given(concertMapper.mapToEntity(concertUpdateDTO)).willReturn(concert);
        concertFacade.update(concertUpdateDTO);
        then(concertService).should().update(concert);
    }
}
//
//    @Mock
//    private ConcertService concertService;
//
//    @Mock
//    private BeanMapper beanMapper;
//
//    @Inject
//    @InjectMocks
//    private ConcertFacadeImpl concertFacade;
//
//    @Rule
//    public MockitoRule mockitoRule = MockitoJUnit.rule();
//
//    private ConcertCreateDTO concertCreateDTO;
//
//    private ConcertDTO concertDTO;
//
//    private Concert concert;
//
//    private Concert testConcert1;
//
//    @Before
//    public void setUp(){
//        this.concert = new Concert(1L, "Vizovice", LocalDate.of( 2022, 1, 28));
//        this.testConcert1 = new Concert(2L, "Praha", LocalDate.of( 2023, 2, 18));
//
//        this.concertCreateDTO = new ConcertCreateDTO();
//        this.concertCreateDTO.setName("Brno");
//        this.concertCreateDTO.setDate(LocalDate.of(2020,10,10));
//
//        when(concertService.update(any(Concert.class))).thenReturn(testConcert1);
//
//    }
//
//    @Test
//    public void createConcertTest(){
//        concertFacade.create(concertCreateDTO);
//        verify(concertService, times(1)).create(any(Concert.class));
//    }
//
//    @Test
//    public void updateBandTest(){
//        concertFacade.update(concertDTO);
//        verify(concertService, times(1)).update(any(Concert.class));
//    }
//
//
//
//}
