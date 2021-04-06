package cz.fi.muni.pa165.persistance.Impl;

import cz.fi.muni.pa165.entities.Band;
import cz.fi.muni.pa165.entities.Manager;
import cz.fi.muni.pa165.persistance.interfaces.BandDAO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
    public void create(Band band) {
        em.persist(band);
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
        return em.createQuery("select b from bands b where b.name = :name", Band.class)
                .setParameter("name", name)
                .getResultList();
    }

    @Override
    public Band findBandByManager(Manager manager) {
        return em.createQuery("select b from bands b where b.manager = :manager", Band.class)
                .setParameter("manager", manager)
                .getSingleResult();
    }

    @Override
    public List<Band> findAll() {
        return em.createQuery("select b from bands b", Band.class).getResultList();
    }

    @Override
    public void deleteBand(Band band) {
        band = em.merge(band);
        em.remove(band);
    }
}
