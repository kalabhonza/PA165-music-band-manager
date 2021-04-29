//package facade;
//
//import com.github.dozermapper.core.inject.Inject;
//import cz.fi.muni.pa165.api.dto.*;
//import cz.fi.muni.pa165.entities.Band;
//import cz.fi.muni.pa165.entities.Concert;
//import cz.fi.muni.pa165.service.BandService;
//import cz.fi.muni.pa165.service.ConcertService;
//import cz.fi.muni.pa165.service.facade.BandFacadeImpl;
//import cz.fi.muni.pa165.service.facade.ConcertFacadeImpl;
//
//import cz.fi.muni.pa165.service.mapping.modelmapper.BeanMapper;
//import cz.fi.muni.pa165.service.mapping.modelmapper.BeanMapperImpl;
//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnit;
//import org.mockito.junit.MockitoRule;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.TestExecutionListeners;
//import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
//
//import java.time.LocalDate;
//
//import static org.mockito.Matchers.any;
//import static org.mockito.Mockito.*;
//
//@ContextConfiguration(classes = BeanMapperImpl.class)
//@TestExecutionListeners(TransactionalTestExecutionListener.class)
//public class ConcertFacadeTest {
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
