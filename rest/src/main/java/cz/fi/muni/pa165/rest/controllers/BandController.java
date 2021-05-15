package cz.fi.muni.pa165.rest.controllers;

import cz.fi.muni.pa165.api.dto.band.BandDTO;
import cz.fi.muni.pa165.api.facade.BandFacade;
import cz.fi.muni.pa165.rest.assemblers.BandResourceAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
