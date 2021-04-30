package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.entities.Band;
import cz.fi.muni.pa165.entities.Manager;
import cz.fi.muni.pa165.persistance.interfaces.BandDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author Igor Ignác
 */
@Service
public class BandServiceImpl implements BandService {

    private BandDAO bandDAO;

    @Autowired
    public BandServiceImpl(BandDAO bandDAO) {
        this.bandDAO = bandDAO;
    }

    @Override
    public Long createBand(Band band) {
        return bandDAO.create(band);
    }

    @Override
    public Band updateBand(Band band) {
        Band updatedBand = bandDAO.update(band);
        if (updatedBand == null) {
            throw new DataAccessException("Band with id: " + band.getId() + "does not exist") {};
        }
        if (!updatedBand.equals(band)){
            throw new DataAccessException("Band with id: " + band.getId() + " was not updated"){};
        }
        return updatedBand;
    }

    @Override
    public Band findBandById(Long id) {
        Band band = bandDAO.findBandById(id);
        if (band == null)
            throw new DataAccessException("Band with id: " + id + "not found") {};
        return band;
    }

    @Override
    public List<Band> findBandByName(String name) {
        List<Band> band = bandDAO.findBandByName(name);
        if (band == null) {
            throw new DataAccessException("Band with name: " + name + "not found") {};
        }
        return band;
    }

    @Override
    public Band findBandByManager(Manager manager) {
        Band band = bandDAO.findBandByManager(manager);
        if (band == null)
            throw new DataAccessException("Band with manager id: " + manager.getId() + "not found") {};
        return band;
    }

    @Override
    public List<Band> findAllBands() { return bandDAO.findAll(); }

    @Override
    public void deleteBand(Band band) {
        bandDAO.deleteBand(band);
        if (bandDAO.findBandById(band.getId()) != null) {
            throw new DataAccessException("Band with id: " + band.getId() + "was not deleted") {};
        }
    }
}
