package cz.fi.muni.pa165.rest.controllers;

import cz.fi.muni.pa165.api.dto.manager.ManagerDTO;
import cz.fi.muni.pa165.api.dto.musician.MusicianCreateDTO;
import cz.fi.muni.pa165.api.dto.musician.MusicianDTO;
import cz.fi.muni.pa165.api.dto.user.UserDTO;
import cz.fi.muni.pa165.api.facade.ManagerFacade;
import cz.fi.muni.pa165.api.facade.MusicianFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

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
     * @param pass password of hero
     * @return Resource<UserDTO>
     */
    @GetMapping("/user-login/{name}/password/{pass}")
    public ResponseEntity<MusicianDTO> loginUser(@PathVariable String name, @PathVariable String pass){
        MusicianDTO musicianDTO = musicianFacade.login(name, pass);
        return new ResponseEntity<>(musicianDTO, HttpStatus.OK);
    }

    /**
     * User login
     *
     * @param name username of user
     * @param pass password of hero
     * @return Resource<UserDTO>
     */
    @GetMapping("/manager-login/{name}/password/{pass}")
    public ResponseEntity<ManagerDTO> loginManager(@PathVariable String name, @PathVariable String pass){
        ManagerDTO managerDTO = managerFacade.login(name, pass);
        return new ResponseEntity<>(managerDTO, HttpStatus.OK);
    }

    /**
     * User logout
     */
    @RolesAllowed({"ROLE_USER"})
    @GetMapping("/logout")
    public ResponseEntity<Void> logout(){
        SecurityContextHolder.clearContext();
        return ResponseEntity.noContent().build();
    }
}
