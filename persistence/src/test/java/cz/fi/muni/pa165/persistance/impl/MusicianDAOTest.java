package cz.fi.muni.pa165.persistance.impl;

import cz.fi.muni.pa165.MusicBandManagerApplicationContext;
import cz.fi.muni.pa165.entities.Band;
import cz.fi.muni.pa165.entities.Concert;
import cz.fi.muni.pa165.entities.Musician;
import cz.fi.muni.pa165.entities.Tour;
import cz.fi.muni.pa165.enums.Instrument;
import cz.fi.muni.pa165.persistance.interfaces.MusicianDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Jan Kaláb
 */
@ContextConfiguration(classes = MusicBandManagerApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class MusicianDAOTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private MusicianDAO musicianDAO;

    @PersistenceContext
    private EntityManager em;

    @Test
    public void createMusicianTest(){
        Musician musician = createBonJoviMusician();
        musicianDAO.create(musician);
        Musician results = musicianDAO.findById(musician.getId());
        Assert.assertNotNull(results);
        Assert.assertEquals(musician.getName(), results.getName());
        Assert.assertEquals(musician.getUsername(), results.getUsername());
        Assert.assertEquals(musician.getPassword(), results.getPassword());
        Assert.assertEquals(musician.getInstruments().size(), results.getInstruments().size());
    }

    @Test
    public void getByIdTest(){
        Musician musician = createBonJoviMusician();
        musicianDAO.create(musician);
        Musician results = musicianDAO.findById(musician.getId());
        Assert.assertNotNull(results);
        Assert.assertEquals(musician.getName(), results.getName());
        Assert.assertEquals(musician.getUsername(), results.getUsername());
        Assert.assertEquals(musician.getPassword(), results.getPassword());
        Assert.assertEquals(musician.getInstruments().size(), results.getInstruments().size());
    }
    @Test
    public void getByUserNameTest(){
        Musician musician = createBonJoviMusician();
        musicianDAO.create(musician);
        Musician results = musicianDAO.findByUserName(musician.getUsername());
        Assert.assertNotNull(results);
        Assert.assertEquals(musician.getName(), results.getName());
        Assert.assertEquals(musician.getUsername(), results.getUsername());
        Assert.assertEquals(musician.getPassword(), results.getPassword());
        Assert.assertEquals(musician.getInstruments().size(), results.getInstruments().size());
    }

    @Test
    public void getByBandTest(){
        Musician musician = createBonJoviMusician();
        musicianDAO.create(musician);
        List<Musician> results = musicianDAO.findAllByBand(musician.getBand());
        Assert.assertNotNull(results);
        Assert.assertEquals(musician.getName(), results.get(0).getName());
        Assert.assertEquals(musician.getUsername(), results.get(0).getUsername());
        Assert.assertEquals(musician.getPassword(), results.get(0).getPassword());
        Assert.assertEquals(musician.getInstruments().size(), results.get(0).getInstruments().size());
    }


    @Test
    public void getAllTest(){
        Musician musician1 = createBonJoviMusician();
        Musician musician2 = createAxelRoseMusician();
        musicianDAO.create(musician1);
        musicianDAO.create(musician2);
        List<Musician> results = musicianDAO.findAll();
        Assert.assertNotNull(results);
        Assert.assertEquals(results.size(), 2);
        Assert.assertEquals(results.get(0), musician1);
        Assert.assertEquals(results.get(1), musician2);
    }

    @Test
    public void updatePasswordTest(){

        Musician musician = createAxelRoseMusician();
        em.persist(musician);
        musician.setPassword("newpassword");
        musicianDAO.update(musician);
        Musician results = musicianDAO.findById(musician.getId());
        Assert.assertNotNull(results);
        Assert.assertEquals(musician.getName(), results.getName());
        Assert.assertEquals(musician.getUsername(), results.getUsername());
        Assert.assertEquals(musician.getPassword(), results.getPassword());
        //Assert.assertEquals(musician.getInstruments().size(), results.getInstruments().size());
    }

    @Test
    public void removeTest(){
        Musician musician = createBonJoviMusician();
        musicianDAO.create(musician);
        musicianDAO.remove(musician);
        Musician results = musicianDAO.findById(musician.getId());
        Assert.assertNull(results);
    }

    private Musician createBonJoviMusician(){
        Musician bonJovi = new Musician();
        bonJovi.setName("Bon Jovi");
        bonJovi.setUsername("bonjovi");
        bonJovi.setPassword("band");
        bonJovi.setInstruments(Arrays.asList(Instrument.GUITAR, Instrument.PIANO));
        bonJovi.setBand(getDefaultBand());
        return bonJovi;
    }

    private Musician createAxelRoseMusician(){
        Musician axelRose = new Musician();
        axelRose.setName("Axel Rose");
        axelRose.setUsername("axel");
        axelRose.setPassword("iambest");
        //axelRose.setInstruments(Arrays.asList(Instrument.BASS, Instrument.PIANO));
        return axelRose;
    }

    private Band getDefaultBand(){
        Band band = new Band();
        band.setName("Queen");
        em.persist(band);
        return band;
    }


}
