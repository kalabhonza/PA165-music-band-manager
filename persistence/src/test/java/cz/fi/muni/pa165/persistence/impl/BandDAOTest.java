package cz.fi.muni.pa165.persistence.impl;

import cz.fi.muni.pa165.MusicBandManagerApplicationContext;
import cz.fi.muni.pa165.entities.Band;
import cz.fi.muni.pa165.entities.Manager;
import cz.fi.muni.pa165.persistence.interfaces.BandDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Albert Sukaný
 */
@ContextConfiguration(classes = MusicBandManagerApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class BandDAOTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private BandDAO bandDAO;



    @PersistenceContext
    private EntityManager entityManager;

    private Band band;
    private Manager manager;
    //constructor = manager + name


    @BeforeMethod
    public void init(){
        band = new Band();
        band.setName("Sabaton");

        manager = new Manager();
        manager.setName("Franta");
        manager.setUserName("Franta 07");
        manager.setPassword("xd123");
        manager.setBand(band.getId());
        entityManager.persist(manager);

        band.setManager(manager);

    }

    @Test
    public void findBandByIdTest(){
        entityManager.persist(band);

        Band savedBand = bandDAO.findBandById(band.getId());
        Assert.assertNotNull(savedBand);
        Assert.assertEquals(band,savedBand);
    }
    @Test
    public void findBandByNameTest(){
        entityManager.persist(band);
        List<Band> bands = bandDAO.findBandByName("Sabaton");

        Assert.assertNotNull(bands);
        Assert.assertEquals(bands.size(), 1);
        Assert.assertTrue(bands.contains(band));

    }

    @Test
    public void findBandByManagerTest(){
        entityManager.persist(band);
        Band savedBand = bandDAO.findBandByManager(manager);

        Assert.assertNotNull(savedBand);
        Assert.assertEquals(savedBand.getManager(), manager);
        Assert.assertEquals(savedBand,band);
    }

    @Test
    public void findAllTest(){
        entityManager.persist(band);
        List<Band> savedBands = bandDAO.findAll();

        Assert.assertNotNull(savedBands);
        Assert.assertTrue(savedBands.contains(band));
        Assert.assertEquals(savedBands.size(),1);
    }

    @Test
    public void create() {
        bandDAO.create(band);

        Assert.assertTrue(entityManager.contains(band));
        Assert.assertNotNull(band.getId());
        Assert.assertTrue(entityManager.contains(band));
    }

    @Test
    public void update() {
        entityManager.persist(band);
        band.setName("Deep purple");
        bandDAO.update(band);

        Assert.assertEquals(band.getName(), "Deep purple");
    }

    @Test
    public void remove() {
        entityManager.persist(band);

        Assert.assertTrue(entityManager.contains(band));

        bandDAO.deleteBand(band);

        Assert.assertFalse(entityManager.contains(band));
    }





}
