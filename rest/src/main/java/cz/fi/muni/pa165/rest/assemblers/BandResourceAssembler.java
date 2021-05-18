package cz.fi.muni.pa165.rest.assemblers;

import cz.fi.muni.pa165.api.dto.band.BandDTO;
import cz.fi.muni.pa165.rest.controllers.BandController;


import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@Component
public class BandResourceAssembler implements RepresentationModelAssembler<BandDTO, EntityModel<BandDTO>> {
    @Override
    public EntityModel<BandDTO> toModel(BandDTO bandDTO) {
        EntityModel<BandDTO> bandResource = new EntityModel<>(bandDTO);
        try {
            bandResource.add(linkTo(BandController.class).slash(bandDTO.getId()).withSelfRel().withType("GET"));
        } catch (Exception ex){

        }
        return bandResource;
    }
}
