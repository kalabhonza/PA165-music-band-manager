package cz.fi.muni.pa165.rest.controllers;

import cz.fi.muni.pa165.api.dto.song.SongCreateDTO;
import cz.fi.muni.pa165.api.dto.song.SongDTO;
import cz.fi.muni.pa165.api.dto.song.SongUpdateDTO;
import cz.fi.muni.pa165.api.facade.SongFacade;
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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rest/songs")
public class SongController {

    private SongFacade songFacade;

    @Autowired
    public SongController(SongFacade songFacade) {
        this.songFacade = songFacade;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SongDTO>> getAll() {
        try {
            return ResponseEntity.ok(songFacade.findAllSongs());
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SongDTO> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(songFacade.findSongById(id));
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }

//    @GetMapping(value = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<EntityModel<SongDTO>> getByName(@PathVariable String name){
//        List<SongDTO> songs = songFacade.findSongByName(name);
//        List<EntityModel<SongDTO>> songsResource = new ArrayList<>();
//        for (SongDTO songDTO : songs){
//            songsResource.add(songResourceAssembler.toModel(songDTO));
//        }
//        CollectionModel<EntityModel<SongDTO>> resultResources = new CollectionModel<>(songsResource);
//        resultResources.add(linkTo(SongController.class).withSelfRel().withType("GET"));
//        return new ResponseEntity<>(resultResources, HttpStatus.OK);
//    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> createSong(@RequestBody @Valid SongCreateDTO songCreateDTO) {
        try {
            return ResponseEntity.ok(songFacade.createSong(songCreateDTO));
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteSong(@RequestBody @Valid SongDTO songDTO) {
        try {
            songFacade.deleteSong(songDTO);;
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, ex.getMessage(), ex);
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateSong(@RequestBody @Valid SongUpdateDTO songUpdateDTO) {
        try {
            songFacade.updateSong(songUpdateDTO);;
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, ex.getMessage(), ex);
        }
        return ResponseEntity.noContent().build();
    }

}
