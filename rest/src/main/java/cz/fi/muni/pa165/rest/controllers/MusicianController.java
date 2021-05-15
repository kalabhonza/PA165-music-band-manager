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
    public ResponseEntity<Resources<Resource<MusicianDTO>>> getAll(){
        try {
            List<MusicianDTO> musicians = musicianFacade.findAll();
            List<Resource<MusicianDTO>> musiciansResource = new ArrayList<>();
            for (MusicianDTO musicianDTO : musicians){
                musiciansResource.add(musicianResourceAssembler.toResource(musicianDTO));
            }
            Resources<Resource<MusicianDTO>> resultResources = new Resources<>(musiciansResource);
            resultResources.add(linkTo(MusicianController.class).withSelfRel().withType("GET"));
            return new ResponseEntity<>(resultResources, HttpStatus.OK);
        }catch (Exception ex){
            //throw ExceptionSorter.throwException(ex);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Resource<MusicianDTO>> getById(@PathVariable Long id){
        try {
            return new ResponseEntity<>(musicianResourceAssembler.toResource(musicianFacade.findById(id)), HttpStatus.OK);
        }catch (Exception ex){
            //throw ExceptionSorter.throwException(ex);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> createMusician(@RequestBody @Valid MusicianCreateDTO musicianCreateDTO){
        try{
            return new ResponseEntity<>(musicianFacade.create(musicianCreateDTO), HttpStatus.CREATED);
        }catch (Exception ex){
            //throw ExceptionSorter.throwException(ex);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteMusician(@RequestBody @Valid MusicianDTO musicianDTO){
        try {
            musicianFacade.remove(musicianDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex){
            //throw ExceptionSorter.throwException(ex);
        }
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateMusician(@RequestBody @Valid MusicianUpdateDTO musicianUpdateDTO){
        try {
            musicianFacade.update(musicianUpdateDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception ex){
            //throw ExceptionSorter.throwException(ex);
        }
    }
}
