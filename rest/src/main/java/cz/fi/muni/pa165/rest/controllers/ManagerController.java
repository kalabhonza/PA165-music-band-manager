package cz.fi.muni.pa165.rest.controllers;

import cz.fi.muni.pa165.api.dto.band.BandDTO;
import cz.fi.muni.pa165.api.dto.manager.ManagerDTO;
import cz.fi.muni.pa165.api.dto.manager.ManagerCreateDTO;
import cz.fi.muni.pa165.api.dto.manager.ManagerUpdateDTO;

import cz.fi.muni.pa165.api.dto.musician.MusicianDTO;
import cz.fi.muni.pa165.api.facade.BandFacade;
import cz.fi.muni.pa165.api.facade.ManagerFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Igor Ignác
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rest/managers")
public class ManagerController {
    private ManagerFacade managerFacade;
    private BandFacade bandFacade;

    @Autowired
    public ManagerController(ManagerFacade managerFacade, BandFacade bandFacade) {
        this.managerFacade = managerFacade;
        this.bandFacade = bandFacade;
    }

    @RolesAllowed("ROLE_ADMIN")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ManagerDTO>> getAll(){
        try {
            return ResponseEntity.ok(managerFacade.findAll());
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }

    @RolesAllowed("ROLE_ADMIN")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ManagerDTO> getById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(managerFacade.findById(id));
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }

    @RolesAllowed("ROLE_ADMIN")
    @GetMapping(value = "/{id}/bands", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BandDTO> getByBandId(@PathVariable Long id){
        try {
            return ResponseEntity.ok(bandFacade.findBandById(id));
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }

    @RolesAllowed("ROLE_ADMIN")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> createManager(@RequestBody @Valid ManagerCreateDTO managerCreateDTO){
        try {
            return ResponseEntity.ok(managerFacade.create(managerCreateDTO));
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }

    @RolesAllowed("ROLE_ADMIN")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteManager(@RequestBody @Valid ManagerDTO managerDTO){
        try {
            managerFacade.remove(managerDTO);
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, ex.getMessage(), ex);
        }
        return ResponseEntity.noContent().build();
    }

    @RolesAllowed("ROLE_ADMIN")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateManager(@RequestBody @Valid ManagerUpdateDTO managerUpdateDTO){
        try {
            managerFacade.update(managerUpdateDTO);
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, ex.getMessage(), ex);
        }
        return ResponseEntity.noContent().build();
    }

    @RolesAllowed("ROLE_ADMIN")
    @PostMapping(value = "/{bandId}/offers/{musicianId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ResponseEntity<MusicianDTO> setOffer(@PathVariable Long musicianId, @PathVariable Long bandId){
        try {
            managerFacade.setOffer(musicianId, bandId);
            return ResponseEntity.noContent().build();
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }
}