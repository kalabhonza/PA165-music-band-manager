package cz.fi.muni.pa165.persistance.impl;

import cz.fi.muni.pa165.MusicBandManagerApplicationContext;
import cz.fi.muni.pa165.entities.Album;
import cz.fi.muni.pa165.persistance.interfaces.AlbumDAO;
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
import java.util.List;

/**
 * @author Aleš Paroulek
 */
@ContextConfiguration(classes = MusicBandManagerApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class AlbumDAOTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private AlbumDAO albumDAO;


    @PersistenceContext
    private EntityManager em;

    private Album albumA;
    private Album albumB;
    private Album albumC;

    @BeforeMethod
    public void beforeTest() {
        albumA = new Album();
        albumA.setName("A");
        em.persist(albumA);

        albumB = new Album();
        albumB.setName("B");
        em.persist(albumB);

        albumC = new Album();
        albumC.setName("C");
        em.persist(albumC);
    }

    @Test
    public void findAlbumById() {
        Album resultA = albumDAO.findById(albumA.getId());
        Album resultB = albumDAO.findById(albumB.getId());
        Album resultC = albumDAO.findById(albumC.getId());

        Assert.assertEquals(resultA.getName(), albumA.getName());
        Assert.assertEquals(resultB.getName(), albumB.getName());
        Assert.assertEquals(resultC.getName(), albumC.getName());
    }

    @Test
    public void findAllAlbums() {
        List<Album> albums = albumDAO.findAllAlbums();
        Assert.assertEquals(albums.size(), 3);
    }

    @Test
    public void createAlbum() {
        Album albumD = new Album();
        albumD.setName("D");

        albumDAO.create(albumD);

        Album resultD = albumDAO.findById(albumD.getId());
        Assert.assertEquals(albumD.getName(), resultD.getName());
    }

    @Test
    public void updateAlbum() {
        albumB.setName("Z");
        albumDAO.update(albumB);

        Album resultB = albumDAO.findById(albumB.getId());
        Assert.assertEquals(albumB.getName(), resultB.getName());
    }

    @Test
    public void removeAlbum() {
        int beforeDeleteCount = albumDAO.findAllAlbums().size();
        albumDAO.remove(albumB);
        int afterDeleteCount = albumDAO.findAllAlbums().size();

        Assert.assertTrue(beforeDeleteCount > afterDeleteCount);
    }

    @Test
    public void getAlbumsByName() {
        albumA.setName("test");
        albumB.setName("test");
        List<Album> sameNameAlbums = albumDAO.findAlbumsByName("test");
        Assert.assertEquals(sameNameAlbums.size(), 2);
    }

    @AfterMethod
    public void afterTest() {
        em.createQuery("delete from albums").executeUpdate();
    }

}
