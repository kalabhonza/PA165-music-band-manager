package cz.fi.muni.pa165.rest.controllers;

import cz.fi.muni.pa165.api.dto.band.BandCreateDTO;
import cz.fi.muni.pa165.api.dto.band.BandDTO;
import cz.fi.muni.pa165.api.dto.band.BandUpdateDTO;
import cz.fi.muni.pa165.api.dto.manager.ManagerDTO;
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
import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rest/bands")

public class BandController {

    private BandFacade bandFacade;
    private ManagerFacade managerFacade;

    @Autowired
    public BandController(BandFacade bandFacade, ManagerFacade managerFacade) {
        this.bandFacade = bandFacade;
        this.managerFacade = managerFacade;
    }

    @RolesAllowed({"ROLE_USER"})
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BandDTO>> getAll(){
        try {
            return ResponseEntity.ok(bandFacade.findAllBands());
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }

    @RolesAllowed({"ROLE_USER"})
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BandDTO> getById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(bandFacade.findBandById(id));
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }


    @RolesAllowed({"ROLE_USER"})
    @GetMapping(value = "/{id}/managers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BandDTO> getByManager(@RequestBody @Valid ManagerDTO managerDTO){
        try {
            return ResponseEntity.ok(bandFacade.findBandByManager(managerDTO));
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }

    @RolesAllowed("ROLE_ADMIN")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> createBand(@RequestBody @Valid BandCreateDTO bandCreateDTO){
        try {
            Long bandId = bandFacade.createBand(bandCreateDTO);
            managerFacade.setManagerBand(bandCreateDTO.getManager(), bandId);
            return ResponseEntity.ok(bandId);
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }

    @RolesAllowed("ROLE_ADMIN")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteBand(@RequestBody @Valid BandDTO bandDTO){
        try {
            bandFacade.deleteBand(bandDTO);
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, ex.getMessage(), ex);
        }
        return ResponseEntity.noContent().build();
    }

    @RolesAllowed("ROLE_ADMIN")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BandDTO> updateBand(@RequestBody @Valid BandUpdateDTO bandUpdateDTO){
        try {
            return ResponseEntity.ok(bandFacade.updateBand(bandUpdateDTO));
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, ex.getMessage(), ex);
        }
    }
}
