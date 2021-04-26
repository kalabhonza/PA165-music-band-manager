package cz.fi.muni.pa165.api.facade;

import cz.fi.muni.pa165.api.dto.TourDTO;
import cz.fi.muni.pa165.entities.Tour;

import java.util.List;

public interface TourFacade {
    /**
     * Finds Tour by id
     * @param id unique identificator
     * @return Tour or {@code null}, if there is no Tour with requested id
     */
    TourDTO findById(Long id);

    /**
     * Find all existing tours
     * @return list of all tours
     */
    List<TourDTO> findAllTours();

    /**
     * Create a new tour
     * @param tour to be created
     */
    void create(TourDTO tour);

    /**
     * update existing tour
     * @param tour to be updated
     */
    TourDTO update(TourDTO tour);

    /**
     * Delete given tour
     * @param tour to be deleted
     */
    void remove(TourDTO tour);

    /**
     * Finds all tours with given name
     * @param name of the tours to be found
     * @return list of all tours having the given name
     */
    List<TourDTO> findByName(String name);
}
