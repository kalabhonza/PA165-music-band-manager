package cz.fi.muni.pa165.persistence.impl;

import cz.fi.muni.pa165.entities.Tour;
import cz.fi.muni.pa165.persistence.interfaces.TourDAO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Albert Sukaný
 */

@Repository
@Transactional
public class TourDAOImpl implements TourDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Tour findById(Long id) {
        return entityManager.find(Tour.class,id);
    }

    @Override
    public List<Tour> findAllTours() {
        return entityManager.createQuery("SELECT t FROM tours t", Tour.class).getResultList();
    }

    @Override
    public Long create(Tour tour) {
        entityManager.persist(tour);
        return tour.getId();
    }

    @Override
    public Tour update(Tour tour) {
        entityManager.merge(tour);
        return tour;
    }

    @Override
    public void remove(Tour tour) {
        tour = entityManager.merge(tour);
        entityManager.remove(tour);
    }

    @Override
    public List<Tour> findByName(String name) {
        return entityManager.createQuery("SELECT t FROM tours t WHERE t.name = :name",Tour.class)
                .setParameter("name",name)
                .getResultList();
    }
}
