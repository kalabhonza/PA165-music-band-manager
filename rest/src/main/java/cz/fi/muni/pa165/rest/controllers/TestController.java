package cz.fi.muni.pa165.rest.controllers;

import org.springframework.web.bind.annotation.*;

/**
 * Testing rest controller
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rest/test")
public class TestController {

    @GetMapping
    public String helloWorld(){
        System.out.println("Workds");
        return "works";
    }

//    /**
//     * Creates test data and logs as admin
//     */
//    @PostMapping
//    public void createTestData() {
//        testDataFacade.createTestData();
//    }
}
