package cz.fi.muni.pa165.rest.assemblers;


import cz.fi.muni.pa165.api.dto.concert.ConcertDTO;
import cz.fi.muni.pa165.rest.controllers.ConcertController;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * @author Aleš Paroulek
 */
@Component
public class ConcertResourceAssembler implements ResourceAssembler<ConcertDTO, Resource<ConcertDTO>>{
    @Override
    public Resource<ConcertDTO> toResource(ConcertDTO concertDTO) {
        Resource<ConcertDTO> concertResource = new Resource<>(concertDTO);
        try {
            concertResource.add(linkTo(ConcertController.class).slash(concertDTO.getId()).withSelfRel().withType("GET"));
        } catch (Exception ex){

        }
        return concertResource;
    }
}
