package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.entities.Band;
import cz.fi.muni.pa165.entities.Musician;
import cz.fi.muni.pa165.enums.Instrument;
import cz.fi.muni.pa165.persistance.interfaces.MusicianDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;

/**
 * @author Aleš Paroulek
 */
@Service
public class MusicianServiceImpl implements MusicianService {
    private MusicianDAO musicianDAO;

    @Autowired
    public MusicianServiceImpl(MusicianDAO musicianDAO) {
        this.musicianDAO = musicianDAO;
    }

    @Override
    public Musician findById(long id) {
        Musician musician = musicianDAO.findById(id);
        if (musician == null) {
            throw new DataAccessException("Musician with id " + id + " was not found.") {};
        }
        return musician;
    }

    @Override
    public Musician findByUserName(String userName) {
        Musician musician = musicianDAO.findByUserName(userName);
        if (musician == null) {
            throw new DataAccessException("Musician with username " + userName + " was not found.") {};
        }
        return musician;
    }

    @Override
    public List<Musician> findAll() {
        return musicianDAO.findAll();
    }

    @Override
    public List<Musician> findAllByBand(Band band) {
        return musicianDAO.findAllByBand(band);
    }

    @Override
    public void create(Musician musician) {
        musicianDAO.create(musician);
    }

    @Override
    public void update(Musician musician) {
        musicianDAO.update(musician);
    }
    
    @Override
    public void remove(Musician musician) {
        musicianDAO.remove(musician);
    }
}
