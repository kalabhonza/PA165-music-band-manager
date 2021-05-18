package cz.fi.muni.pa165.rest.assemblers;


import cz.fi.muni.pa165.api.dto.concert.ConcertDTO;
import cz.fi.muni.pa165.rest.controllers.ConcertController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

/**
 * @author Aleš Paroulek
 */
@Component
public class ConcertResourceAssembler implements RepresentationModelAssembler<ConcertDTO, EntityModel<ConcertDTO>>{
    @Override
    public EntityModel<ConcertDTO> toModel(ConcertDTO concertDTO) {
        EntityModel<ConcertDTO> concertResource = new EntityModel<>(concertDTO);
        try {
            concertResource.add(linkTo(ConcertController.class).slash(concertDTO.getId()).withSelfRel().withType("GET"));
        } catch (Exception ex){

        }
        return concertResource;
    }
}
