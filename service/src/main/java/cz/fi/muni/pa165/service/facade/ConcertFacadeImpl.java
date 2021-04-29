package cz.fi.muni.pa165.service.facade;

import cz.fi.muni.pa165.api.dto.concert.ConcertCreateDTO;
import cz.fi.muni.pa165.api.dto.concert.ConcertDTO;
import cz.fi.muni.pa165.api.dto.concert.ConcertUpdateDTO;
import cz.fi.muni.pa165.api.facade.ConcertFacade;
import cz.fi.muni.pa165.entities.Concert;
import cz.fi.muni.pa165.service.ConcertService;
import org.springframework.beans.factory.annotation.Autowired;
import cz.fi.muni.pa165.service.mapping.mapstruct.ConcertMapperImpl;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Aleš Paroulek
 */
public class ConcertFacadeImpl implements ConcertFacade {
    private ConcertService concertService;

    @Autowired
    private ConcertMapperImpl concertMapper;

    @Autowired
    public ConcertFacadeImpl(ConcertService concertService) {
        this.concertService = concertService;
    }

    @Override
    public ConcertDTO findById(long id) {
        Concert concert = concertService.findById(id);
        return concertMapper.mapToConcertDTO(concert);
    }

    @Override
    public List<ConcertDTO> findAll() {
        List<Concert> concerts = concertService.findAll();
        return concertMapper.mapToListDTO(concerts);
    }

    @Override
    public List<ConcertDTO> findAllByDate(LocalDate date) {
        List<Concert> concerts = concertService.findAllByDate(date);
        return concertMapper.mapToListDTO(concerts);
    }

    @Override
    public Long create(ConcertCreateDTO concert) {
        return concertService.create(concertMapper.mapToEntity(concert));
    }

    @Override
    public ConcertDTO update(ConcertUpdateDTO concert) {
        Concert convertedConcert = concertMapper.mapToEntity(concert);
        Concert updatedConcert = concertService.update(convertedConcert);
        return concertMapper.mapToConcertDTO(updatedConcert);
    }

    @Override
    public void remove(ConcertDTO concert) {
        concertService.remove(concertMapper.mapToEntity(concert));
    }
}
