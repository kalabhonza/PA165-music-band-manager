package cz.fi.muni.pa165.rest.controllers;

import cz.fi.muni.pa165.api.dto.song.SongCreateDTO;
import cz.fi.muni.pa165.api.dto.song.SongDTO;
import cz.fi.muni.pa165.api.dto.song.SongUpdateDTO;
import cz.fi.muni.pa165.api.facade.SongFacade;
import cz.fi.muni.pa165.rest.assemblers.SongResourceAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rest/songs")
public class SongController {

    private SongFacade songFacade;
    private SongResourceAssembler songResourceAssembler;

    @Autowired
    public SongController(SongFacade songFacade, SongResourceAssembler songResourceAssembler) {
        this.songFacade = songFacade;
        this.songResourceAssembler = songResourceAssembler;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SongDTO>> getAll() {
        List<SongDTO> songs = songFacade.findAllSongs();
        return ResponseEntity.ok(songs);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<SongDTO>> getById(@PathVariable Long id) {
        return new ResponseEntity<>(songResourceAssembler.toModel(songFacade.findSongById(id)), HttpStatus.OK);
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
        return new ResponseEntity<>(songFacade.createSong(songCreateDTO), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteSong(@RequestBody @Valid SongDTO songDTO) {
        songFacade.deleteSong(songDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateSong(@RequestBody @Valid SongUpdateDTO songUpdateDTO) {
        songFacade.updateSong(songUpdateDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
