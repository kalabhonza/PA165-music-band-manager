package cz.fi.muni.pa165.service.facade;

import cz.fi.muni.pa165.api.dto.ConcertDTO;
import cz.fi.muni.pa165.api.facade.ConcertFacade;
import cz.fi.muni.pa165.entities.Concert;
import cz.fi.muni.pa165.service.ConcertService;
import cz.fi.muni.pa165.service.mapping.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

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
    public void create(ConcertDTO concert) {
        Concert newConcert = beanMapper.mapTo(concert, Concert.class);
        concertService.create(newConcert);
    }

    @Override
    public void setName(ConcertDTO concert, String name) {
        concertService.setName(beanMapper.mapTo(concert, Concert.class), name);

    }

    @Override
    public void setDate(ConcertDTO concert, LocalDate date) {
        concertService.setDate(beanMapper.mapTo(concert, Concert.class), date);
    }

    @Override
    public void remove(ConcertDTO concert) {
        concertService.remove(beanMapper.mapTo(concert, Concert.class));
    }
}
