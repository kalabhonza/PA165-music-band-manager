package cz.fi.muni.pa165.rest.controllers;

import cz.fi.muni.pa165.api.dto.band.BandCreateDTO;
import cz.fi.muni.pa165.api.dto.band.BandDTO;
import cz.fi.muni.pa165.api.dto.band.BandUpdateDTO;
import cz.fi.muni.pa165.api.dto.manager.ManagerDTO;
import cz.fi.muni.pa165.api.facade.BandFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rest/bands")

public class BandController {

    private BandFacade bandFacade;

    @Autowired
    public BandController(BandFacade bandFacade) {
        this.bandFacade = bandFacade;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BandDTO>> getAll(){
        try {
            return ResponseEntity.ok(bandFacade.findAllBands());
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BandDTO> getById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(bandFacade.findBandById(id));
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }

//    @GetMapping(value = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<EntityModel<BandDTO>> getByName(@PathVariable String name){
//        List<BandDTO> bands = bandFacade.findBandByName(name);
//        List<EntityModel<BandDTO>> bandsResource = new ArrayList<>();
//        for (BandDTO bandDTO : bands){
//            bandsResource.add(bandResourceAssembler.toModel(bandDTO));
//        }
//        CollectionModel<EntityModel<BandDTO>> resultResources = new CollectionModel<>(bandsResource);
//        resultResources.add(linkTo(BandController.class).withSelfRel().withType("GET"));
//        return new ResponseEntity<>(resultResources, HttpStatus.OK);
//    }

    @GetMapping(value = "/{id}/managers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BandDTO> getByManager(@RequestBody @Valid ManagerDTO managerDTO){
        try {
            return ResponseEntity.ok(bandFacade.findBandByManager(managerDTO));
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> createBand(@RequestBody @Valid BandCreateDTO bandCreateDTO){
        try {
            return ResponseEntity.ok(bandFacade.createBand(bandCreateDTO));
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteBand(@RequestBody @Valid BandDTO bandDTO){
        try {
            bandFacade.deleteBand(bandDTO);;
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, ex.getMessage(), ex);
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateBand(@RequestBody @Valid BandUpdateDTO bandUpdateDTO){
        try {
            bandFacade.updateBand(bandUpdateDTO);;
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, ex.getMessage(), ex);
        }
        return ResponseEntity.noContent().build();
    }
}
