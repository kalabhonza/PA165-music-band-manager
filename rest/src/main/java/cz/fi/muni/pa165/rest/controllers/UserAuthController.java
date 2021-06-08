package cz.fi.muni.pa165.rest.controllers;

import cz.fi.muni.pa165.api.dto.manager.ManagerDTO;
import cz.fi.muni.pa165.api.dto.musician.MusicianDTO;
import cz.fi.muni.pa165.api.facade.ManagerFacade;
import cz.fi.muni.pa165.api.facade.MusicianFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


/**
 * @author Igor Ignác
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rest/auth")
public class UserAuthController {
    private MusicianFacade musicianFacade;
    private ManagerFacade managerFacade;

    @Autowired
    public UserAuthController(MusicianFacade musicianFacade, ManagerFacade managerFacade) {
        this.managerFacade = managerFacade;
        this.musicianFacade = musicianFacade;
    }

    /**
     * User login
     *
     * @param name username of user
     * @param pass password of user
     * @return Resource<UserDTO>
     */
    @GetMapping("/user-login/{name}/password/{pass}")
    public ResponseEntity<MusicianDTO> loginUser(@PathVariable String name, @PathVariable String pass){
        try {
            MusicianDTO musicianDTO = musicianFacade.login(name, pass);
            return new ResponseEntity<>(musicianDTO, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage(), e);
        }
    }

    /**
     * User login
     *
     * @param name username of user
     * @param pass password of user
     * @return Resource<UserDTO>
     */
    @GetMapping("/manager-login/{name}/password/{pass}")
    public ResponseEntity<ManagerDTO> loginManager(@PathVariable String name, @PathVariable String pass){
        try {
            ManagerDTO managerDTO = managerFacade.login(name, pass);
            return new ResponseEntity<>(managerDTO, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage(), e);
        }
    }

    /**
     * User logout
     */
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/logout")
    public ResponseEntity<Void> logout(){
        musicianFacade.logout();
        return ResponseEntity.noContent().build();
    }
}
