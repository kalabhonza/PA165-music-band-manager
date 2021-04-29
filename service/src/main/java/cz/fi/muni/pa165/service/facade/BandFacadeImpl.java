package cz.fi.muni.pa165.service.facade;

import cz.fi.muni.pa165.api.dto.band.BandCreateDTO;
import cz.fi.muni.pa165.api.dto.band.BandDTO;
import cz.fi.muni.pa165.api.dto.ManagerDTO;
import cz.fi.muni.pa165.api.dto.band.BandUpdateDTO;
import cz.fi.muni.pa165.api.facade.BandFacade;
import cz.fi.muni.pa165.entities.Band;
import cz.fi.muni.pa165.entities.Manager;
import cz.fi.muni.pa165.service.BandService;
import cz.fi.muni.pa165.service.mapping.modelmapper.BeanMapper;
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
    public Long createBand(BandCreateDTO band) {
        Band createdBand = beanMapper.mapTo(band, Band.class);
        return this.bandService.createBand(createdBand);
    }

    @Override
    public BandDTO updateBand(BandUpdateDTO band) {
        Band updateBand = beanMapper.mapTo(band, Band.class);
        updateBand = bandService.updateBand(updateBand);
        return beanMapper.mapTo(updateBand, BandDTO.class);
    }

    @Override
    public BandDTO findBandById(Long id) {
        Band band = bandService.findBandById(id);
        return beanMapper.mapTo(band, BandDTO.class);
    }

    @Override
    public List<BandDTO> findBandByName(String name) {
        List<Band> band = bandService.findBandByName(name);
        return beanMapper.mapTo(band, BandDTO.class);
    }

    @Override
    public BandDTO findBandByManager(ManagerDTO manager) {
        Manager bandMananager = beanMapper.mapTo(manager, Manager.class);
        Band band = bandService.findBandByManager(bandMananager);
        return beanMapper.mapTo(band, BandDTO.class);
    }

    @Override
    public List<BandDTO> findAllBands() {
        List<Band> band = bandService.findAllBands();
        return beanMapper.mapTo(band, BandDTO.class);
    }

    @Override
    public void deleteBand(BandDTO band) {
        Band deleteBand = beanMapper.mapTo(band, Band.class);
        bandService.deleteBand(deleteBand);
    }
}
