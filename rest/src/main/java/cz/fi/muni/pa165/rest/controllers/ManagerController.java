package cz.fi.muni.pa165.rest.controllers;

import cz.fi.muni.pa165.api.dto.band.BandDTO;
import cz.fi.muni.pa165.api.dto.manager.ManagerCreateDTO;
import cz.fi.muni.pa165.api.dto.manager.ManagerDTO;
import cz.fi.muni.pa165.api.dto.manager.ManagerUpdateDTO;
import cz.fi.muni.pa165.api.facade.BandFacade;
import cz.fi.muni.pa165.api.facade.ManagerFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Igor Ignác
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rest/managers")
public class ManagerController {
    private ManagerFacade managerFacade;
    private BandFacade bandFacade;

    @Autowired
    public ManagerController(ManagerFacade managerFacade, BandFacade bandFacade) {
        this.managerFacade = managerFacade;
        this.bandFacade = bandFacade;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ManagerDTO>> getAll(){
        List<ManagerDTO> managers = managerFacade.findAll();
        return ResponseEntity.ok(managers);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ManagerDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(managerFacade.findById(id));
    }

    @GetMapping(value = "/{id}/bands", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BandDTO> getByBandId(@PathVariable Long id){
        return ResponseEntity.ok(managerFacade.getManagerBand(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> createManager(@RequestBody @Valid ManagerCreateDTO managerCreateDTO){
        return ResponseEntity.ok(managerFacade.create(managerCreateDTO));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteManager(@RequestBody @Valid ManagerDTO managerDTO){
        managerFacade.remove(managerDTO);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateManager(@RequestBody @Valid ManagerUpdateDTO managerUpdateDTO){
        managerFacade.update(managerUpdateDTO);
        return ResponseEntity.noContent().build();
    }
}