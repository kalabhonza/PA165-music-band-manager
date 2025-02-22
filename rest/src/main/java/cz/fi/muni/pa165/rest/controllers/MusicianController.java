package cz.fi.muni.pa165.rest.controllers;

import cz.fi.muni.pa165.api.dto.band.BandDTO;
import cz.fi.muni.pa165.api.dto.musician.MusicianCreateDTO;
import cz.fi.muni.pa165.api.dto.musician.MusicianDTO;
import cz.fi.muni.pa165.api.dto.musician.MusicianUpdateDTO;
import cz.fi.muni.pa165.api.facade.BandFacade;
import cz.fi.muni.pa165.api.facade.MusicianFacade;
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
@CrossOrigin()
@RestController
@RequestMapping("/rest/musicians")
public class MusicianController {
    private MusicianFacade musicianFacade;
    private BandFacade bandFacade;

    @Autowired
    public MusicianController(MusicianFacade musicianFacade, BandFacade bandFacade) {
        this.musicianFacade = musicianFacade;
        this.bandFacade = bandFacade;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MusicianDTO>> getAll(){
        try {
            return ResponseEntity.ok(musicianFacade.findAll());
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(value = "/free", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MusicianDTO>> getAllWithoutBand(){
        try {
            return ResponseEntity.ok(musicianFacade.findAllWithoutBand());
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ResponseEntity<MusicianDTO> getById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(musicianFacade.findById(id));
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> createMusician(@RequestBody @Valid MusicianCreateDTO musicianCreateDTO){
        try {
            return ResponseEntity.ok(musicianFacade.create(musicianCreateDTO));
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteMusician(@RequestBody @Valid MusicianDTO musicianDTO){
        try {
            musicianFacade.remove(musicianDTO);
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, ex.getMessage(), ex);
        }
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateMusician(@RequestBody @Valid MusicianUpdateDTO musicianUpdateDTO){
        try {
            musicianFacade.update(musicianUpdateDTO);
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping(value = "/{musicianID}/offers/{bandID}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseEntity<MusicianDTO> acceptOffer(@PathVariable Long musicianID, @PathVariable Long bandID){
        try {
            MusicianDTO musician = musicianFacade.findById(musicianID);
            BandDTO band = bandFacade.findBandById(bandID);
            return ResponseEntity.ok(musicianFacade.acceptOffer(musician, band));
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping(value = "/{musicianID}/offers/{bandID}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseEntity<Void> declineOffer(@PathVariable Long musicianID, @PathVariable Long bandID) {
        try {
            MusicianDTO musician = musicianFacade.findById(musicianID);
            BandDTO band = bandFacade.findBandById(bandID);
            musicianFacade.declineOffer(musician, band);
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
        return ResponseEntity.noContent().build();
    }

}
