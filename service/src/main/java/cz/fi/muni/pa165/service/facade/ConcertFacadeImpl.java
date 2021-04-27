package cz.fi.muni.pa165.service.facade;

import cz.fi.muni.pa165.api.dto.ConcertCreateDTO;
import cz.fi.muni.pa165.api.dto.ConcertDTO;
import cz.fi.muni.pa165.api.facade.ConcertFacade;
import cz.fi.muni.pa165.entities.Concert;
import cz.fi.muni.pa165.service.ConcertService;
import cz.fi.muni.pa165.service.mapping.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Aleš Paroulek
 */
public class ConcertFacadeImpl implements ConcertFacade {
    private ConcertService concertService;
    private BeanMapper beanMapper;

    @Autowired
    public ConcertFacadeImpl(ConcertService concertService, BeanMapper beanMapper) {
        this.concertService = concertService;
        this.beanMapper = beanMapper;
    }

    @Override
    public ConcertDTO findById(long id) {
        Concert concert = concertService.findById(id);
        return beanMapper.mapTo(concert, ConcertDTO.class);
    }

    @Override
    public List<ConcertDTO> findAll() {
        List<Concert> concerts = concertService.findAll();
        return beanMapper.mapTo(concerts, ConcertDTO.class);
    }

    @Override
    public List<ConcertDTO> findAllByDate(LocalDate date) {
        List<Concert> concerts = concertService.findAllByDate(date);
        return beanMapper.mapTo(concerts, ConcertDTO.class);
    }

    @Override
    public Long create(ConcertCreateDTO concert) {
        return concertService.create(beanMapper.mapTo(concert, Concert.class));
    }

    @Override
    public ConcertDTO update(ConcertDTO concert) {
        Concert convertedConcert = beanMapper.mapTo(concert, Concert.class);
        Concert updatedConcert = concertService.update(convertedConcert);
        return beanMapper.mapTo(updatedConcert, ConcertDTO.class);
    }

    @Override
    public void remove(ConcertDTO concert) {
        concertService.remove(beanMapper.mapTo(concert, Concert.class));
    }
}
