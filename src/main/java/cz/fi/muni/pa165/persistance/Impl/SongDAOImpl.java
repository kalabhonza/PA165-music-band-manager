package cz.fi.muni.pa165.persistance.Impl;

import cz.fi.muni.pa165.entities.Band;
import cz.fi.muni.pa165.entities.Song;
import cz.fi.muni.pa165.persistance.interfaces.SongDAO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Igor Ignác
 */
@Repository
@Transactional
public class SongDAOImpl implements SongDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Song song) {
        em.persist(song);
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
    public Song findSongByName(String name) {
        return em.createQuery("select s from Song s where s.name = :name", Song.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public Song findSongByBand(Band band) {
        return em.createQuery("select s from Song s where s.band = :band", Song.class)
                .setParameter("band", band)
                .getSingleResult();
    }

    @Override
    public List<Song> findAll() {
        return em.createQuery("select s from Song s", Song.class).getResultList();
    }

    @Override
    public void deleteSong(Song song) {
        song = em.merge(song);
        em.remove(song);
    }
}
