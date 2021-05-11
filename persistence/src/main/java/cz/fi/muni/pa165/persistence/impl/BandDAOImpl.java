package cz.fi.muni.pa165.persistence.impl;

import cz.fi.muni.pa165.entities.Band;
import cz.fi.muni.pa165.entities.Manager;
import cz.fi.muni.pa165.persistence.interfaces.BandDAO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Igor Ignác
 */
@Repository
public class BandDAOImpl implements BandDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Long create(Band band) {
        em.persist(band);
        return band.getId();
    }

    @Override
    public Band update(Band band) {
        return em.merge(band);
    }

    @Override
    public Band findBandById(Long id) {
        return em.find(Band.class, id);
    }

    @Override
    public List<Band> findBandByName(String name) {
        return em.createQuery("SELECT b FROM bands b WHERE b.name = :name", Band.class)
                .setParameter("name", name)
                .getResultList();
    }

    @Override
    public Band findBandByManager(Manager manager) {
        return em.createQuery("SELECT b FROM bands b WHERE b.manager = :manager", Band.class)
                .setParameter("manager", manager)
                .getSingleResult();
    }

    @Override
    public List<Band> findAll() {
        return em.createQuery("SELECT b FROM bands b", Band.class).getResultList();
    }

    @Override
    public void deleteBand(Band band) {
        band = em.merge(band);
        em.remove(band);
    }
}
