package cz.fi.muni.pa165.rest.controllers;

import cz.fi.muni.pa165.api.facade.PresentationDataFacade;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

/**
 * Testing rest controller
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rest/populate")
public class TestController {

    private PresentationDataFacade presentationDataFacade;

    public TestController(PresentationDataFacade presentationDataFacade) {
        this.presentationDataFacade = presentationDataFacade;
    }

    @GetMapping
    public String helloWorld(){
        return "works";
    }

    /**
     * Creates test data and logs as admin
     */
    @PostMapping
    public void createTestData() {
        try{
            presentationDataFacade.createData();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        }
    }
}
