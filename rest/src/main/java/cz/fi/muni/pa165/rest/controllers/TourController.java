package cz.fi.muni.pa165.rest.controllers;


import cz.fi.muni.pa165.api.dto.tour.TourCreateDTO;
import cz.fi.muni.pa165.api.dto.tour.TourDTO;
import cz.fi.muni.pa165.api.dto.tour.TourUpdateDTO;
import cz.fi.muni.pa165.api.facade.TourFacade;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rest/tours")
public class TourController {
    private TourFacade tourFacade;


    public TourController(TourFacade tourFacade) {
        this.tourFacade = tourFacade;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TourDTO>> getAll(){
        try {
            return ResponseEntity.ok(tourFacade.findAllTours());
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TourDTO> getById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(tourFacade.findById(id));
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> createTour(@RequestBody @Valid TourCreateDTO tourCreateDTO){
        try {
            return ResponseEntity.ok(tourFacade.create(tourCreateDTO));
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteTour(@RequestBody @Valid TourDTO tourDTO){
        try {
            tourFacade.remove(tourDTO);
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, ex.getMessage(), ex);
        }
        return ResponseEntity.noContent().build();
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateTour(@RequestBody @Valid TourUpdateDTO tourUpdateDTO){
        try {
            tourFacade.update(tourUpdateDTO);
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
        return ResponseEntity.noContent().build();
    }
}
