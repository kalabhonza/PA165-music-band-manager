package cz.fi.muni.pa165.persistence.impl;

import cz.fi.muni.pa165.entities.Song;
import cz.fi.muni.pa165.persistence.interfaces.SongDAO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Igor Ignác
 */
@Repository
public class SongDAOImpl implements SongDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Long create(Song song) {
        em.persist(song);
        return song.getId();
    }

    @Override
    public Song update(Song song) {
        return em.merge(song);
    }

    @Override
    public Song findSongById(Long id) {
        return em.find(Song.class, id);
    }

    @Override
    public List<Song> findSongByName(String name) {
        return em.createQuery("SELECT s FROM songs s WHERE s.name = :name", Song.class)
                .setParameter("name", name)
                .getResultList();
    }

    @Override
    public List<Song> findAll() {
        return em.createQuery("SELECT s FROM songs s", Song.class).getResultList();
    }

    @Override
    public void deleteSong(Song song) {
        song = em.merge(song);
        em.remove(song);
    }
}
