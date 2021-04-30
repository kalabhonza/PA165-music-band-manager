package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.entities.Band;
import cz.fi.muni.pa165.entities.Concert;
import cz.fi.muni.pa165.persistance.interfaces.ConcertDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Aleš Paroulek
 */
@Service
public class ConcertServiceImpl implements ConcertService {
    private ConcertDAO concertDAO;

    @Autowired
    public ConcertServiceImpl(ConcertDAO concertDAO) {
        this.concertDAO = concertDAO;
    }

    @Override
    public Concert findById(long id) {
        Concert concert = concertDAO.findById(id);
        if (concert == null) {
            throw new DataAccessException("Concert with id " + id + " was not found.") {};
        }
        return concert;
    }

    @Override
    public List<Concert> findAll() {
        return concertDAO.findAll();
    }

    @Override
    public List<Concert> findAllByDate(LocalDate date) {
        return concertDAO.findAllByDate(date);
    }

    @Override
    public Long create(Concert concert) {
        return concertDAO.create(concert);
    }

    @Override
    public Concert update(Concert concert) {
        Concert updatedConcert = concertDAO.update(concert);
        if (updatedConcert == null) {
            throw new DataAccessException("Concert with id: " + concert.getId() + " does not exist") {};
        }
        if (!updatedConcert.equals(concert)) {
            throw new DataAccessException("Updating of concert with id: " + concert.getId() + " failed") {};
        }
        return updatedConcert;
    }

    @Override
    public void remove(Concert concert) {
        concertDAO.remove(concert);
        if (concertDAO.findById(concert.getId()) != null) {
            throw new DataAccessException("Removing of concert with id: " + concert.getId() + " failed") {};
        }
    }

    @Override
    public List<Concert> findByName(String name) {
        return concertDAO.findByName(name);
    }
}
