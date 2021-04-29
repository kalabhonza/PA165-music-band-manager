package cz.fi.muni.pa165.service.facade;

import cz.fi.muni.pa165.api.dto.band.BandDTO;
import cz.fi.muni.pa165.api.dto.musician.MusicianCreateDTO;
import cz.fi.muni.pa165.api.dto.musician.MusicianDTO;
import cz.fi.muni.pa165.api.dto.musician.MusicianUpdateDTO;
import cz.fi.muni.pa165.api.facade.MusicianFacade;
import cz.fi.muni.pa165.entities.Musician;
import cz.fi.muni.pa165.service.MusicianService;
import cz.fi.muni.pa165.service.mapping.mapstruct.BandMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import cz.fi.muni.pa165.service.mapping.mapstruct.MusicianMapperImpl;

import java.util.List;

/**
 * @author Aleš Paroulek
 */
public class MusicianFacadeImpl implements MusicianFacade {
    private MusicianService musicianService;

    @Autowired
    private MusicianMapperImpl musicianMapper;

    @Autowired
    private BandMapperImpl bandMapper;

    @Autowired
    public MusicianFacadeImpl(MusicianService musicianService) {
        this.musicianService = musicianService;
    }


    @Override
    public MusicianDTO findById(long id) {
        Musician musician = musicianService.findById(id);
        return musicianMapper.mapToMusicianDTO(musician);
    }

    @Override
    public MusicianDTO findByUserName(String userName) {
        Musician musician = musicianService.findByUserName(userName);
        return musicianMapper.mapToMusicianDTO(musician);
    }

    @Override
    public List<MusicianDTO> findAll() {
        List<Musician> musicians = musicianService.findAll();
        return musicianMapper.mapToListDTO(musicians);
    }

    @Override
    public List<MusicianDTO> findAllByBand(BandDTO band) {
        List<Musician> musicians = musicianService.findAllByBand(bandMapper.mapToEntity(band));
        return musicianMapper.mapToListDTO(musicians);
    }

    @Override
    public Long create(MusicianCreateDTO musician) {
        return musicianService.create(musicianMapper.mapToEntity(musician));
    }

    @Override
    public MusicianDTO update(MusicianUpdateDTO musician) {
        Musician convertedMusician = musicianMapper.mapToEntity(musician);
        Musician updatedMusician = musicianService.update(convertedMusician);
        return musicianMapper.mapToMusicianDTO(updatedMusician);
    }

    @Override
    public void remove(MusicianDTO musician) {
        musicianService.remove(musicianMapper.mapToEntity(musician));
    }
}
