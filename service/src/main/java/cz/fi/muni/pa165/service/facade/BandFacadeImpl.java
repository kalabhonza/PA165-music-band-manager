package cz.fi.muni.pa165.service.facade;

import cz.fi.muni.pa165.api.dto.band.BandCreateDTO;
import cz.fi.muni.pa165.api.dto.band.BandDTO;
import cz.fi.muni.pa165.api.dto.manager.ManagerDTO;
import cz.fi.muni.pa165.api.dto.band.BandUpdateDTO;
import cz.fi.muni.pa165.api.facade.BandFacade;
import cz.fi.muni.pa165.entities.Band;
import cz.fi.muni.pa165.entities.Manager;
import cz.fi.muni.pa165.service.BandService;
import cz.fi.muni.pa165.service.mapping.mapstruct.BandMapperImpl;
import cz.fi.muni.pa165.service.mapping.mapstruct.ManagerMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author Igor Ignác
 */
@Service
public class BandFacadeImpl implements BandFacade {

    private BandService bandService;
    private BandMapperImpl bandMapper;
    private ManagerMapperImpl managerMapper;

    @Autowired
    public BandFacadeImpl(BandService bandService, BandMapperImpl bandMapper, ManagerMapperImpl managerMapper) {
        this.bandService = bandService;
        this.bandMapper = bandMapper;
        this.managerMapper = managerMapper;
    }

    @Override
    public Long createBand(BandCreateDTO band) {
        Band createdBand = bandMapper.mapToEntity(band);
        return this.bandService.createBand(createdBand);
    }

    @Override
    public BandDTO updateBand(BandUpdateDTO band) {
        Band updateBand = bandMapper.mapToEntity(band);
        updateBand = bandService.updateBand(updateBand);
        return bandMapper.mapToBandDTO(updateBand);
    }

    @Override
    public BandDTO findBandById(Long id) {
        Band band = bandService.findBandById(id);
        return bandMapper.mapToBandDTO(band);
    }

    @Override
    public List<BandDTO> findBandByName(String name) {
        List<Band> band = bandService.findBandByName(name);
        return bandMapper.mapToListDTO(band);
    }

    @Override
    public BandDTO findBandByManager(ManagerDTO manager) {
        Manager bandMananager = managerMapper.mapToEntity(manager);
        Band band = bandService.findBandByManager(bandMananager);
        return bandMapper.mapToBandDTO(band);
    }

    @Override
    public List<BandDTO> findAllBands() {
        List<Band> band = bandService.findAllBands();
        return bandMapper.mapToListDTO(band);
    }

    @Override
    public void deleteBand(BandDTO band) {
        Band deleteBand = bandMapper.mapToEntity(band);
        bandService.deleteBand(deleteBand);
    }
}
