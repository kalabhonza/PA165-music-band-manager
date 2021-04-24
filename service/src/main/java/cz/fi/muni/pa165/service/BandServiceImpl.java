package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.entities.Band;
import cz.fi.muni.pa165.entities.Manager;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author Igor Ignac
 */
@Service
public class BandServiceImpl implements BandService {
    @Override
    public void createBand(Band band) {

    }

    @Override
    public Band updateBand(Band band) {
        return null;
    }

    @Override
    public Band findBandById(Long id) {
        return null;
    }

    @Override
    public List<Band> findBandByName(String name) {
        return null;
    }

    @Override
    public Band findBandByManager(Manager manager) {
        return null;
    }

    @Override
    public List<Band> findAllBands() {
        return null;
    }

    @Override
    public void deleteBand(Band band) {

    }
}
