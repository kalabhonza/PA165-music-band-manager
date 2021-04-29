package facade;

import cz.fi.muni.pa165.api.dto.manager.ManagerDTO;
import cz.fi.muni.pa165.api.dto.band.BandDTO;
import cz.fi.muni.pa165.api.dto.musician.MusicianCreateDTO;
import cz.fi.muni.pa165.api.dto.musician.MusicianDTO;
import cz.fi.muni.pa165.api.dto.musician.MusicianUpdateDTO;
import cz.fi.muni.pa165.api.facade.MusicianFacade;
import cz.fi.muni.pa165.entities.Band;
import cz.fi.muni.pa165.entities.Manager;
import cz.fi.muni.pa165.entities.Musician;
import cz.fi.muni.pa165.service.MusicianService;


import cz.fi.muni.pa165.service.facade.MusicianFacadeImpl;
import cz.fi.muni.pa165.service.mapping.mapstruct.BandMapper;
import cz.fi.muni.pa165.service.mapping.mapstruct.BandMapperImpl;
import cz.fi.muni.pa165.service.mapping.mapstruct.MusicianMapperImpl;
import cz.fi.muni.pa165.service.mapping.mapstruct.ManagerMapperImpl;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

public class MusicianFacadeTest {

    private MusicianFacade musicianFacade;
    @Mock
    private MusicianService musicianService;
    @Mock
    private MusicianMapperImpl musicianMapper;
    @Mock
    private BandMapperImpl bandMapper;
    @Mock
    private ManagerMapperImpl managerMapper;

    private Musician musician1;
    private Musician musician2;
    private List<Musician> allMusicians;
    private MusicianDTO musicianDTO;
    private MusicianDTO musicianDTO2;
    private List<MusicianDTO> allMusiciansDTO;
    private MusicianCreateDTO musicianCreateDTO;
    private MusicianUpdateDTO musicianUpdateDTO;
    private Band band;
    private BandDTO bandDTO;
    private Manager manager;
    private ManagerDTO managerDTO;

    @BeforeMethod
    public void unit(){
        MockitoAnnotations.openMocks(this);
        musicianFacade = new MusicianFacadeImpl(musicianService, musicianMapper,bandMapper);

        this.musician1 = new Musician(1L, "Bon Jovi", "bjovi", "prague");
        this.musician2 = new Musician(2L, "John Lock", "jlock", "abcd");

        this.band = new Band();
        this.band.setName("ACDC");
        this.band.setId(5L);

        this.manager = new Manager();
        this.manager.setId(6L);
        this.manager.setName("Anton");
        this.manager.setUserName("antonn");
        this.manager.setPassword("1234");

        this.band.setManager(this.manager);
        this.manager.setBand(this.band);

        this.allMusicians = new ArrayList<>();
        this.allMusicians.add(this.musician1);
        this.allMusicians.add(this.musician2);

        this.band.setMembers(new HashSet<>(allMusicians));
        this.musician1.setBand(this.band);
        this.musician2.setBand(this.band);

        this.musicianCreateDTO = new MusicianCreateDTO();
        this.musicianCreateDTO.setName(musician1.getName());
        this.musicianCreateDTO.setUsername(musician1.getUsername());
        this.musicianCreateDTO.setPassword(musician1.getPassword());

        this.musicianDTO = new MusicianDTO();
        this.musicianDTO.setName(musician1.getName());
        this.musicianDTO.setUsername(musician1.getUsername());
        this.musicianDTO.setPassword(musician1.getPassword());
        this.musicianDTO.setBand(bandMapper.mapToBandDTO(this.band));

        this.musicianDTO2 = new MusicianDTO();
        this.musicianDTO2.setName(musician2.getName());
        this.musicianDTO2.setUsername(musician2.getUsername());
        this.musicianDTO2.setPassword(musician2.getPassword());
        this.musicianDTO2.setBand(bandMapper.mapToBandDTO(this.band));

        this.allMusiciansDTO = new ArrayList<>();
        this.allMusiciansDTO.add(musicianDTO);
        this.allMusiciansDTO.add(musicianDTO2);

        this.musicianUpdateDTO = new MusicianUpdateDTO();
        this.musicianUpdateDTO.setName(musician1.getName());
        this.musicianUpdateDTO.setUsername(musician1.getUsername());
        this.musicianUpdateDTO.setPassword(musician1.getPassword());
        this.musicianUpdateDTO.setBand(bandMapper.mapToBandDTO(this.band));

        this.bandDTO = new BandDTO();
        this.bandDTO.setId(band.getId());
        this.bandDTO.setName(band.getName());

        this.managerDTO = new ManagerDTO();
        this.managerDTO.setId(manager.getId());
        this.managerDTO.setName(manager.getName());
        this.managerDTO.setUserName(manager.getUserName());
        this.managerDTO.setPassword(manager.getPassword());
        this.managerDTO.setBand(bandMapper.mapToBandDTO(this.band));

        this.bandDTO.setManager(managerMapper.mapToManagerDTO(this.manager));

        this.bandDTO.setMembers(new HashSet<>(allMusiciansDTO));

    }

    @Test
    public void createMusicianTest(){
        given(musicianMapper.mapToEntity(musicianCreateDTO)).willReturn(musician1);
        musicianFacade.create(musicianCreateDTO);
        then(musicianService).should().create(musician1);
    }

    @Test
    public void updateMusicianTest(){
        given(musicianMapper.mapToEntity(musicianUpdateDTO)).willReturn(musician1);
        musicianFacade.update(musicianUpdateDTO);
        then(musicianService).should().update(musician1);
    }

    @Test
    public void findMusicianByIdTest(){
        given(musicianService.findById(musician1.getId())).willReturn(musician1);
        given(musicianMapper.mapToMusicianDTO(musician1)).willReturn(musicianDTO);
        MusicianDTO result = musicianFacade.findById(musician1.getId());
        assertEquals(musicianDTO, result);
        then(musicianService).should().findById(musician1.getId());
    }

    @Test
    public void findMusicianByUserNameTest(){
        given(musicianService.findByUserName(musician1.getUsername())).willReturn(musician1);
        given(musicianMapper.mapToMusicianDTO(musician1)).willReturn(musicianDTO);
        MusicianDTO result = musicianFacade.findByUserName(musician1.getUsername());
        assertEquals(musicianDTO, result);
        then(musicianService).should().findByUserName(musician1.getUsername());
    }

    @Test
    public void findAllMusicianTest(){
        given(musicianService.findAll()).willReturn(allMusicians);
        given(musicianMapper.mapToListDTO(allMusicians)).willReturn(allMusiciansDTO);
        List<MusicianDTO> result = musicianFacade.findAll();
        assertEquals(allMusiciansDTO, result);
        then(musicianService).should().findAll();
    }

    @Test
    public void removeMusicianTest(){
        musicianFacade.remove(musicianDTO);
        then(musicianService).should().remove(musicianMapper.mapToEntity(musicianDTO));
    }

    @Test
    public void findAllMusicianByBandTest(){
        given(musicianService.findAllByBand(band)).willReturn(allMusicians);
        given(bandMapper.mapToEntity(bandDTO)).willReturn(band);
        given(musicianMapper.mapToListDTO(allMusicians)).willReturn(allMusiciansDTO);
        List<MusicianDTO> result = musicianFacade.findAllByBand(bandDTO);
        assertEquals(allMusiciansDTO, result);
        then(musicianService).should().findAllByBand(band);
    }
}
