package cz.fi.muni.pa165.persistence.impl;

import cz.fi.muni.pa165.entities.Manager;
import cz.fi.muni.pa165.persistence.interfaces.ManagerDAO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Jan Kaláb
 */
@Repository
public class ManagerDAOImpl implements ManagerDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Manager findById(long id) {
        return entityManager.find(Manager.class, id);
    }

    @Override
    public List<Manager> findAll() {
        return entityManager.createQuery("SELECT m FROM managers m", Manager.class).getResultList();
    }

    @Override
    public Long create(Manager manager) {
        entityManager.persist(manager);
        return manager.getId();
    }

    @Override
    public Manager update(Manager manager) {
        return entityManager.merge(manager);
    }

    @Override
    public void remove(Manager manager) {
        entityManager.remove(manager);
    }

    @Override
    public Manager findByUserName(String userName) {
        return entityManager.createQuery("SELECT m FROM managers m WHERE m.userName = :userName", Manager.class)
                .setParameter("userName", userName)
                .getSingleResult();
    }

    @Override
    public List<Manager> findByName(String name) {
        return entityManager.createQuery("SELECT m FROM managers m WHERE m.name = :name", Manager.class)
                .setParameter("name", name)
                .getResultList();
    }
}
