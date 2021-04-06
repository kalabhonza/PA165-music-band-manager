package cz.fi.muni.pa165.persistance.impl;

import cz.fi.muni.pa165.MusicBandManagerApplicationContext;
import cz.fi.muni.pa165.entities.*;
import cz.fi.muni.pa165.enums.Instrument;
import cz.fi.muni.pa165.enums.Style;
import cz.fi.muni.pa165.persistance.interfaces.ManagerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Time;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Igor Ignác
 */
@ContextConfiguration(classes = MusicBandManagerApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class ManagerDAOTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private ManagerDAO managerDAO;

    @PersistenceContext
    private EntityManager em;

    private Manager manager;

    @BeforeMethod
    public void init() {
        manager = new Manager();
        manager.setName("Joseph");
        manager.setPassword("password");
        manager.setUserName("joseph12");

        Song song = new Song();
        song.setDuration(new Time(1500));
        song.setName("Song 1");
        em.persist(song);

        Song song2 = new Song();
        song2.setDuration(new Time(1500));
        song2.setName("Song 1");
        em.persist(song2);

        Album album = new Album();
        album.setName("Album 1");
        album.setSongs(Arrays.asList(song, song2));
        em.persist(album);
        Set<Album> albumSet = new HashSet<>();
        albumSet.add(album);

        Band band = new Band();
        band.setManager(manager);
        band.setName("Band");
        band.setStyle(Style.ALTERNATIVE);
        band.setAlbums(albumSet);
        em.persist(band);

        Musician musician = new Musician();
        musician.setPassword("passwordverylongone");
        musician.setName("Peter Re");
        musician.setInstruments(Arrays.asList(Instrument.BASS, Instrument.GUITAR));
        musician.setUsername("repter");
        em.persist(musician);

        manager.setBand(band);
    }

    @Test
    public void findById() {
        em.persist(manager);
        Manager managerFromDb = managerDAO.findById(manager.getId());
        Assert.assertNotNull(managerFromDb);
        Assert.assertEquals(manager, managerFromDb);
    }

    @Test
    public void getAll() {
        em.persist(manager);
        List<Manager> managers = managerDAO.getAll();
        Assert.assertEquals(managers.size(), 1);
        Assert.assertEquals(managers.get(0).getName(), manager.getName());
    }

    @Test
    public void create() {
        managerDAO.create(manager);
        Assert.assertTrue(em.contains(manager));
        Assert.assertNotNull(manager.getId());
        Assert.assertTrue(em.contains(manager));
    }

    @Test
    public void update() {
        em.persist(manager);
        manager.setName("newName");

        managerDAO.update(manager);
        Assert.assertEquals(manager.getName(), "newName");
    }

    @Test
    public void remove() {
        em.persist(manager);

        Assert.assertTrue(em.contains(manager));
        managerDAO.remove(manager);
        Assert.assertFalse(em.contains(manager));
    }

    @Test
    public void getByUserName() {
        em.persist(manager);

        Manager managerFromDb = managerDAO.getByUserName("joseph12");
        Assert.assertNotNull(managerFromDb);
        Assert.assertEquals(managerFromDb, manager);
    }

    @Test
    public void getByName() {
        em.persist(manager);

        Manager managerFromDb = managerDAO.getByName("Joseph");
        Assert.assertNotNull(managerFromDb);
        Assert.assertEquals(managerFromDb, manager);
    }
}
