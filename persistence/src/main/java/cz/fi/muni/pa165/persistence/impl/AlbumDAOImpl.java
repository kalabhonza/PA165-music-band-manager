package cz.fi.muni.pa165.persistence.impl;

import cz.fi.muni.pa165.entities.Album;
import cz.fi.muni.pa165.persistence.interfaces.AlbumDAO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Albert Sukaný
 */

@Repository
public class AlbumDAOImpl implements AlbumDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Album findById(Long id) {
        return entityManager.find(Album.class,id);
    }

    @Override
    public List<Album> findAllAlbums() {
        return entityManager.createQuery("SELECT a FROM albums a", Album.class).getResultList();
    }

    @Override
    public Long create(Album album) {
        entityManager.persist(album);
        return album.getId();
    }

    @Override
    public Album update(Album album) {
        entityManager.merge(album);
        return album;
    }

    @Override
    public void remove(Album album) {
        album = entityManager.merge(album);
        entityManager.remove(album);
    }

    @Override
    public List<Album> findAlbumsByName(String name) {
        return entityManager.createQuery("SELECT a FROM albums a WHERE a.name = :name",Album.class)
                .setParameter("name", name)
                .getResultList();
    }
}
