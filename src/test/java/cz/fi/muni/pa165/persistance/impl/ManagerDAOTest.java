package cz.fi.muni.pa165.persistance.impl;

import cz.fi.muni.pa165.MusicBandManagerApplicationContext;
import cz.fi.muni.pa165.entities.Album;
import cz.fi.muni.pa165.entities.Band;
import cz.fi.muni.pa165.entities.Manager;
import cz.fi.muni.pa165.entities.Song;
import cz.fi.muni.pa165.enums.Style;
import cz.fi.muni.pa165.persistance.interfaces.ManagerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.sql.Time;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Igor Ignác
 */
@ContextConfiguration(classes = MusicBandManagerApplicationContext.class)
public class ManagerDAOTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private ManagerDAO managerDAO;

    @PersistenceUnit
    private EntityManagerFactory emf;

    @PersistenceContext
    private EntityManager em;


    @Test
    public void createTest() {
        Manager manager = new Manager();

        manager.setName("Joseph");
        manager.setBand(mockBand(manager, "Band 1"));
        managerDAO.create(manager);
        Manager result = findManagerById(manager.getId());
        Assert.assertNotNull(result);
        Assert.assertEquals(manager.getName(), result.getName());
        Assert.assertEquals(manager.getBand(), result.getBand());
    }

    @Test
    public void getByIdTest() {
        Manager manager = new Manager();

        manager.setName("Joseph");
        manager.setBand(mockBand(manager, "Band 1"));
        manager.setId(1L);

        managerDAO.create(manager);
        Manager result = managerDAO.getById(manager.getId());

        Assert.assertNotNull(result);
        Assert.assertEquals(manager.getName(), result.getName());
        Assert.assertEquals(manager.getBand(), result.getBand());
    }

    @Test
    public void getAllTest() {
        Manager manager1 = new Manager();
        manager1.setName("Joseph");
        manager1.setBand(mockBand(manager1, "Band 1"));

        Manager manager2 = new Manager();
        manager2.setName("Joseph");
        manager2.setBand(mockBand(manager2, "Band 2"));

        managerDAO.create(manager1);
        managerDAO.create(manager2);
        List<Manager> managers = managerDAO.getAll();

        Assert.assertNotNull(managers);
        Assert.assertEquals(managers.size(), 2);
        Assert.assertEquals(managers.get(0), manager1);
        Assert.assertEquals(managers.get(1), manager2);
    }

    @Test
    public void updateTest() {
        Manager manager = new Manager();

        manager.setName("Joseph");
        manager.setBand(mockBand(manager, "Band 1"));

        managerDAO.create(manager);

        manager.setName("Peter");
        managerDAO.update(manager);

        Manager result = findManagerById(manager.getId());
        Assert.assertNotNull(result);
        Assert.assertEquals(manager.getName(), result.getName());
        Assert.assertEquals(manager.getBand(), result.getBand());
    }

    @Test
    public void removeTest() {
        Manager manager = new Manager();

        manager.setName("Joseph");
        manager.setBand(mockBand(manager, "Band 1"));
        managerDAO.create(manager);

        managerDAO.remove(manager);
        Manager result = findManagerById(manager.getId());
        Assert.assertNull(result);
    }

    @Test
    public void getByUserNameTest() {
        Manager manager = new Manager();

        manager.setName("Joseph");
        manager.setBand(mockBand(manager, "Band 1"));
        manager.setUserName("jj245");

        managerDAO.create(manager);
        Manager result = managerDAO.getByUserName("jj245");

        Assert.assertNotNull(result);
        Assert.assertEquals(manager.getName(), result.getName());
        Assert.assertEquals(manager.getBand(), result.getBand());
    }

    @Test
    public void getByNameTest() {
        Manager manager = new Manager();

        manager.setName("Joseph");
        manager.setBand(mockBand(manager, "Band 1"));

        managerDAO.create(manager);
        Manager result = managerDAO.getByName("Joseph");

        Assert.assertNotNull(result);
        Assert.assertEquals(manager.getName(), result.getName());
        Assert.assertEquals(manager.getBand(), result.getBand());
    }

    private Manager findManagerById(Long id) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();

            Manager manager = em.find(Manager.class, id);
            em.getTransaction().commit();
            em.close();
            return manager;
        } finally {
            if (em != null) em.close();
        }
    }

    private Band mockBand(Manager manager, String name) {
        Band band = new Band();
        Set<Album> albumSet = new HashSet<>();

        albumSet.add(mockAlbum("Album 1"));
        albumSet.add(mockAlbum("Album 2"));
        band.setManager(manager);
        band.setName(name);
        band.setStyle(Style.ALTERNATIVE);
        band.setAlbums(albumSet);
        return band;
    }

    private Album mockAlbum(String name) {
        Album album = new Album();

        album.setName(name);
        album.setSongs(Arrays.asList(mockSong("Song 1 "), mockSong("Song 2")));
        return album;
    }

    private Song mockSong(String name) {
        Song song = new Song();

        song.setDuration(new Time(1500));
        song.setName(name);
        return song;
    }
}
