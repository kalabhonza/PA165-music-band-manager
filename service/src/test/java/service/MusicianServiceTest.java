package service;

import cz.fi.muni.pa165.entities.Band;
import cz.fi.muni.pa165.entities.Manager;
import cz.fi.muni.pa165.entities.Musician;
import cz.fi.muni.pa165.persistence.interfaces.BandDAO;
import cz.fi.muni.pa165.persistence.interfaces.MusicianDAO;
import cz.fi.muni.pa165.service.service.musician.MusicianService;
import cz.fi.muni.pa165.service.service.musician.MusicianServiceImpl;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.testng.Assert.assertEquals;

public class MusicianServiceTest {

    private MusicianService musicianService;
    private Musician musician1;
    private Musician musician2;
    private List<Musician> allMusicians;
    private Band band;
    private Manager manager;

    @Mock
    private MusicianDAO musicianDAO;
    @Mock
    private BandDAO bandDAO;

    @BeforeMethod
    private void init() {
        MockitoAnnotations.initMocks(this);
        musicianService = new MusicianServiceImpl(musicianDAO, bandDAO);
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
        this.manager.setBand(this.band.getId());

        this.allMusicians = new ArrayList<>();
        this.allMusicians.add(this.musician1);
        this.allMusicians.add(this.musician2);

        this.band.setMembers(new HashSet<>(allMusicians));
        this.musician1.setBand(this.band.getId());
        this.musician2.setBand(this.band.getId());
    }

    @Test
    public void acceptOfferTest() {
        Musician lonelyMusician = new Musician();
        lonelyMusician.setId(555L);

        Band bandToAccept = new Band();
        bandToAccept.setId(666L);
        assertEquals(lonelyMusician.getBand(), null);

        lonelyMusician.setOffers(new HashSet<Band>(){{
            add(bandToAccept); }});

        given(bandDAO.update(bandToAccept)).willReturn(bandToAccept);
        musicianService.acceptOffer(lonelyMusician, bandToAccept);
        assertEquals(lonelyMusician.getBand(), bandToAccept.getId());
    }

    @Test
    public void createMusicianTest() {
        Long id = musicianService.create(musician1);
        then(musicianDAO).should().create(musician1);
    }

    @Test
    public void updateMusicianTest() {
        given(musicianDAO.update(musician1)).willReturn(musician1);
        Musician musician = musicianService.update(musician1);
        assertEquals(musician, musician1);
        then(musicianDAO).should().update(musician1);
    }

    @Test
    public void deleteMusicianTest() {
        musicianService.remove(musician1);
        then(musicianDAO).should().remove(musician1);
    }

    @Test
    public void findMusicianByIdTest() {
        given(musicianDAO.findById(musician1.getId())).willReturn(musician1);
        Musician musician = musicianService.findById(musician1.getId());
        assertEquals(musician, musician1);
        then(musicianDAO).should().findById(musician1.getId());
    }

    @Test
    public void findMusiciansByUserNameTest() {
        given(musicianDAO.findByUserName(musician1.getUsername())).willReturn(musician1);
        Musician musician = musicianService.findByUserName(musician1.getUsername());
        assertEquals(musician, musician1);
        then(musicianDAO).should().findByUserName(musician1.getUsername());
    }

    @Test
    public void findAllMusiciansTest() {
        given(musicianDAO.findAll()).willReturn(allMusicians);
        List<Musician> musicians = musicianService.findAll();
        assertEquals(musicians, allMusicians);
        then(musicianDAO).should().findAll();
    }

    @Test
    public void findMusiciansByBandTest() {
        given(musicianDAO.findAllByBand(band.getId())).willReturn(allMusicians);
        List<Musician> musicians = musicianService.findAllByBand(band.getId());
        assertEquals(musicians, allMusicians);
        then(musicianDAO).should().findAllByBand(band.getId());
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void findMusicianByNonExistingIdTest() {
        given(musicianDAO.findById(anyLong())).willReturn(null);
        musicianService.findById(10L);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void findMusicianByNonExistingUserNameTest() {
        given(musicianDAO.findByUserName(anyString())).willReturn(null);
        musicianService.findByUserName("Peeeete21");
    }

}
