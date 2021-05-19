package cz.fi.muni.pa165.rest.controllers;

import cz.fi.muni.pa165.api.dto.user.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Igor Ignác
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rest/auth")
public class UserAuthController {

    /**
     * User login
     *
     * @param name username of user
     * @param pass password of hero
     * @return Resource<UserDTO>
     */
    @GetMapping("/login/{name}/password/{pass}")
    public ResponseEntity<UserDTO> login(@PathVariable String name, @PathVariable String pass){
//        try {
//            UserDTO userDTO = userFacade.login(name, pass);
//            return new ResponseEntity<>(userResourceAssembler.toResource(userDTO), HttpStatus.OK);
//        }catch (Exception ex){
//            throw ExceptionSorter.throwException(ex);
//        }
    }
}
