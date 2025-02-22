package cz.fi.muni.pa165.persistence.impl;

import cz.fi.muni.pa165.entities.Concert;
import cz.fi.muni.pa165.persistence.interfaces.ConcertDAO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Aleš Paroulek
 */
@Repository
@Transactional
public class ConcertDAOImpl implements ConcertDAO {

    @PersistenceContext
    private EntityManager em;

    public Concert findById(long id) {
        return em.find(Concert.class, id);
    }

    public List<Concert> findAll() {
        return em.createQuery("SELECT c FROM concerts c", Concert.class).getResultList();
    }

    public List<Concert> findAllByDate(LocalDate date) {
        return em.createQuery("SELECT c FROM concerts c WHERE c.date = :date", Concert.class)
                .setParameter("date", date)
                .getResultList();
    }

    public Long create(Concert concert) {
        em.persist(concert);
        return concert.getId();
    }

    public Concert update(Concert concert) {
        return em.merge(concert);
    }

    public void remove(Concert concert) {
        em.remove(concert);
    }

    @Override
    public List<Concert> findByName(String name) {
        return em.createQuery("SELECT c FROM concerts c WHERE c.name = :name", Concert.class)
                .setParameter("name", name)
                .getResultList();
    }
}

