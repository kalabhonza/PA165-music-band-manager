package cz.fi.muni.pa165.rest.assemblers;


import cz.fi.muni.pa165.api.dto.tour.TourDTO;
import cz.fi.muni.pa165.rest.controllers.TourController;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Component
public class TourResourceAssembler implements ResourceAssembler<TourDTO, Resource<TourDTO>> {
    @Override
    public Resource<TourDTO> toResource(TourDTO tourDTO) {
        Resource<TourDTO> tourResource = new Resource<>(tourDTO);
        try {
            tourResource.add(linkTo(TourController.class).slash(tourDTO.getId()).withSelfRel().withType("GET"));
        } catch (Exception ex){

        }
        return tourResource;
    }
}
