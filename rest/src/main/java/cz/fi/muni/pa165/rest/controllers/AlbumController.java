package cz.fi.muni.pa165.rest.controllers;

import cz.fi.muni.pa165.api.dto.album.AlbumCreateDTO;
import cz.fi.muni.pa165.api.dto.album.AlbumDTO;
import cz.fi.muni.pa165.api.dto.album.AlbumUpdateDTO;
import cz.fi.muni.pa165.api.facade.AlbumFacade;
import cz.fi.muni.pa165.rest.assemblers.AlbumResourceAssembler;
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
@RequestMapping("/rest/albums")
public class AlbumController {
    private AlbumFacade albumFacade;
    private AlbumResourceAssembler albumResourceAssembler;

    @Autowired

    public AlbumController(AlbumFacade albumFacade, AlbumResourceAssembler albumResourceAssembler) {
        this.albumFacade = albumFacade;
        this.albumResourceAssembler = albumResourceAssembler;
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<AlbumDTO>>> getAll(){
        List<AlbumDTO> albums = albumFacade.findAllAlbums();
        List<EntityModel<AlbumDTO>> albumResource = new ArrayList<>();
        for (AlbumDTO albumDTO : albums){
            albumResource.add(albumResourceAssembler.toModel(albumDTO));
        }
        CollectionModel<EntityModel<AlbumDTO>> resultResources = new CollectionModel<>(albumResource);
        resultResources.add(linkTo(AlbumController.class).withSelfRel().withType("GET"));
        return new ResponseEntity<>(resultResources, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<AlbumDTO>> getById(@PathVariable Long id){
        return new ResponseEntity<>(albumResourceAssembler.toModel(albumFacade.findById(id)), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> createAlbum(@RequestBody @Valid AlbumCreateDTO albumCreateDTO){
        return new ResponseEntity<>(albumFacade.create(albumCreateDTO), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteAlbum(@RequestBody @Valid AlbumDTO albumDTO){
        albumFacade.remove(albumDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateAlbum(@RequestBody @Valid AlbumUpdateDTO albumUpdateDTO){
        albumFacade.update(albumUpdateDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
