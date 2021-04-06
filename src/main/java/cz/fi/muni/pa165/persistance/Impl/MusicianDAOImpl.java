package cz.fi.muni.pa165.persistance.Impl;

import cz.fi.muni.pa165.entities.Band;
import cz.fi.muni.pa165.entities.Musician;
import cz.fi.muni.pa165.persistance.interfaces.MusicianDAO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Aleš Paroulek
 */
@Repository
public class MusicianDAOImpl implements MusicianDAO {

    @PersistenceContext
    private EntityManager em;

    public Musician findById(long id) {
        return em.find(Musician.class, id);
    }

    public Musician findByUserName(String username) {
        return em.createQuery("select m from musicians m where username = :username", Musician.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    public List<Musician> findAll() {
        return em.createQuery("select m from musicians m", Musician.class).getResultList();
    }

    public List<Musician> findAllByBand(Band band) {
        return em.createQuery("select m from musicians m where band = :band", Musician.class)
                .setParameter("band", band)
                .getResultList();
    }

    public void create(Musician musician) {
        em.persist(musician);
    }

    public void update(Musician musician) {
        em.merge(musician);
    }

    public void remove(Musician musician) {
        em.remove(musician);
    }
}
