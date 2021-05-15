package cz.fi.muni.pa165.rest.controllers;

import cz.fi.muni.pa165.api.dto.band.BandCreateDTO;
import cz.fi.muni.pa165.api.dto.band.BandDTO;
import cz.fi.muni.pa165.api.dto.band.BandUpdateDTO;
import cz.fi.muni.pa165.api.dto.manager.ManagerDTO;
import cz.fi.muni.pa165.api.facade.BandFacade;
import cz.fi.muni.pa165.rest.assemblers.BandResourceAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
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
    public ResponseEntity<Resources<Resource<BandDTO>>> getAll(){
        try {
            List<BandDTO> bands = bandFacade.findAllBands();
            List<Resource<BandDTO>> bandsResource = new ArrayList<>();
            for (BandDTO bandDTO : bands){
                bandsResource.add(bandResourceAssembler.toResource(bandDTO));
            }
            Resources<Resource<BandDTO>> resultResources = new Resources<>(bandsResource);
            resultResources.add(linkTo(BandController.class).withSelfRel().withType("GET"));
            return new ResponseEntity<>(resultResources, HttpStatus.OK);
        }catch (Exception ex){
            //throw ExceptionSorter.throwException(ex);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Resource<BandDTO>> getById(@PathVariable Long id){
        try {
            return new ResponseEntity<>(bandResourceAssembler.toResource(bandFacade.findBandById(id)), HttpStatus.OK);
        }catch (Exception ex){
            //throw ExceptionSorter.throwException(ex);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Resource<BandDTO>> getByName(@PathVariable String name){
        try {
            List<BandDTO> bands = bandFacade.findBandByName(name);
            List<Resource<BandDTO>> bandsResource = new ArrayList<>();
            for (BandDTO bandDTO : bands){
                bandsResource.add(bandResourceAssembler.toResource(bandDTO));
            }
            Resources<Resource<BandDTO>> resultResources = new Resources<>(bandsResource);
            resultResources.add(linkTo(BandController.class).withSelfRel().withType("GET"));
            return new ResponseEntity<>(resultResources, HttpStatus.OK);
        }catch (Exception ex){
            //throw ExceptionSorter.throwException(ex);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Resource<BandDTO>> getByManager(@RequestBody @Valid ManagerDTO managerDTO){
        try {
            return new ResponseEntity<>(bandResourceAssembler.toResource(bandFacade.findBandByManager(managerDTO)), HttpStatus.OK);
        }catch (Exception ex){
            //throw ExceptionSorter.throwException(ex);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> createBand(@RequestBody @Valid BandCreateDTO bandCreateDTO){
        try{
            return new ResponseEntity<>(bandFacade.createBand(bandCreateDTO), HttpStatus.CREATED);
        }catch (Exception ex){
            //throw ExceptionSorter.throwException(ex);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteBand(@RequestBody @Valid BandDTO bandDTO){
        try {
            bandFacade.deleteBand(bandDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex){
            //throw ExceptionSorter.throwException(ex);
        }
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateBand(@RequestBody @Valid BandUpdateDTO bandUpdateDTO){
        try {
            bandFacade.updateBand(bandUpdateDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception ex){
            //throw ExceptionSorter.throwException(ex);
        }
    }






}
