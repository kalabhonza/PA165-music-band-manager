package cz.fi.muni.pa165.rest.controllers;

import cz.fi.muni.pa165.api.dto.band.BandCreateDTO;
import cz.fi.muni.pa165.api.dto.band.BandDTO;
import cz.fi.muni.pa165.api.dto.band.BandUpdateDTO;
import cz.fi.muni.pa165.api.dto.manager.ManagerDTO;
import cz.fi.muni.pa165.api.facade.BandFacade;
import cz.fi.muni.pa165.rest.assemblers.BandResourceAssembler;
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
@RequestMapping("/rest/bands")
public class BandController {

    private BandFacade bandFacade;
    private BandResourceAssembler bandResourceAssembler;

    @Autowired
    public BandController(BandFacade bandFacade, BandResourceAssembler bandResourceAssembler){
        this.bandFacade = bandFacade;
        this.bandResourceAssembler = bandResourceAssembler;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<BandDTO>>> getAll(){
        List<BandDTO> bands = bandFacade.findAllBands();
        List<EntityModel<BandDTO>> bandsResource = new ArrayList<>();
        for (BandDTO bandDTO : bands){
            bandsResource.add(bandResourceAssembler.toModel(bandDTO));
        }
        CollectionModel<EntityModel<BandDTO>> resultResources = new CollectionModel<>(bandsResource);
        resultResources.add(linkTo(BandController.class).withSelfRel().withType("GET"));
        return new ResponseEntity<>(resultResources, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<BandDTO>> getById(@PathVariable Long id){
        return new ResponseEntity<>(bandResourceAssembler.toModel(bandFacade.findBandById(id)), HttpStatus.OK);
    }

//    @GetMapping(value = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<EntityModel<BandDTO>> getByName(@PathVariable String name){
//        List<BandDTO> bands = bandFacade.findBandByName(name);
//        List<EntityModel<BandDTO>> bandsResource = new ArrayList<>();
//        for (BandDTO bandDTO : bands){
//            bandsResource.add(bandResourceAssembler.toModel(bandDTO));
//        }
//        CollectionModel<EntityModel<BandDTO>> resultResources = new CollectionModel<>(bandsResource);
//        resultResources.add(linkTo(BandController.class).withSelfRel().withType("GET"));
//        return new ResponseEntity<>(resultResources, HttpStatus.OK);
//    }

//    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<EntityModel<BandDTO>> getByManager(@RequestBody @Valid ManagerDTO managerDTO){
//        return new ResponseEntity<>(bandResourceAssembler.toModel(bandFacade.findBandByManager(managerDTO)), HttpStatus.OK);
//    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> createBand(@RequestBody @Valid BandCreateDTO bandCreateDTO){
        return new ResponseEntity<>(bandFacade.createBand(bandCreateDTO), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteBand(@RequestBody @Valid BandDTO bandDTO){
        bandFacade.deleteBand(bandDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateBand(@RequestBody @Valid BandUpdateDTO bandUpdateDTO){
        bandFacade.updateBand(bandUpdateDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
