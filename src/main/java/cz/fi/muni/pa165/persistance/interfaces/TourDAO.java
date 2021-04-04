package cz.fi.muni.pa165.persistance.interfaces;

import cz.fi.muni.pa165.entities.Tour;

import java.util.List;

/**
 * @author Albert Sukaný
 */
public interface TourDAO {
    Tour getById(Long id);
    List<Tour> getAllTours();
    void create(Tour tour);
    void update(Tour tour);
    void remove(Tour tour);
    List<Tour> getByName(String name);

}
