package facade;

import com.github.dozermapper.core.inject.Inject;
import cz.fi.muni.pa165.api.dto.MusicianCreateDTO;
import cz.fi.muni.pa165.api.dto.MusicianDTO;
import cz.fi.muni.pa165.entities.Musician;
import cz.fi.muni.pa165.service.MusicianService;
import cz.fi.muni.pa165.service.facade.MusicianFacadeImpl;
import cz.fi.muni.pa165.service.mapping.BeanMapper;
import cz.fi.muni.pa165.service.mapping.BeanMapperImpl;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.test.context.ContextConfiguration;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = BeanMapperImpl.class)
public class MusicianFacadeTest {
    @Mock
    private MusicianService musicianService;

    @Mock
    private BeanMapper beanMapper;

    @Inject
    @InjectMocks
    private MusicianFacadeImpl musicianFacade;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    private MusicianCreateDTO musicianCreateDTO;

    private MusicianDTO musicianDTO;

    private Musician musician;

    private Musician testMusician1;

    @Before
    public void setUp(){
        this.musician = new Musician(1L, "Bon Jovi", "bjovi", "prague");

        this.testMusician1 = new Musician(2L, "John Lock", "jlock", "abcd");

        this.musicianCreateDTO = new MusicianCreateDTO();
        this.musicianCreateDTO.setName("Honza Kaláb");
        this.musicianCreateDTO.setUsername("honzaa");
        this.musicianCreateDTO.setPassword("12345");

        this.musicianDTO = new MusicianDTO();
        this.musicianDTO.setId(2L);
        this.musicianDTO.setName("John Lock");
        this.musicianDTO.setUsername("jlock");
        this.musicianDTO.setPassword("newpassword");

        when(musicianService.update(any(Musician.class))).thenReturn(testMusician1);

    }

    @Test
    public void createMusicianTest(){
        musicianFacade.create(musicianCreateDTO);
        verify(musicianService, times(1)).create(any(Musician.class));
    }

    @Test
    public void updateMusicianTest(){
        musicianFacade.update(musicianDTO);
        verify(musicianService, times(1)).update(any(Musician.class));
    }

}
