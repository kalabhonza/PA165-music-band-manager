package cz.fi.muni.pa165.persistence.interfaces;

import cz.fi.muni.pa165.entities.Tour;

import java.util.List;

/**
 * @author Albert Sukaný
 */
public interface TourDAO {
    /**
     * Finds Tour by id
     * @param id unique identifier
     * @return Tour or {@code null}, if there is no Tour with requested id
     */
    Tour findById(Long id);

    /**
     * Find all existing tours
     * @return list of all tours
     */
    List<Tour> findAllTours();

    /**
     * Create a new tour
     * @param tour to be created
     */
    Long create(Tour tour);

    /**
     * update existing tour
     * @param tour to be updated
     */
    Tour update(Tour tour);

    /**
     * Delete given tour
     * @param tour to be deleted
     */
    void remove(Tour tour);

    /**
     * Finds all tours with given name
     * @param name of the tours to be found
     * @return list of all tours having the given name
     */
    List<Tour> findByName(String name);

}
