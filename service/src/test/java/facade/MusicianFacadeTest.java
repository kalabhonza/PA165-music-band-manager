//package facade;
//
//import com.github.dozermapper.core.inject.Inject;
//import cz.fi.muni.pa165.api.dto.ManagerDTO;
//import cz.fi.muni.pa165.api.dto.MusicianCreateDTO;
//import cz.fi.muni.pa165.api.dto.MusicianDTO;
//import cz.fi.muni.pa165.api.dto.band.BandDTO;
//import cz.fi.muni.pa165.entities.Band;
//import cz.fi.muni.pa165.entities.Manager;
//import cz.fi.muni.pa165.entities.Musician;
//import cz.fi.muni.pa165.service.MusicianService;
//import cz.fi.muni.pa165.service.facade.MusicianFacadeImpl;
//
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
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//
//import static org.mockito.Matchers.any;
//import static org.mockito.Mockito.*;
//
//@ContextConfiguration(classes = BeanMapperImpl.class)
//public class MusicianFacadeTest {
//    @Mock
//    private MusicianService musicianService;
//
//    @Mock
//    private BeanMapper beanMapper;
//
//    @Inject
//    @InjectMocks
//    private MusicianFacadeImpl musicianFacade;
//
//    @Rule
//    public MockitoRule mockitoRule = MockitoJUnit.rule();
//
//    private MusicianCreateDTO musicianCreateDTO;
//
//    private MusicianDTO musicianDTO;
//
//    private BandDTO bandDTO;
//
//    private Band band;
//
//    private Manager manager;
//
//    private ManagerDTO managerDTO;
//
//    private Musician musician;
//
//    private Musician testMusician1;
//
//    private List<Musician> allMusicians;
//
//    @Before
//    public void setUp(){
//        this.musician = new Musician(1L, "Bon Jovi", "bjovi", "prague");
//
//        this.testMusician1 = new Musician(2L, "John Lock", "jlock", "abcd");
//
//        this.allMusicians = new ArrayList<>();
//        this.allMusicians.add(this.musician);
//        this.allMusicians.add(this.testMusician1);
//
//        this.musicianCreateDTO = new MusicianCreateDTO();
//        this.musicianCreateDTO.setName("Honza Kaláb");
//        this.musicianCreateDTO.setUsername("honzaa");
//        this.musicianCreateDTO.setPassword("12345");
//
//        this.musicianDTO = new MusicianDTO();
//        this.musicianDTO.setId(2L);
//        this.musicianDTO.setName("John Lock");
//        this.musicianDTO.setUsername("jlock");
//        this.musicianDTO.setPassword("newpassword");
//
//        this.band = new Band();
//        this.band.setName("ACDC");
//        this.band.setId(5L);
//
//        this.manager = new Manager();
//        this.manager.setId(6L);
//        this.manager.setName("Anton");
//        this.manager.setUserName("antonn");
//        this.manager.setPassword("1234");
//
//        this.band.setManager(this.manager);
//        this.manager.setBand(this.band);
//
//
//
//        this.band.setMembers(new HashSet<>(allMusicians));
//        this.musician.setBand(this.band);
//        this.testMusician1.setBand(this.band);
//
//        this.bandDTO = new BandDTO();
//        this.bandDTO.setId(5L);
//        this.bandDTO.setName("ACDC");
//
//        this.managerDTO = new ManagerDTO();
//        this.managerDTO.setId(6L);
//        this.managerDTO.setName("Anton");
//        this.managerDTO.setUserName("antonn");
//        this.managerDTO.setPassword("1234");
//        this.managerDTO.setBand(this.bandDTO);
//
//        this.bandDTO.setManager(this.managerDTO);
//
//        when(musicianService.update(any(Musician.class))).thenReturn(testMusician1);
//        when(musicianService.findById(any(Long.class))).thenReturn(testMusician1);
//        when(musicianService.findByUserName(any(String.class))).thenReturn(testMusician1);
//        when(musicianService.findAll()).thenReturn(allMusicians);
//        when(musicianService.findAllByBand(this.band)).thenReturn(allMusicians);
//    }
//
//    @Test
//    public void createMusicianTest(){
//        musicianFacade.create(musicianCreateDTO);
//        verify(musicianService, times(1)).create(any(Musician.class));
//    }
//
//    @Test
//    public void updateMusicianTest(){
//        musicianFacade.update(musicianDTO);
//        verify(musicianService, times(1)).update(any(Musician.class));
//    }
//
//    @Test
//    public void findMusicianByIdTest(){
//        musicianFacade.findById(2L);
//        verify(musicianService, times(1)).findById(2L);
//    }
//
//    @Test
//    public void findMusicianByUserNameTest(){
//        musicianFacade.findByUserName("jlock");
//        verify(musicianService, times(1)).findByUserName("jlock");
//    }
//
//    @Test
//    public void findAllMusicianTest(){
//        musicianFacade.findAll();
//        verify(musicianService, times(1)).findAll();
//    }
//
//    @Test
//    public void removeMusicianTest(){
//        musicianFacade.remove(musicianDTO);
//        verify(musicianService, times(1)).remove(any(Musician.class));
//    }
//
//    @Test
//    public void findAllMusicianByBandTest(){
//        musicianFacade.findAllByBand(bandDTO);
//        verify(musicianService, times(1)).findAllByBand(this.band);
//    }
//}
