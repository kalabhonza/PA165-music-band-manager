package cz.fi.muni.pa165.persistence.impl;

import cz.fi.muni.pa165.entities.Band;
import cz.fi.muni.pa165.entities.Musician;
import cz.fi.muni.pa165.persistence.interfaces.MusicianDAO;
import org.springframework.stereotype.Repository;

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
        return em.createQuery("SELECT m FROM musicians m WHERE m.username = :username", Musician.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    public List<Musician> findAll() {
        return em.createQuery("SELECT m FROM musicians m", Musician.class).getResultList();
    }

    public List<Musician> findAllByBand(Long bandId) {
        return em.createQuery("SELECT m FROM musicians m WHERE m.bandId = :bandId", Musician.class)
                .setParameter("bandId", bandId)
                .getResultList();
    }

    @Override
    public List<Musician> findAllWithoutBand() {
        return em.createQuery("SELECT m FROM musicians m WHERE m.bandId = null", Musician.class)
                .getResultList();
    }

    public Long create(Musician musician) {
        em.persist(musician);
        return musician.getId();
    }

    public Musician update(Musician musician) {
        return em.merge(musician);
    }

    public void remove(Musician musician) {
        em.remove(musician);
    }
}
