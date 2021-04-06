package cz.fi.muni.pa165.persistance.impl;

import cz.fi.muni.pa165.MusicBandManagerApplicationContext;
import cz.fi.muni.pa165.entities.Album;
import cz.fi.muni.pa165.persistance.Impl.AlbumDAOImpl;
import cz.fi.muni.pa165.persistance.interfaces.AlbumDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.List;

/**
 * @author Aleš Paroulek
 */
@ContextConfiguration(classes = MusicBandManagerApplicationContext.class)
public class AlbumDAOTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private AlbumDAO albumDAO;

    @PersistenceUnit
    private EntityManagerFactory emf;

    @PersistenceContext
    private EntityManager em;

    private Album albumA;
    private Album albumB;
    private Album albumC;

    @BeforeMethod
    public void beforeTest() {
        EntityManager em = null;

        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();

            albumA = new Album();
            albumA.setName("A");
            albumA.setId(1L);
            em.persist(albumA);

            albumB = new Album();
            albumB.setName("B");
            albumB.setId(2L);
            em.persist(albumB);

            albumC = new Album();
            albumC.setName("C");
            albumC.setId(3L);
            em.persist(albumC);

            em.getTransaction().commit();
        } finally {
            if (em != null) em.close();
        }
    }

//    @Test
//    public void findAlbumById() {
//        Album resultA = albumDAO.getById(1L);
//        Album resultB = albumDAO.getById(2L);
//        Album resultC = albumDAO.getById(3L);
//
//        Assert.assertEquals(resultA.getName(), albumA.getName());
//        Assert.assertEquals(resultB.getName(), albumB.getName());
//        Assert.assertEquals(resultC.getName(), albumC.getName());
//    }

//    @Test
//    public void findAllAlbums() {
//        List<Album> albums = albumDAO.getAllAlbums();
//        Assert.assertEquals(albums.size(), 3);
//    }

//    @Test
//    public void createAlbum() {
//        Album albumD = new Album();
//        albumD.setName("D");
//        albumD.setId(4L);
//
//        albumDAO.create(albumD);
//
//        Album resultD = albumDAO.getById(4L);
//        Assert.assertEquals(albumD.getName(), resultD.getName());
//    }

//    @Test
//    public void updateAlbum() {
//        albumB.setName("Z");
//        albumDAO.update(albumB);
//
//        Album resultB = albumDAO.getById(2L);
//        Assert.assertEquals(albumB.getName(), resultB.getName());
//    }
//
//    @Test
//    public void removeAlbum() {
//        int beforeDeleteCount = albumDAO.getAllAlbums().size();
//        albumDAO.remove(albumB);
//        int afterDeleteCount = albumDAO.getAllAlbums().size();
//
//        Assert.assertTrue(beforeDeleteCount > afterDeleteCount);
//    }
//
//    @Test
//    public void getAlbumsByName() {
//        List<Album> sameNameAlbums = albumDAO.getAlbumsByName("A");
//        Assert.assertEquals(sameNameAlbums.size(), 2);
//    }

    @AfterMethod
    public void afterTest() {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.createQuery("delete from albums").executeUpdate();
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


}
