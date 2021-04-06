package cz.fi.muni.pa165.persistance.impl;

import cz.fi.muni.pa165.MusicBandManagerApplicationContext;
import cz.fi.muni.pa165.entities.Song;
import cz.fi.muni.pa165.persistance.interfaces.SongDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.List;

/**
 * @author Aleš Paroulek
 */
@ContextConfiguration(classes = MusicBandManagerApplicationContext.class)
public class SongDAOTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private SongDAO songDAO;

    @PersistenceUnit
    private EntityManagerFactory emf;

    @PersistenceContext
    private EntityManager em;

    private Song songA;
    private Song songB;
    private Song songC;

    @BeforeMethod
    public void beforeTest() {
        EntityManager em = null;

        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();

            songA = new Song();
            songA.setName("A");
            songA.setId(1L);
            em.persist(songA);

            songB = new Song();
            songB.setName("B");
            songB.setId(2L);
            em.persist(songB);

            songC = new Song();
            songC.setName("A");
            songC.setId(3L);
            em.persist(songC);

            em.getTransaction().commit();
        } finally {
            if (em != null) em.close();
        }
    }

    @Test
    public void findSongById() {
        Song resultA = songDAO.findSongById(1L);
        Song resultB = songDAO.findSongById(2L);
        Song resultC = songDAO.findSongById(3L);

        Assert.assertEquals(resultA.getName(), songA.getName());
        Assert.assertEquals(resultB.getName(), songB.getName());
        Assert.assertEquals(resultC.getName(), songC.getName());
    }

    @Test
    public void findAllSongs() {
        List<Song> songs = songDAO.findAll();
        Assert.assertEquals(songs.size(), 3);
    }

    @Test
    public void createSong() {
        Song songD = new Song();
        songD.setName("D");
        songD.setId(4L);

        songDAO.create(songD);

        Song resultD = songDAO.findSongById(4L);
        Assert.assertEquals(songD.getName(), resultD.getName());
    }

    @Test
    public void updateSong() {
        songB.setName("Z");
        songDAO.update(songB);

        Song resultB = songDAO.findSongById(2L);
        Assert.assertEquals(songB.getName(), resultB.getName());
    }

    @Test
    public void removeSong() {
        int beforeDeleteCount = songDAO.findAll().size();
        songDAO.deleteSong(songB);
        int afterDeleteCount = songDAO.findAll().size();

        Assert.assertTrue(beforeDeleteCount > afterDeleteCount);
    }

    @Test
    public void findSongByName() {
        Song resultA = songDAO.findSongByName("A");
        Assert.assertEquals(resultA.getId(), songA.getId());

    }

    @AfterMethod
    public void afterTest() {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.createQuery("delete from songs").executeUpdate();
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


}
