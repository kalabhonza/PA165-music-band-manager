package cz.fi.muni.pa165.service.facade;

import cz.fi.muni.pa165.api.dto.BandDTO;
import cz.fi.muni.pa165.api.facade.BandFacade;
import cz.fi.muni.pa165.entities.Band;
import cz.fi.muni.pa165.service.BandService;
import cz.fi.muni.pa165.service.mapping.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author Igor Ignac
 */
@Service
public class BandFacadeImpl implements BandFacade {

    private BandService bandService;
    private BeanMapper beanMapper;

    @Autowired
    public BandFacadeImpl(BandService bandService, BeanMapper beanMapper) {
        this.bandService = bandService;
        this.beanMapper = beanMapper;
    }

    @Override
    public void createBand(BandDTO band) {
        Band createdBand = beanMapper.mapTo(band, Band.class);
        this.bandService.createBand(createdBand);
    }

    @Override
    public BandDTO updateBand(BandDTO band) {
        return null;
    }

    @Override
    public BandDTO findBandById(Long id) {
        return null;
    }

    @Override
    public List<BandDTO> findBandByName(String name) {
        return null;
    }

    @Override
    public BandDTO findBandByManager(ManagerDTO manager) {
        return null;
    }

    @Override
    public List<BandDTO> findAllBands() {
        return null;
    }

    @Override
    public void deleteBand(BandDTO band) {

    }
}
