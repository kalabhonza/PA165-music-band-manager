package cz.fi.muni.pa165.rest.controllers;

import cz.fi.muni.pa165.api.dto.song.SongCreateDTO;
import cz.fi.muni.pa165.api.dto.song.SongDTO;
import cz.fi.muni.pa165.api.dto.song.SongUpdateDTO;
import cz.fi.muni.pa165.api.facade.SongFacade;
import cz.fi.muni.pa165.rest.assemblers.SongResourceAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rest/songs")
public class SongController {

    private SongFacade songFacade;
    private SongResourceAssembler songResourceAssembler;

    @Autowired
    public SongController(SongFacade songFacade, SongResourceAssembler songResourceAssembler){
        this.songFacade = songFacade;
        this.songResourceAssembler = songResourceAssembler;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Resources<Resource<SongDTO>>> getAll(){
        try {
            List<SongDTO> songs = songFacade.findAllSongs();
            List<Resource<SongDTO>> songsResource = new ArrayList<>();
            for (SongDTO songDTO : songs){
                songsResource.add(songResourceAssembler.toResource(songDTO));
            }
            Resources<Resource<SongDTO>> resultResources = new Resources<>(songsResource);
            resultResources.add(linkTo(SongController.class).withSelfRel().withType("GET"));
            return new ResponseEntity<>(resultResources, HttpStatus.OK);
        }catch (Exception ex){
            //throw ExceptionSorter.throwException(ex);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Resource<SongDTO>> getById(@PathVariable Long id){
        try {
            return new ResponseEntity<>(songResourceAssembler.toResource(songFacade.findSongById(id)), HttpStatus.OK);
        }catch (Exception ex){
            //throw ExceptionSorter.throwException(ex);
        }
    }

    @GetMapping(value = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Resource<SongDTO>> getByName(@PathVariable String name){
        try {
            List<SongDTO> songs = songFacade.findSongByName(name);
            List<Resource<SongDTO>> songsResource = new ArrayList<>();
            for (SongDTO songDTO : songs){
                songsResource.add(songResourceAssembler.toResource(songDTO));
            }
            Resources<Resource<SongDTO>> resultResources = new Resources<>(songsResource);
            resultResources.add(linkTo(SongController.class).withSelfRel().withType("GET"));
            return new ResponseEntity<>(resultResources, HttpStatus.OK);
        }catch (Exception ex){
            //throw ExceptionSorter.throwException(ex);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> createSong(@RequestBody @Valid SongCreateDTO songCreateDTO){
        try{
            return new ResponseEntity<>(songFacade.createSong(songCreateDTO), HttpStatus.CREATED);
        }catch (Exception ex){
            //throw ExceptionSorter.throwException(ex);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteSong(@RequestBody @Valid SongDTO songDTO){
        try {
            songFacade.deleteSong(songDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex){
            //throw ExceptionSorter.throwException(ex);
        }
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateSong(@RequestBody @Valid SongUpdateDTO songUpdateDTO){
        try {
            songFacade.updateSong(songUpdateDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception ex){
            //throw ExceptionSorter.throwException(ex);
        }
    }
