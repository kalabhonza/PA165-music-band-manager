package cz.fi.muni.pa165.rest.controllers;

import cz.fi.muni.pa165.api.dto.manager.ManagerCreateDTO;
import cz.fi.muni.pa165.api.dto.manager.ManagerDTO;
import cz.fi.muni.pa165.api.dto.manager.ManagerUpdateDTO;
import cz.fi.muni.pa165.api.facade.ManagerFacade;
import cz.fi.muni.pa165.rest.assemblers.ManagerResourceAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;


import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Igor Ignác
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rest/manager")
public class ManagerController {
    private ManagerFacade managerFacade;
    private ManagerResourceAssembler managerResourceAssembler;

    @Autowired
    public ManagerController(ManagerFacade managerFacade, ManagerResourceAssembler managerResourceAssembler) {
        this.managerFacade = managerFacade;
        this.managerResourceAssembler = managerResourceAssembler;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Resources<Resource<ManagerDTO>>> getAll(){
        List<ManagerDTO> managers = managerFacade.findAll();
        List<Resource<ManagerDTO>> managersResource = new ArrayList<>();
        for (ManagerDTO managerDTO : managers){
            managersResource.add(managerResourceAssembler.toResource(managerDTO));
        }
        Resources<Resource<ManagerDTO>> resultResources = new Resources<>(managersResource);
        resultResources.add(linkTo(ManagerController.class).withSelfRel().withType("GET"));
        return new ResponseEntity<>(resultResources, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Resource<ManagerDTO>> getById(@PathVariable Long id){
        return new ResponseEntity<>(managerResourceAssembler.toResource(managerFacade.findById(id)), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> createManager(@RequestBody @Valid ManagerCreateDTO managerCreateDTO){
        return new ResponseEntity<>(managerFacade.create(managerCreateDTO), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteManager(@RequestBody @Valid ManagerDTO managerDTO){
        managerFacade.remove(managerDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateManager(@RequestBody @Valid ManagerUpdateDTO managerUpdateDTO){
        managerFacade.update(managerUpdateDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}