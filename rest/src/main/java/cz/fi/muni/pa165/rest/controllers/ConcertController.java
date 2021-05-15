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
    public ResponseEntity<Resources<Resource<ConcertDTO>>> findAll(){
        try {
            List<ConcertDTO> concerts = concertFacade.findAll();
            List<Resource<ConcertDTO>> concertsResource = new ArrayList<>();
            for (ConcertDTO concertDTO : concerts){
                concertResource.add(concertResourceAssembler.toResource(concertDTO));
            }
            Resources<Resource<ConcertDTO>> resultResources = new Resources<>(concertsResource);
            resultResources.add(linkTo(ConcertController.class).withSelfRel().withType("GET"));
            return new ResponseEntity<>(resultResources, HttpStatus.OK);
        }catch (Exception ex){
            //throw ExceptionSorter.throwException(ex);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Resource<ConcertDTO>> findById(@PathVariable Long id){
        try {
            return new ResponseEntity<>(concertResourceAssembler.toResource(concertFacade.findById(id)), HttpStatus.OK);
        }catch (Exception ex){
            //throw ExceptionSorter.throwException(ex);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> createConcert(@RequestBody @Valid ConcertCreateDTO concertCreateDTO){
        try{
            return new ResponseEntity<>(concertFacade.create(concertCreateDTO), HttpStatus.CREATED);
        }catch (Exception ex){
            //throw ExceptionSorter.throwException(ex);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteConcert(@RequestBody @Valid ConcertDTO concertDTO){
        try {
            concertFacade.remove(concertDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex){
            //throw ExceptionSorter.throwException(ex);
        }
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateConcert(@RequestBody @Valid ConcertUpdateDTO concertUpdateDTO){
        try {
            concertFacade.update(concertUpdateDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception ex){
            //throw ExceptionSorter.throwException(ex);
        }
    }
}
