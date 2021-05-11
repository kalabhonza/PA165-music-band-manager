package cz.fi.muni.pa165.persistence.impl;

import cz.fi.muni.pa165.MusicBandManagerApplicationContext;
import cz.fi.muni.pa165.entities.Concert;
import cz.fi.muni.pa165.entities.Tour;
import cz.fi.muni.pa165.persistence.interfaces.TourDAO;
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
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Jan Kaláb
 */
@ContextConfiguration(classes = MusicBandManagerApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class TourDAOTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private TourDAO tourDAO;

    @PersistenceContext
    private EntityManager em;

    @Test
    public void createTourTest(){
        Tour tour1 = createAcdcTour();
        tourDAO.create(tour1);
        Tour results = tourDAO.findById(tour1.getId());
        Assert.assertNotNull(results);
        Assert.assertEquals(tour1.getName(), results.getName());
        Assert.assertEquals(tour1.getConcerts().size(), results.getConcerts().size());
    }

    @Test
    public void getByIdTest(){
        Tour tour1 = createAcdcTour();
        tourDAO.create(tour1);
        Tour results = tourDAO.findById(tour1.getId());
        Assert.assertNotNull(results);
        Assert.assertEquals(tour1.getName(), results.getName());
        Assert.assertEquals(tour1.getConcerts().size(), results.getConcerts().size());
    }
    @Test
    public void getByNameTest(){
        Tour tour1 = createAcdcTour();
        tourDAO.create(tour1);
        List<Tour> results = tourDAO.findByName(tour1.getName());
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        Assert.assertEquals(tour1.getName(), results.get(0).getName());
        Assert.assertEquals(tour1.getConcerts().size(), results.get(0).getConcerts().size());

    }


    @Test
    public void getAllTest(){
        Tour acdcTour = createAcdcTour();
        Tour queenTour = createQueenTour();
        tourDAO.create(acdcTour);
        tourDAO.create(queenTour);
        List<Tour> tours = tourDAO.findAllTours();
        Assert.assertNotNull(tours);
        Assert.assertEquals(tours.size(), 2);
        Assert.assertEquals(tours.get(0), acdcTour);
        Assert.assertEquals(tours.get(1), queenTour);
    }

    @Test
    public void updateNameTest(){
        Tour tour1 = createAcdcTour();
        tourDAO.create(tour1);
        tour1.setName("ACDC last tour Europe 2022");
        tourDAO.update(tour1);
        Tour results = tourDAO.findById(tour1.getId());
        Assert.assertNotNull(results);
        Assert.assertEquals(tour1.getName(), results.getName());
        Assert.assertEquals(tour1.getConcerts().size(), results.getConcerts().size());

    }

    @Test
    public void removeTest(){
        Tour tour1 = createAcdcTour();
        tourDAO.create(tour1);
        tourDAO.remove(tour1);
        Tour results = tourDAO.findById(tour1.getId());
        Assert.assertNull(results);
    }

    private Tour createAcdcTour(){
        Tour acdcTour = new Tour();
        acdcTour.setName("ACDC tour Europe 2022");
        Set<Concert> concertSet = new HashSet<Concert>();
        concertSet.add(createAcdcConcert1());
        concertSet.add(createAcdcConcert2());
        concertSet.add(createAcdcConcert3());
        concertSet.add(createAcdcConcert4());
        acdcTour.setConcerts(concertSet);
        return acdcTour;
    }

    private Concert createAcdcConcert1(){
        Concert acdcConcert1 = new Concert();
        acdcConcert1.setName("Prague - O2 Arena");
        acdcConcert1.setDate(LocalDate.of(2022, 1, 20));
        em.persist(acdcConcert1);
        return acdcConcert1;
    }

    private Concert createAcdcConcert2(){
        Concert acdcConcert2 = new Concert();
        acdcConcert2.setName("Bratislava - Zimni Stadion Ondreje Nepelu");
        acdcConcert2.setDate(LocalDate.of(2022, 1, 24));
        em.persist(acdcConcert2);
        return acdcConcert2;
    }

    private Concert createAcdcConcert3(){
        Concert acdcConcert3 = new Concert();
        acdcConcert3.setName("London - London Arena");
        acdcConcert3.setDate(LocalDate.of(2022, 1, 28));
        em.persist(acdcConcert3);
        return acdcConcert3;
    }

    private Concert createAcdcConcert4(){
        Concert acdcConcert4 = new Concert();
        acdcConcert4.setName("Paris - Paris La Defense Arena");
        acdcConcert4.setDate(LocalDate.of(2022, 2, 2));
        em.persist(acdcConcert4);
        return acdcConcert4;
    }

    private Tour createQueenTour(){
        Tour queenTour = new Tour();
        queenTour.setName("Queen tour Europe 2023");
        Set<Concert> concertSet = new HashSet<Concert>();
        concertSet.add(createQueenConcert1());
        concertSet.add(createQueenConcert2());
        queenTour.setConcerts(concertSet);
        return queenTour;
    }

    private Concert createQueenConcert1(){
        Concert queenConcert1 = new Concert();
        queenConcert1.setName("Ostrava - Ostravar Arena");
        queenConcert1.setDate(LocalDate.of(2023, 4, 2));
        em.persist(queenConcert1);
        return queenConcert1;
    }

    private Concert createQueenConcert2(){
        Concert queenConcert2 = new Concert();
        queenConcert2.setName("Praha - Forum Karlin");
        queenConcert2.setDate(LocalDate.of(2023, 4, 6));
        em.persist(queenConcert2);
        return queenConcert2;
    }
}
