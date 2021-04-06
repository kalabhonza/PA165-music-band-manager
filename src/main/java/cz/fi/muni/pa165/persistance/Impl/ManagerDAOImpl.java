package cz.fi.muni.pa165.persistance.Impl;

import cz.fi.muni.pa165.entities.Manager;
import cz.fi.muni.pa165.persistance.interfaces.ManagerDAO;
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
        return entityManager.createQuery("select m from managers m", Manager.class).getResultList();
    }

    @Override
    public void create(Manager manager) {
        entityManager.persist(manager);
    }

    @Override
    public void update(Manager manager) {
        entityManager.merge(manager);
    }

    @Override
    public void remove(Manager manager) {
        entityManager.remove(manager);
    }

    @Override
    public Manager findByUserName(String userName) {
        return entityManager.createQuery("select m from managers m where m.userName = :userName", Manager.class)
                .setParameter("userName", userName)
                .getSingleResult();
    }

    @Override
    public List<Manager> findByName(String name) {
        return entityManager.createQuery("select m from managers m where m.name = :name", Manager.class)
                .setParameter("name", name)
                .getResultList();
    }
}
