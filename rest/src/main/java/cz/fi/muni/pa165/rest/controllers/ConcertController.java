package cz.fi.muni.pa165.rest.controllers;

import cz.fi.muni.pa165.api.dto.concert.ConcertCreateDTO;
import cz.fi.muni.pa165.api.dto.concert.ConcertDTO;
import cz.fi.muni.pa165.api.dto.concert.ConcertUpdateDTO;
import cz.fi.muni.pa165.api.facade.ConcertFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Aleš Paroulek
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rest/concerts")
public class ConcertController {

    private ConcertFacade concertFacade;

    @Autowired
    public ConcertController(ConcertFacade concertFacade) {
        this.concertFacade = concertFacade;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ConcertDTO>> getAll(){
        try {
            return ResponseEntity.ok(concertFacade.findAll());
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConcertDTO> getById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(concertFacade.findById(id));
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> createConcert(@RequestBody @Valid ConcertCreateDTO concertCreateDTO){
        try {
            return ResponseEntity.ok(concertFacade.create(concertCreateDTO));
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteConcert(@RequestBody @Valid ConcertDTO concertDTO){
        try {
            concertFacade.remove(concertDTO);
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, ex.getMessage(), ex);
        }
        return ResponseEntity.noContent().build();

    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateConcert(@RequestBody @Valid ConcertUpdateDTO concertUpdateDTO){
        try {
            concertFacade.update(concertUpdateDTO);
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
        return ResponseEntity.noContent().build();
    }
}
