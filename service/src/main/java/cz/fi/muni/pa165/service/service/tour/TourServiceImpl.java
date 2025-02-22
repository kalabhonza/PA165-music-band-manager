package cz.fi.muni.pa165.service.service.tour;


import cz.fi.muni.pa165.entities.Tour;
import cz.fi.muni.pa165.persistence.interfaces.TourDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourServiceImpl implements  TourService{

    private TourDAO tourDAO;

    @Autowired
    public TourServiceImpl(TourDAO tourDAO){
        this.tourDAO = tourDAO;
    }

    @Override
    public Tour findById(Long id) {
        Tour tour = tourDAO.findById(id);
        if (tour == null) {
            throw new DataAccessException("Tour with id: " + id + "not found") {};
        }
        return tour;
    }

    @Override
    public List<Tour> findAllTours() {
        return tourDAO.findAllTours();
    }

    @Override
    public Long create(Tour tour) {
        return tourDAO.create(tour);
    }

    @Override
    public Tour update(Tour tour) {
        Tour updatedTour = tourDAO.update(tour);
        if (updatedTour == null) {
            throw new DataAccessException("Tour with id: " + tour.getId() + "does not exist") {};
        }
        if(!updatedTour.equals(tour)){
            throw new DataAccessException("Tour with id: " + tour.getId() + "was not updated"){};
        }
        return updatedTour;
    }

    @Override
    public void remove(Tour tour) {
        tourDAO.remove(tour);
        if (tourDAO.findById(tour.getId()) != null){
            throw new DataAccessException("Tour with id: " + tour.getId() + "was not deleted"){};
        }
    }

    @Override
    public List<Tour> findByName(String name) {
        List<Tour> tours = tourDAO.findByName(name);
        if (tours == null){
            throw new DataAccessException("Tour with name: " + name + "not found") {};
        }
        return tours;
    }
}
