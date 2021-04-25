package cz.fi.muni.pa165.service.facade;

import cz.fi.muni.pa165.api.dto.BandDTO;
import cz.fi.muni.pa165.api.dto.MusicianDTO;
import cz.fi.muni.pa165.api.facade.MusicianFacade;
import cz.fi.muni.pa165.enums.Instrument;
import cz.fi.muni.pa165.service.MusicianService;
import cz.fi.muni.pa165.service.mapping.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
        return null;
    }

    @Override
    public MusicianDTO findByUserName(String userName) {
        return null;
    }

    @Override
    public List<MusicianDTO> findAll() {
        return null;
    }

    @Override
    public List<MusicianDTO> findAllByBand(BandDTO band) {
        return null;
    }

    @Override
    public void create(MusicianDTO musician) {

    }

    @Override
    public void setName(MusicianDTO musician, String name) {

    }

    @Override
    public void setUsername(MusicianDTO musician, String username) {

    }

    @Override
    public void setPassword(MusicianDTO musician, String password) {

    }

    @Override
    public void setInstruments(MusicianDTO musician, List<Instrument> instruments) {

    }

    @Override
    public void addOffer(MusicianDTO musician, BandDTO band) {

    }

    @Override
    public void removeOffer(MusicianDTO musician, BandDTO band) {

    }

    @Override
    public void acceptOffer(MusicianDTO musician, BandDTO band) {

    }

    @Override
    public void remove(MusicianDTO musician) {

    }
}
