package cz.fi.muni.pa165.service.facade;

import cz.fi.muni.pa165.api.dto.band.BandDTO;
import cz.fi.muni.pa165.api.dto.MusicianCreateDTO;
import cz.fi.muni.pa165.api.dto.MusicianDTO;
import cz.fi.muni.pa165.api.facade.MusicianFacade;
import cz.fi.muni.pa165.entities.Band;
import cz.fi.muni.pa165.entities.Musician;
import cz.fi.muni.pa165.enums.Instrument;
import cz.fi.muni.pa165.service.MusicianService;
import cz.fi.muni.pa165.service.mapping.modelmapper.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Aleš Paroulek
 */
public class MusicianFacadeImpl implements MusicianFacade {
    private MusicianService musicianService;
    private BeanMapper beanMapper;

    @Autowired
    public MusicianFacadeImpl(MusicianService musicianService, BeanMapper beanMapper) {
        this.musicianService = musicianService;
        this.beanMapper = beanMapper;
    }


    @Override
    public MusicianDTO findById(long id) {
        Musician musician = musicianService.findById(id);
        return beanMapper.mapTo(musician, MusicianDTO.class);
    }

    @Override
    public MusicianDTO findByUserName(String userName) {
        Musician musician = musicianService.findByUserName(userName);
        return beanMapper.mapTo(musician, MusicianDTO.class);
    }

    @Override
    public List<MusicianDTO> findAll() {
        List<Musician> musicians = musicianService.findAll();
        return beanMapper.mapTo(musicians, MusicianDTO.class);
    }

    @Override
    public List<MusicianDTO> findAllByBand(BandDTO band) {
        List<Musician> musicians = musicianService.findAllByBand(beanMapper.mapTo(band, Band.class));
        return beanMapper.mapTo(musicians, MusicianDTO.class);
    }

    @Override
    public Long create(MusicianCreateDTO musician) {
        return musicianService.create(beanMapper.mapTo(musician, Musician.class));
    }

    @Override
    public MusicianDTO update(MusicianDTO musician) {
        Musician convertedMusician = beanMapper.mapTo(musician, Musician.class);
        Musician updatedMusician = musicianService.update(convertedMusician);
        return beanMapper.mapTo(updatedMusician, MusicianDTO.class);
    }

    @Override
    public void remove(MusicianDTO musician) {
        musicianService.remove(beanMapper.mapTo(musician, Musician.class));
    }
}
