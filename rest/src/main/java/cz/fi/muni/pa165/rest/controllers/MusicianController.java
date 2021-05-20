package cz.fi.muni.pa165.rest.controllers;

import cz.fi.muni.pa165.api.dto.musician.MusicianCreateDTO;
import cz.fi.muni.pa165.api.dto.musician.MusicianDTO;
import cz.fi.muni.pa165.api.dto.musician.MusicianUpdateDTO;
import cz.fi.muni.pa165.api.facade.MusicianFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Aleš Paroulek
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rest/musicians")
public class MusicianController {
    private MusicianFacade musicianFacade;

    @Autowired
    public MusicianController(MusicianFacade musicianFacade) {
        this.musicianFacade = musicianFacade;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MusicianDTO>> getAll(){
        List<MusicianDTO> musicians = musicianFacade.findAll();
        return ResponseEntity.ok(musicians);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ResponseEntity<MusicianDTO> getById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(musicianFacade.findById(id));
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> createMusician(@RequestBody @Valid MusicianCreateDTO musicianCreateDTO){
        return ResponseEntity.ok(musicianFacade.create(musicianCreateDTO));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteMusician(@RequestBody @Valid MusicianDTO musicianDTO){
        musicianFacade.remove(musicianDTO);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateMusician(@RequestBody @Valid MusicianUpdateDTO musicianUpdateDTO){
        musicianFacade.update(musicianUpdateDTO);
        return ResponseEntity.noContent().build();
    }
}
