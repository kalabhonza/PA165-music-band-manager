package cz.fi.muni.pa165.persistance.impl;

import cz.fi.muni.pa165.MusicBandManagerApplicationContext;
import cz.fi.muni.pa165.entities.Album;
import cz.fi.muni.pa165.entities.Band;
import cz.fi.muni.pa165.entities.Manager;
import cz.fi.muni.pa165.entities.Song;
import cz.fi.muni.pa165.enums.Style;
import cz.fi.muni.pa165.persistance.interfaces.BandDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

/**
 * @author Albert Sukaný
 */
@ContextConfiguration(classes = MusicBandManagerApplicationContext.class)
//@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class BandDAOTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private BandDAO bandDAO;

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void createTest(){
        Band band = new Band();
        band.setName("Sabaton");
        System.out.println("XD");
//        byte[] logo = "\u00e0\u004f".getBytes();
//        band.setLogo(logo);
        band.setManager(mockManager(1L, "franta", "franta09", "xd"));
        System.out.println("XD2");

        bandDAO.create(band);
        System.out.println("XD3");
        Band result = findBandById(band.getId());
        System.out.println("XD4");
        Assert.assertNotNull(result);
        Assert.assertEquals(band.getName(), result.getName());
        Assert.assertEquals(band.getManager(), result.getManager());
        System.out.println("XD5");


    }
    private Manager mockManager(Long id, String name, String userName, String password){
        Manager manager = new Manager();
        manager.setName(name);
        manager.setUserName(userName);
        manager.setPassword(password);
        return manager;
    }


//    private Band mockBand(Manager manager, String name) {
//        Band band = new Band();
//        Set<Album> albumSet = new HashSet<>();
//
//        albumSet.add(mockAlbum("Album 1"));
//        albumSet.add(mockAlbum("Album 2"));
//        band.setManager(manager);
//        band.setName(name);
//        band.setStyle(Style.ALTERNATIVE);
//        band.setAlbums(albumSet);
//        return band;
//    }
//
//    private Album mockAlbum(String name) {
//        Album album = new Album();
//
//        album.setName(name);
//        Set<Song> randomSongs = new HashSet<Song>();
//        randomSongs.add(mockSong("Song 1 "));
//        randomSongs.add(mockSong("Song 2 "))
//
//        album.setSongs(randomSongs);
//        return album;
//    }
//
//    private Song mockSong(String name) {
//        Song song = new Song();
//
//        song.setDuration(new Time(1500));
//        song.setName(name);
//        return song;
//    }

    private Band findBandById(Long id) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            Band band = entityManager.find(Band.class, id);
            entityManager.getTransaction().commit();
            entityManager.close();
            return band;
        } finally {
            if (entityManager != null) entityManager.close();
        }
    }
}
