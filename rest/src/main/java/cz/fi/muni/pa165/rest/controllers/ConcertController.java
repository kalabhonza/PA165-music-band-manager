package cz.fi.muni.pa165.rest.controllers;

import cz.fi.muni.pa165.api.dto.concert.ConcertCreateDTO;
import cz.fi.muni.pa165.api.dto.concert.ConcertDTO;
import cz.fi.muni.pa165.api.dto.concert.ConcertUpdateDTO;
import cz.fi.muni.pa165.api.facade.ConcertFacade;
import cz.fi.muni.pa165.rest.assemblers.ConcertResourceAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


/**
 * @author Aleš Paroulek
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rest/concerts")
public class ConcertController {

    private ConcertFacade concertFacade;
    private ConcertResourceAssembler concertResourceAssembler;

    @Autowired
    public ConcertController(ConcertFacade concertFacade, ConcertResourceAssembler concertResourceAssembler) {
        this.concertFacade = concertFacade;
        this.concertResourceAssembler = concertResourceAssembler;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<ConcertDTO>>> getAll(){
        List<ConcertDTO> concerts = concertFacade.findAll();
        List<EntityModel<ConcertDTO>> concertsResource = new ArrayList<>();
        for (ConcertDTO concertDTO : concerts){
            concertsResource.add(concertResourceAssembler.toModel(concertDTO));
        }
        CollectionModel<EntityModel<ConcertDTO>> resultResources = new CollectionModel<>(concertsResource);
        resultResources.add(linkTo(ConcertController.class).withSelfRel().withType("GET"));
        return new ResponseEntity<>(resultResources, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<ConcertDTO>> getById(@PathVariable Long id){
        return new ResponseEntity<>(concertResourceAssembler.toModel(concertFacade.findById(id)), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> createConcert(@RequestBody @Valid ConcertCreateDTO concertCreateDTO){
        return new ResponseEntity<>(concertFacade.create(concertCreateDTO), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteConcert(@RequestBody @Valid ConcertDTO concertDTO){
        concertFacade.remove(concertDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateConcert(@RequestBody @Valid ConcertUpdateDTO concertUpdateDTO){
        concertFacade.update(concertUpdateDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
