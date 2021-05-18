package cz.fi.muni.pa165.rest.assemblers;


import cz.fi.muni.pa165.api.dto.tour.TourDTO;
import cz.fi.muni.pa165.rest.controllers.TourController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class TourResourceAssembler implements RepresentationModelAssembler<TourDTO, EntityModel<TourDTO>> {
    @Override
    public EntityModel<TourDTO> toModel(TourDTO tourDTO) {
        EntityModel<TourDTO> tourResource = new EntityModel<>(tourDTO);
        try {
            tourResource.add(linkTo(TourController.class).slash(tourDTO.getId()).withSelfRel().withType("GET"));
        } catch (Exception ex){

        }
        return tourResource;
    }
}
