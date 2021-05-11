package cz.fi.muni.pa165.persistence.impl;

import cz.fi.muni.pa165.MusicBandManagerApplicationContext;
import cz.fi.muni.pa165.entities.Concert;
import cz.fi.muni.pa165.persistence.interfaces.ConcertDAO;
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
import java.time.LocalDate;
import java.util.List;

/**
 * @author Albert Sukaný
 */
@ContextConfiguration(classes = MusicBandManagerApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class ConcertDAOTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private ConcertDAO concertDAO;

    @PersistenceContext
    private EntityManager entityManager;

    private Concert concert;

    @BeforeMethod
    public void init(){
        concert = new Concert();
        concert.setName("Masters of Rock");
        LocalDate date = LocalDate.of(2021,6,1);
        concert.setDate(date);
    }

    @Test
    public void findByIdTest(){
        entityManager.persist(concert);
        Concert savedConcert = concertDAO.findById(concert.getId());
        Assert.assertNotNull(savedConcert);
        Assert.assertEquals(concert,savedConcert);

    }

    @Test
    public void findAllTest(){
        entityManager.persist(concert);
        List<Concert> savedConcerts = concertDAO.findAll();

        Assert.assertNotNull(savedConcerts);
        Assert.assertEquals(savedConcerts.size(), 1);
        Assert.assertTrue(savedConcerts.contains(concert));
    }

    @Test
    public void findAllByDateTest(){
        entityManager.persist(concert);
        List<Concert> savedConcerts = concertDAO.findAllByDate(LocalDate.of(2021,6,1));

        Assert.assertNotNull(savedConcerts);
        Assert.assertEquals(savedConcerts.size(), 1);
        Assert.assertTrue(savedConcerts.contains(concert));
    }

    @Test
    public void create() {
        concertDAO.create(concert);

        Assert.assertTrue(entityManager.contains(concert));
        Assert.assertNotNull(concert.getId());
        Assert.assertTrue(entityManager.contains(concert));
    }

    @Test
    public void update() {
        entityManager.persist(concert);
        concert.setName("Colors of Ostrava");
        concertDAO.update(concert);

        Assert.assertEquals(concert.getName(), "Colors of Ostrava");
    }

    @Test
    public void remove() {
        entityManager.persist(concert);

        Assert.assertTrue(entityManager.contains(concert));

        concertDAO.remove(concert);

        Assert.assertFalse(entityManager.contains(concert));
    }





}
