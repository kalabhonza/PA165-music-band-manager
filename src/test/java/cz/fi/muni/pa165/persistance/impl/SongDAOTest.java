package cz.fi.muni.pa165.persistance.impl;

import cz.fi.muni.pa165.MusicBandManagerApplicationContext;
import cz.fi.muni.pa165.entities.Song;
import cz.fi.muni.pa165.persistance.interfaces.SongDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.sql.Time;
import java.util.List;

/**
 * @author Aleš Paroulek
 */
@ContextConfiguration(classes = MusicBandManagerApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class SongDAOTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private SongDAO songDAO;

    @PersistenceContext
    private EntityManager em;

    private Song songA;
    private Song songB;
    private Song songC;

    @BeforeMethod
    public void beforeTest() {
        songA = new Song();
        songA.setName("A");
        songA.setDuration(new Time(60));
        em.persist(songA);

        songB = new Song();
        songB.setName("B");
        songB.setDuration(new Time(60));
        em.persist(songB);

        songC = new Song();
        songC.setName("C");
        songC.setDuration(new Time(60));
        em.persist(songC);
    }

    @Test
    public void findSongById() {
        Song resultA = songDAO.findSongById(songA.getId());
        Song resultB = songDAO.findSongById(songB.getId());
        Song resultC = songDAO.findSongById(songC.getId());

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
        songD.setDuration(new Time(60));

        songDAO.create(songD);

        Song resultD = songDAO.findSongById(songD.getId());
        Assert.assertEquals(songD.getName(), resultD.getName());
    }

    @Test
    public void updateSong() {
        songB.setName("Z");
        songDAO.update(songB);

        Song resultB = songDAO.findSongById(songB.getId());
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
        songA.setName("test");
        songB.setName("test");
        List<Song> sameNameSongs = songDAO.findSongByName("test");
        Assert.assertEquals(sameNameSongs.size(), 2);
    }

    @AfterMethod
    public void afterTest() {
        em.createQuery("delete from songs").executeUpdate();
    }


}
