package cz.fi.muni.pa165.rest.controllers;

import cz.fi.muni.pa165.api.facade.PresentationDataFacade;
import org.springframework.web.bind.annotation.*;

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
        presentationDataFacade.createData();
    }
}
