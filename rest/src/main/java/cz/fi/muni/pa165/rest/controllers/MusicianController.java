package cz.fi.muni.pa165.rest.controllers;


import cz.fi.muni.pa165.api.dto.musician.MusicianCreateDTO;
import cz.fi.muni.pa165.api.dto.musician.MusicianDTO;
import cz.fi.muni.pa165.api.dto.musician.MusicianUpdateDTO;
import cz.fi.muni.pa165.api.facade.MusicianFacade;
import cz.fi.muni.pa165.rest.assemblers.MusicianResourceAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Aleš Paroulek
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rest/musicians")
public class MusicianController {
    private MusicianFacade musicianFacade;
    private MusicianResourceAssembler musicianResourceAssembler;

    @Autowired
    public MusicianController(MusicianFacade musicianFacade, MusicianResourceAssembler musicianResourceAssembler) {
        this.musicianFacade = musicianFacade;
        this.musicianResourceAssembler = musicianResourceAssembler;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<MusicianDTO>>> getAll(){
        List<MusicianDTO> musicians = musicianFacade.findAll();
        List<EntityModel<MusicianDTO>> musiciansResource = new ArrayList<>();
        for (MusicianDTO musicianDTO : musicians){
            musiciansResource.add(musicianResourceAssembler.toModel(musicianDTO));
        }
        CollectionModel<EntityModel<MusicianDTO>> resultResources = new CollectionModel<>(musiciansResource);
        resultResources.add(linkTo(MusicianController.class).withSelfRel().withType("GET"));
        return new ResponseEntity<>(resultResources, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<MusicianDTO>> getById(@PathVariable Long id){
        return new ResponseEntity<>(musicianResourceAssembler.toModel(musicianFacade.findById(id)), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> createMusician(@RequestBody @Valid MusicianCreateDTO musicianCreateDTO){
        return new ResponseEntity<>(musicianFacade.create(musicianCreateDTO), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteMusician(@RequestBody @Valid MusicianDTO musicianDTO){
        musicianFacade.remove(musicianDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateMusician(@RequestBody @Valid MusicianUpdateDTO musicianUpdateDTO){
        musicianFacade.update(musicianUpdateDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
