package cz.fi.muni.pa165.service.facade;

import cz.fi.muni.pa165.api.dto.TourDTO;
import cz.fi.muni.pa165.api.facade.TourFacade;
import cz.fi.muni.pa165.entities.Tour;
import cz.fi.muni.pa165.service.TourService;
import cz.fi.muni.pa165.service.mapping.modelmapper.BeanMapper;

import java.util.List;

public class TourFacadeImpl implements TourFacade {

    private TourService tourService;
    private BeanMapper beanMapper;

    @Override
    public TourDTO findById(Long id) {
        Tour tour = tourService.findById(id);
        return beanMapper.mapTo(tour,TourDTO.class);
    }

    @Override
    public List<TourDTO> findAllTours() {
        List<Tour> tours = tourService.findAllTours();
        return beanMapper.mapTo(tours,TourDTO.class);
    }

    @Override
    public void create(TourDTO tour) {
        Tour createdTour = beanMapper.mapTo(tour,Tour.class);
        this.tourService.create(createdTour);

    }

    @Override
    public TourDTO update(TourDTO tour) {
        Tour updatedTour = beanMapper.mapTo(tour,Tour.class);
        updatedTour = tourService.update(updatedTour);
        return beanMapper.mapTo(updatedTour,TourDTO.class);
    }

    @Override
    public void remove(TourDTO tour) {
        Tour deletedTour = beanMapper.mapTo(tour,Tour.class);
        tourService.remove(deletedTour);
    }

    @Override
    public List<TourDTO> findByName(String name) {
        List<Tour> tours = tourService.findByName(name);
        return beanMapper.mapTo(tours,TourDTO.class);
    }
}
