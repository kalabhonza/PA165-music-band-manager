package cz.fi.muni.pa165.rest.controllers;



import cz.fi.muni.pa165.api.dto.tour.TourCreateDTO;
import cz.fi.muni.pa165.api.dto.tour.TourDTO;
import cz.fi.muni.pa165.api.dto.tour.TourUpdateDTO;
import cz.fi.muni.pa165.api.facade.TourFacade;
import cz.fi.muni.pa165.rest.assemblers.TourResourceAssembler;
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
@RequestMapping("/rest/tours")
public class TourController {
    private TourFacade tourFacade;
    private TourResourceAssembler tourResourceAssembler;

    public TourController(TourFacade tourFacade, TourResourceAssembler tourResourceAssembler) {
        this.tourFacade = tourFacade;
        this.tourResourceAssembler = tourResourceAssembler;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<TourDTO>>> getAll(){
        List<TourDTO> tours = tourFacade.findAllTours();
        List<EntityModel<TourDTO>> tourResource = new ArrayList<>();
        for (TourDTO tourDTO : tours){
            tourResource.add(tourResourceAssembler.toModel(tourDTO));
        }
        CollectionModel<EntityModel<TourDTO>> resultResources = new CollectionModel<>(tourResource);
        resultResources.add(linkTo(TourController.class).withSelfRel().withType("GET"));
        return new ResponseEntity<>(resultResources, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<TourDTO>> getById(@PathVariable Long id){
        return new ResponseEntity<>(tourResourceAssembler.toModel(tourFacade.findById(id)), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> createTour(@RequestBody @Valid TourCreateDTO tourCreateDTO){
        return new ResponseEntity<>(tourFacade.create(tourCreateDTO), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteTour(@RequestBody @Valid TourDTO tourDTO){
        tourFacade.remove(tourDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateTour(@RequestBody @Valid TourUpdateDTO tourUpdateDTO){
        tourFacade.update(tourUpdateDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
