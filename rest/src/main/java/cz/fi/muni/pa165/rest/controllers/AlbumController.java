package cz.fi.muni.pa165.rest.controllers;

import cz.fi.muni.pa165.api.dto.album.AlbumCreateDTO;
import cz.fi.muni.pa165.api.dto.album.AlbumDTO;
import cz.fi.muni.pa165.api.dto.album.AlbumUpdateDTO;
import cz.fi.muni.pa165.api.facade.AlbumFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import javax.validation.Valid;
import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rest/albums")
public class AlbumController {
    private AlbumFacade albumFacade;


    @Autowired
    public AlbumController(AlbumFacade albumFacade) {
        this.albumFacade = albumFacade;

    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AlbumDTO>> getAll(){
        try {
            return ResponseEntity.ok(albumFacade.findAllAlbums());
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AlbumDTO> getById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(albumFacade.findById(id));
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> createAlbum(@RequestBody @Valid AlbumCreateDTO albumCreateDTO){
        try {
            return ResponseEntity.ok(albumFacade.create(albumCreateDTO));
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteAlbum(@RequestBody @Valid AlbumDTO albumDTO){
        try {
            albumFacade.remove(albumDTO);;
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, ex.getMessage(), ex);
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateAlbum(@RequestBody @Valid AlbumUpdateDTO albumUpdateDTO){
        try {
            albumFacade.update(albumUpdateDTO);;
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, ex.getMessage(), ex);
        }
        return ResponseEntity.noContent().build();
    }
}
